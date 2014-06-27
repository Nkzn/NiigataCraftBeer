package info.nkzn.niigatacraftbeer;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import java.util.ArrayList;

import info.nkzn.niigatacraftbeer.R;
import info.nkzn.niigatacraftbeer.core.Brewery;

@EActivity(R.layout.activity_brewery)
public class BreweryActivity extends Activity {

    @Extra
    Brewery brewery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, BeerListFragment_.builder().brewery(brewery).build())
                    .commit();
        }

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0x0102002c: { // android.R.id.home
                finish(); // ホームボタンが押されたらActivityを終了
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @AfterInject
    void initTitle() {
        setTitle(brewery.getName());
    }
}
