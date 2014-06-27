package info.nkzn.niigatacraftbeer.core;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import info.nkzn.niigatacraftbeer.R;

public class BeerProvider {

    private static BeerProvider instance;

    private BeerProvider() {}

    public static BeerProvider getInstance() {
        if (instance == null) {
            instance = new BeerProvider();
        }
        return instance;
    }

    String readJsonAsset(Context context) throws IOException {
        InputStream inputStream = context.getResources().openRawResource(R.raw.niigata_craft_beer_2014_json);

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder sb = new StringBuilder();
        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }

        return sb.toString();
    }
}
