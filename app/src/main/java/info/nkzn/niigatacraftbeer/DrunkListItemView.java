package info.nkzn.niigatacraftbeer;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.Date;

@EViewGroup(R.layout.drunk_list_item)
public class DrunkListItemView extends FrameLayout {

    @ViewById
    TextView text1;

    @ViewById
    TextView text2;

    @ViewById
    TextView tvNumber;

    public DrunkListItemView(Context context) {
        super(context);
    }

    public void bind(DrunkListItem drunkListItem, int position) {
        text1.setText(drunkListItem.getBreweryName() + "/" + drunkListItem.getBeerName());
        text2.setText(formatDate(drunkListItem.getLastDrunk()));
        tvNumber.setText("" + (position + 1));
    }

    String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(getContext().getString(R.string.date_format));
        return sdf.format(date);
    }
}
