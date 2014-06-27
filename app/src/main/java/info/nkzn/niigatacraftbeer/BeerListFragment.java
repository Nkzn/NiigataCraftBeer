package info.nkzn.niigatacraftbeer;

import android.app.ListFragment;
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

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import info.nkzn.niigatacraftbeer.core.Brewery;

@EFragment
public class BeerListFragment extends ListFragment {

    @FragmentArg
    ArrayList<String> beers;

    @ViewById
    ListView list;

    ArrayAdapter<String> adapter;

    @AfterViews
    void bindAdapter() {
        if (adapter == null) {
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, beers);
        }
        setListAdapter(adapter);

        list.setItemsCanFocus(false);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final SparseBooleanArray checkList = list.getCheckedItemPositions();

                // TODO Twitter/Facebookへの写真投稿を促す

                // TODO チェック状態を保存する
            }
        });
    }
}
