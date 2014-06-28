package info.nkzn.niigatacraftbeer;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    private static final String PREF_KEY_INSTAGRAM_CHECK = MainActivity.class.getCanonicalName() + ".instagram_check";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, BreweryListFragment_.builder().build())
                    .commit();

            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            if (!pref.getBoolean(PREF_KEY_INSTAGRAM_CHECK, false)) {

                String appId = "com.instagram.android";
                try {
                    PackageManager pm = getPackageManager();
                    ApplicationInfo appInfo = pm.getApplicationInfo(appId, PackageManager.GET_META_DATA);
                } catch(PackageManager.NameNotFoundException e) {
                    Toast.makeText(this, R.string.suggest_install_instagram, Toast.LENGTH_LONG).show();
                }

                pref.edit().putBoolean(PREF_KEY_INSTAGRAM_CHECK, true).commit();
            }
        }

    }
}
