package info.nkzn.niigatacraftbeer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;


public class BeerListAdapter extends ArrayAdapter<String> {

    public BeerListAdapter(Context context, List<String> objects) {
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
