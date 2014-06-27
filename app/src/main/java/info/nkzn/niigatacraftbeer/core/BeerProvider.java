package info.nkzn.niigatacraftbeer.core;

public class BeerProvider {

    private static BeerProvider instance;

    private BeerProvider() {}

    public static BeerProvider getInstance() {
        if (instance == null) {
            instance = new BeerProvider();
        }
        return instance;
    }

}
