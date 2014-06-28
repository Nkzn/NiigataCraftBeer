package info.nkzn.niigatacraftbeer;

import android.app.ActionBar;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * <h3>写真閲覧画面</h3>
 * <h4>使い方</h4>
 * <code>PhotoViewActivity_.intent(this).imageUrl("画像のURL").start()</code>
 */
@EActivity(R.layout.activity_photo_view)
public class PhotoViewActivity extends FragmentActivity {

    private static final String TAG = PhotoViewActivity.class.getSimpleName();

    @Extra
    Uri imageUrl;

    @ViewById
    ImageView ivImage;

    private PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @AfterViews
    void loadImage() {
        Log.d(TAG, "loadImage");
        Picasso.with(this)
                .load(imageUrl)
                .into(ivImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        // 画像の読み込みに成功したらPhotoViewを適用
                        mAttacher = new PhotoViewAttacher(ivImage);

                        // シングルタップでActivityを閉じる
                        mAttacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                            @Override
                            public void onViewTap(View view, float x, float y) {
                                finish();
                            }
                        });
                    }

                    @Override
                    public void onError() {
                        // 読み込めなかったら特に何もしない
                    }
                });
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();

        if (mAttacher != null) {
            mAttacher.cleanup(); // 必要
        }
    }
}