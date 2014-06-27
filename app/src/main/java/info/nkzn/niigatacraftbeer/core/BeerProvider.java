package info.nkzn.niigatacraftbeer.core;

import android.content.Context;

import net.vvakame.util.jsonpullparser.JsonFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import info.nkzn.niigatacraftbeer.R;

public class BeerProvider {

    private static BeerProvider instance;

    private BeerProvider() {
    }

    public static BeerProvider getInstance() {
        if (instance == null) {
            instance = new BeerProvider();
        }
        return instance;
    }

    public List<Brewery> getBreweries(Context context) {
        try {
            return BreweryGen.getList(readJsonAsset(context));
        } catch (IOException e) {
            throw new IllegalStateException("JSONのデータ上手く取れてないよ");
        } catch (JsonFormatException e) {
            throw new IllegalArgumentException("JSONの中身おかしかったみたいよ");
        }
    }

    InputStream readJsonAsset(Context context) throws IOException {
        return context.getResources().openRawResource(R.raw.niigata_craft_beer_2014_json);
    }
}
