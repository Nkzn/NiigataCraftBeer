package info.nkzn.niigatacraftbeer;

import android.app.ListFragment;
import android.content.Intent;
import android.widget.ListView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import info.nkzn.niigatacraftbeer.core.Brewery;

@EFragment
public class BreweryListFragment extends ListFragment {

    @ViewById
    ListView list;

    @Bean
    BreweryListAdapter adapter;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @AfterViews
    void bindAdapter() {
        setListAdapter(adapter);
    }

    @ItemClick
    void listItemClicked(Brewery brewery) {
        BreweryActivity_.intent(this).brewery(brewery).startForResult(0);
    }
}
