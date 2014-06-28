package info.nkzn.niigatacraftbeer;

import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity {

    private static final String PREF_KEY_INSTAGRAM_CHECK = MainActivity.class.getCanonicalName() + ".instagram_check";

    @ViewById
    ViewPager pager;

    @ViewById
    PagerTabStrip strip;

    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {

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

        fragments.add(BreweryListFragment_.builder().build());
        fragments.add(DrunkListFragment_.builder().build());
    }

    @AfterViews
    void afterViews() {
        strip.setTextColor(getResources().getColor(android.R.color.primary_text_dark));
        strip.setTabIndicatorColorResource(android.R.color.primary_text_dark);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), fragments);
        pager.setAdapter(adapter);
    }

    public static class PagerAdapter extends FragmentPagerAdapter {

        List<Fragment> fragments;

        public PagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "ブルワリー一覧";
                case 1:
                    return "これまで飲んだビール一覧";
                default:
                    return "";
            }
        }
    }
}
