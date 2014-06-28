package info.nkzn.niigatacraftbeer.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import net.vvakame.util.jsonpullparser.JsonFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import info.nkzn.niigatacraftbeer.R;

public class BeerProvider {

    private static final String MASTER_LOADED = BeerProvider.class.getCanonicalName() + ".master_loaded";
    private static final String BREWERY_KEYS = BeerProvider.class.getCanonicalName() + ".brewery_keys";

    private BeerProvider() {
    }

    public static List<Brewery> getBreweries(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);

        try {
            // まだrawのJSONからオリジナルデータを読み込んでいないっぽかったら、読み込んでSharedPreferenceにブチ込む
            ensureOriginalDataLoaded(pref, context);

            // SharedPreferenceからデータを引き出して返す
            Set<String> breweryKeys = pref.getStringSet(BREWERY_KEYS, null);
            if (breweryKeys == null) {
                throw new IllegalStateException("breweryKeysにはブルワリーの名前を入れておいてください");
            }

            List<Brewery> breweries = new ArrayList<>();
            for (String breweryName : breweryKeys) {
                String json = pref.getString(breweryName, null);
                breweries.add(BreweryGen.get(json));
            }

            return breweries;
        } catch (IOException e) {
            throw new IllegalStateException("JSONのデータ上手く取れてないよ");
        } catch (JsonFormatException e) {
            throw new IllegalArgumentException("JSONの中身おかしかったみたいよ");
        }
    }

    public static List<Beer> getBeers(Context context, String breweryName) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);

        try {
            ensureOriginalDataLoaded(pref, context);

            String json = pref.getString(breweryName, null);

            return BreweryGen.get(json).getBeers();
        } catch (IOException e) {
            throw new IllegalStateException("JSONのデータ上手く取れてないよ");
        } catch (JsonFormatException e) {
            throw new IllegalArgumentException("JSONの中身おかしかったみたいよ");
        }
    }

    public static void save(Context context, Brewery brewery) {
        StringWriter sw = new StringWriter();
        try {
            BreweryGen.encodeNullToNull(sw, brewery);
        } catch (IOException e) {
            throw new IllegalArgumentException("JSON化に失敗したみたいよ");
        }

        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString(brewery.getName(), sw.toString());
        edit.commit();
    }

    static void ensureOriginalDataLoaded(SharedPreferences pref, Context context) throws IOException, JsonFormatException {
        if (!pref.getBoolean(MASTER_LOADED, false) || pref.getStringSet(BREWERY_KEYS, null) == null) {
            SharedPreferences.Editor edit = pref.edit();

            List<Brewery> breweries = BreweryGen.getList(readJsonAsset(context));

            Set<String> breweryNames = new TreeSet<>();
            for (Brewery brewery : breweries) {
                StringWriter sw = new StringWriter();
                BreweryGen.encodeNullToNull(sw, brewery);
                edit.putString(brewery.getName(), sw.toString());

                breweryNames.add(brewery.getName());
            }

            edit.putStringSet(BREWERY_KEYS, breweryNames);

            edit.putBoolean(MASTER_LOADED, true);
            edit.commit();
        }
    }

    static InputStream readJsonAsset(Context context) throws IOException {
        return context.getResources().openRawResource(R.raw.niigata_craft_beer_2014_json);
    }
}
