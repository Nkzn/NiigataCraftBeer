package info.nkzn.niigatacraftbeer;

import android.content.Context;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import info.nkzn.niigatacraftbeer.core.Brewery;

@EViewGroup(android.R.layout.simple_list_item_2)
public class BreweryItemView extends FrameLayout {

    @ViewById
    TextView text1;

    @ViewById
    TextView text2;

    public BreweryItemView(Context context) {
        super(context);
    }

    public void bind(Brewery brewery) {
        text1.setText(brewery.getName());
        text2.setText(TextUtils.join(", ", brewery.getBeers()));
    }
}
