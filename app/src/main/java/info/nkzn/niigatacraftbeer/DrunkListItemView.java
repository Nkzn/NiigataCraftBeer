package info.nkzn.niigatacraftbeer;

import android.content.Context;
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
public class DrunkListItemView extends FrameLayout {

    @ViewById
    TextView text1;

    @ViewById
    TextView text2;

    @ViewById
    ImageView ivBeer;

    public DrunkListItemView(Context context) {
        super(context);
    }

    public void bind(DrunkListItem drunkListItem) {
        text1.setText(drunkListItem.getBreweryName() + "/" + drunkListItem.getBeerName());
        text2.setText(formatDate(drunkListItem.getLastDrunk()));

        Picasso.with(getContext())
                .load(drunkListItem.getImageUri())
                .into(ivBeer);
    }

    String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(getContext().getString(R.string.date_format));
        return sdf.format(date);
    }
}
