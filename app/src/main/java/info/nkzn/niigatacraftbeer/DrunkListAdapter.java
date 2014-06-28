package info.nkzn.niigatacraftbeer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import info.nkzn.niigatacraftbeer.core.Beer;
import info.nkzn.niigatacraftbeer.core.BeerProvider;
import info.nkzn.niigatacraftbeer.core.Brewery;

@EBean
public class DrunkListAdapter extends BaseAdapter {

    final List<DrunkListItem> drunkListItems = new ArrayList<>();

    @RootContext
    Context context;

    @AfterInject
    public void loadCurrentBreweries() {
        drunkListItems.clear();

        for (Brewery brewery : BeerProvider.getBreweries(context)) {
            for (Beer beer: brewery.getBeers()) {
                if (beer.getLastDrunk() != null) {
                    drunkListItems.add(new DrunkListItem(beer.getName(), brewery.getName(), beer.getPhotoUri(), beer.getLastDrunk()));
                }
            }
        }

        Collections.sort(drunkListItems, DrunkListItem.getComparator());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DrunkListItemView view;
        if (convertView == null) {
            view = DrunkListItemView_.build(context);
        } else {
            view = (DrunkListItemView) convertView;
        }

        view.bind((DrunkListItem) getItem(position), position);

        return view;
    }

    @Override
    public int getCount() {
        return drunkListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return drunkListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
