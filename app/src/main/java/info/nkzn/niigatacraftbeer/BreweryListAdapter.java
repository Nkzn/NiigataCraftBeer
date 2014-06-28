package info.nkzn.niigatacraftbeer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

import info.nkzn.niigatacraftbeer.core.BeerProvider;
import info.nkzn.niigatacraftbeer.core.Brewery;

@EBean
public class BreweryListAdapter extends BaseAdapter {

    final List<Brewery> breweries = new ArrayList<>();

    @RootContext
    Context context;

    @AfterInject
    public void loadCurrentBreweries() {
        breweries.clear();
        breweries.addAll(BeerProvider.getBreweries(context));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BreweryItemView view;
        if (convertView == null) {
            view = BreweryItemView_.build(context);
        } else {
            view = (BreweryItemView) convertView;
        }

        view.bind((Brewery) getItem(position));

        return view;
    }

    @Override
    public int getCount() {
        return breweries.size();
    }

    @Override
    public Object getItem(int position) {
        return breweries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
