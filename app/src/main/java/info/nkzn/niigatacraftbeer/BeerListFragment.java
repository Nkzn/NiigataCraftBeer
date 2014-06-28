package info.nkzn.niigatacraftbeer;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ListFragment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ItemLongClick;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import info.nkzn.niigatacraftbeer.core.Beer;
import info.nkzn.niigatacraftbeer.core.BeerProvider;
import info.nkzn.niigatacraftbeer.core.Brewery;

@EFragment
public class BeerListFragment extends ListFragment {

    private static final String TAG = BeerListFragment.class.getSimpleName();

    private static final int CAMERA_REQUEST_CODE = BeerListFragment.class.hashCode() & 0xffff;

    @FragmentArg
    @InstanceState
    Brewery brewery;

    @InstanceState
    Uri imageUri;

    @InstanceState
    Beer beerToTakePhoto;

    @InstanceState
    Date lastDrankToTakePhoto;

    @InstanceState
    ArrayList<Beer> beers;

    BeerListAdapter adapter;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE) {
            resultCamera(resultCode);
        }
    }

    @AfterViews
    void bindAdapter() {
        beers = new ArrayList<>(brewery.getBeers());
        adapter = new BeerListAdapter(getActivity(), beers);

        setListAdapter(adapter);
    }

    @ItemClick
    void listItemClicked(Beer beer) {
        if (beer.getLastDrunk() == null || beer.getPhotoUri() == null) {
            takeWithCamera(beer);
        } else {
            PhotoViewActivity_.intent(this).imageUrl(beer.getPhotoUri()).start();
        }
    }

    @ItemLongClick
    void listItemLongClicked(Beer beer) {
        takeWithCamera(beer);
    }

    /**
     * カメラで撮影
     */
    private void takeWithCamera(Beer beer) {
        Log.d(TAG, "takeWithCamera");

        final Activity activity = getActivity();
        if (activity == null) {
            Log.w(TAG, "activity is null.");
            return;
        }

        beerToTakePhoto = beer;
        lastDrankToTakePhoto = new Date();

        // カメラで撮影した画像の保存場所を確保
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, getPicFileName(lastDrankToTakePhoto));
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg"); // getPicFileName()が.jpgなので
        imageUri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    /**
     * カメラで撮影のonActivityResult
     */
    private void resultCamera(int resultCode) {
        Log.d(TAG, "resultCamera: " + resultCode);

        final Activity activity = getActivity();
        if (activity == null) {
            Log.w(TAG, "activity is null.");
            return;
        }

        if (resultCode == Activity.RESULT_OK) {
            beerToTakePhoto.setLastDrunk(lastDrankToTakePhoto);
            beerToTakePhoto.setPhotoUri(imageUri);
            int photoTakedBeerIndex = beerIndexOf(beerToTakePhoto);

            beers.set(photoTakedBeerIndex, beerToTakePhoto);

            brewery.setBeers(beers);
            BeerProvider.save(getActivity(), brewery);

            adapter.notifyDataSetChanged();

            openShareDialog(beerToTakePhoto);
        } else {
            // resultがOKでなかったら確保した保存場所を削除
            Log.d(TAG, "cancel: " + activity.getContentResolver().delete(imageUri, null, null));
        }

        imageUri = null;
        beerToTakePhoto = null;
        lastDrankToTakePhoto = null;
    }

    /**
     * 日付から画像ファイル名を生成
     */
    private String getPicFileName(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss'.jpg'");
        return sdf.format(date);
    }

    private int beerIndexOf(Beer beer) {
        for (int i = 0; i < beers.size(); i++) {
            if (TextUtils.equals(beer.getName(), beers.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }

    void openShareDialog(Beer beer) {
        final String shareText = getString(R.string.drunk_now, brewery.getName(), beer.getName());

        ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newPlainText("text_data", shareText));

        Toast.makeText(getActivity(), getString(R.string.please_share_via_clipboard, shareText), Toast.LENGTH_LONG).show();

        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, beer.getPhotoUri());
        shareIntent.setPackage("com.instagram.android");

        try {
            startActivity(shareIntent);
        } catch (ActivityNotFoundException e) {
            // instagramが無いなら無いで握りつぶす
        }
    }
}
