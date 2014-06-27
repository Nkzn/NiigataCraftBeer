package info.nkzn.niigatacraftbeer;

import android.content.Context;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import info.nkzn.niigatacraftbeer.core.Brewery;

@EViewGroup(android.R.layout.simple_list_item_1)
public class BeerItemView extends FrameLayout {

    @ViewById
    TextView text1;

    public BeerItemView(Context context) {
        super(context);
    }

    public void bind(String beerName) {
        text1.setText(beerName);
    }
}
