package info.nkzn.niigatacraftbeer;

import android.content.Intent;
import android.support.v4.app.ListFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;

import info.nkzn.niigatacraftbeer.core.Brewery;

@EFragment
public class BreweryListFragment extends ListFragment {

    private static final int REQUEST_CODE_BREWERY = "brewery".hashCode();

    @Bean
    BreweryListAdapter adapter;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_BREWERY) {
            adapter.loadCurrentBreweries();
        }

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
        BreweryActivity_.intent(this).brewery(brewery).startForResult(REQUEST_CODE_BREWERY);
    }
}
