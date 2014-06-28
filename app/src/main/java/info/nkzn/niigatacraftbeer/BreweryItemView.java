package info.nkzn.niigatacraftbeer;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import net.vvakame.util.jsonpullparser.util.JsonArray;
import net.vvakame.util.jsonpullparser.util.JsonHash;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import info.nkzn.niigatacraftbeer.core.Beer;
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

        String beersHighlightedWithHtml = highlight(brewery);
        //Log.d("bind", beersHighlightedWithHtml);

        text2.setText(Html.fromHtml(beersHighlightedWithHtml));
    }

    private String highlight(Brewery brewery) {
        List<Beer> beers = brewery.getBeers();

        List<String> highlightedBeers = new ArrayList<>();
        for (Beer beer : beers) {
            //Log.d("highlight", "beer -> " + beer.toString());
            if (beer.getLastDrunk() != null) {
                highlightedBeers.add("<font color=\"#FF0000\">" + beer.getName() + "</font>");
            } else {
                highlightedBeers.add(beer.getName());
            }
        }

        return TextUtils.join(", ", highlightedBeers);
    }
}
