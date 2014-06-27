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

        String beersHighlightedWithHtml = highlite(brewery);

        text2.setText(Html.fromHtml(beersHighlightedWithHtml));
    }

    private String highlite(Brewery brewery) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        String json = pref.getString(brewery.getName(), "");

        if (TextUtils.isEmpty(json)) {
            return TextUtils.join(", ", brewery.getBeers());
        }

        List<String> highlightedBeers = new ArrayList<String>();
        try {
            List<String> beers = brewery.getBeers();
            JsonArray jsonArray = JsonArray.fromString(json);

            if (beers.size() != jsonArray.size()) {
                return TextUtils.join(", ", beers);
            }

            for (int i=0; i < beers.size(); i++) {
                final String beer = beers.get(i);

                JsonHash jsonHash = jsonArray.getJsonHashOrNull(i);
                if (jsonHash == null) {
                    highlightedBeers.add(beer);
                    continue;
                }

                Boolean state = jsonHash.getBooleanOrNull("state");

                if (state != null && state) {
                    highlightedBeers.add("<font color=\"#FF0000\">" + beer + "</font>");
                } else {
                    highlightedBeers.add(beer);
                }
            }
        } catch (Exception e) {
            return TextUtils.join(", ", brewery.getBeers());
        }

        return TextUtils.join(", ", highlightedBeers);
    }
}
