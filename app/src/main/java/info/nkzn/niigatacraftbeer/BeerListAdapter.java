package info.nkzn.niigatacraftbeer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import info.nkzn.niigatacraftbeer.core.Beer;

public class BeerListAdapter extends ArrayAdapter<Beer> {

    public BeerListAdapter(Context context, List<Beer> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BeerItemView view;
        if (convertView == null) {
            view = BeerItemView_.build(getContext());
        } else {
            view = (BeerItemView) convertView;
        }

        view.bind(getItem(position));

        return view;
    }
}
