package info.nkzn.niigatacraftbeer;

import android.app.ListFragment;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import net.vvakame.util.jsonpullparser.JsonFormatException;
import net.vvakame.util.jsonpullparser.util.JsonArray;
import net.vvakame.util.jsonpullparser.util.JsonHash;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import info.nkzn.niigatacraftbeer.core.Brewery;

@EFragment
public class BeerListFragment extends ListFragment {

    @FragmentArg
    Brewery brewery;

    @ViewById
    ListView list;

    ArrayAdapter<String> adapter;

    @AfterViews
    void initListView() {
        if (adapter == null) {
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, brewery.getBeers());
        }
        setListAdapter(adapter);

        list.setItemsCanFocus(false);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final SparseBooleanArray checkList = list.getCheckedItemPositions();

                // チェック状態を保存する
                saveCurrentCheckState(checkList, position);

                // TODO Twitter/Facebookへの写真投稿を促す

            }
        });

        restoreCheckState();
    }

    void saveCurrentCheckState(SparseBooleanArray checkList, int position) {
        List<String> beers = brewery.getBeers();
        JsonArray jsonArray = new JsonArray();

        for(int i=0; i < beers.size(); i++) {
            final boolean checked = checkList.get(i, false);

            JsonHash jsonHash = new JsonHash();
            if (i == position && checked) {
                jsonHash.put("last_checked", new Date().getTime());
            }

            jsonHash.put("state", checked);

            jsonArray.add(jsonHash);
        }

        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
        edit.putString(brewery.getName(), jsonArray.toString());
        edit.commit();
    }

    void restoreCheckState() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String json = pref.getString(brewery.getName(), "");

        if (!TextUtils.isEmpty(json)) {
            try {
                JsonArray jsonArray = JsonArray.fromString(json);

                for(int i=0; i < brewery.getBeers().size(); i++) {
                    JsonHash jsonHash = jsonArray.getJsonHashOrNull(i);
                    if (jsonHash != null) {
                        Boolean checked = jsonHash.getBooleanOrNull("state");
                        if (checked != null) {
                            list.setItemChecked(i, checked);
                        }
                    }
                }
            } catch (IOException e) {
            } catch (JsonFormatException e) {
            }
        }
    }
}
