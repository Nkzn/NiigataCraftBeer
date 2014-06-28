package info.nkzn.niigatacraftbeer;

import android.support.v4.app.ListFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;

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

    @ItemClick
    void listItemClicked(DrunkListItem item) {
        PhotoViewActivity_.intent(this).imageUrl(item.getImageUri()).start();
    }
}
