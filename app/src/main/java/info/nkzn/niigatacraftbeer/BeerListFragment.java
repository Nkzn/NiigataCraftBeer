package info.nkzn.niigatacraftbeer;

import android.app.ListFragment;
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

    BeerListAdapter adapter;

    @AfterViews
    void bindAdapter() {
        if (adapter == null) {
            adapter = new BeerListAdapter(getActivity(), beers);
        }
        setListAdapter(adapter);
    }

    @ItemClick
    void listItemClicked(String beer) {
        Toast.makeText(getActivity(), beer, Toast.LENGTH_LONG).show();
    }
}
