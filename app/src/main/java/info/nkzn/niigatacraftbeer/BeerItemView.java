package info.nkzn.niigatacraftbeer;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.Date;

import info.nkzn.niigatacraftbeer.core.Beer;

@EViewGroup(R.layout.beer_list_item)
public class BeerItemView extends FrameLayout {

    @ViewById
    TextView text1;

    @ViewById
    TextView text2;

    @ViewById
    ImageView ivBeer;

    public BeerItemView(Context context) {
        super(context);
    }

    public void bind(Beer beer) {
        text1.setText(beer.getName());

        Date lastDrunk = beer.getLastDrunk();
        if (lastDrunk == null) {
            text2.setText(R.string.didnot_drink_yet);
        } else {
            text2.setText(formatDate(lastDrunk));
        }

        Picasso.with(getContext())
                .load(beer.getPhotoUri())
                .config(Bitmap.Config.ARGB_4444)
                .placeholder(android.R.drawable.ic_menu_camera)
                .error(android.R.drawable.ic_menu_camera)
                .into(ivBeer);
    }

    String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(getContext().getString(R.string.date_format));
        return sdf.format(date);
    }
}
