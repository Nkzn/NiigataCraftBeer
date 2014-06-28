package info.nkzn.niigatacraftbeer;

import android.support.v4.app.ListFragment;
import android.widget.GridView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

@EFragment
public class DrunkListFragment extends ListFragment {

    @Bean
    DrunkListAdapter adapter;

    @Override
    public void onResume() {
        super.onResume();

        if (adapter != null) {
            adapter.loadCurrentBreweries();
            adapter.notifyDataSetChanged();
        }
    }

    @AfterViews
    void bindAdapter() {
        setListAdapter(adapter);
    }
}
