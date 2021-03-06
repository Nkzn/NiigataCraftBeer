package info.nkzn.niigatacraftbeer.core;

import android.os.Parcel;
import android.os.Parcelable;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

import java.util.ArrayList;
import java.util.List;

@JsonModel
public class Brewery implements Parcelable {

    @JsonKey(value = "brewery")
    String name;

    @JsonKey
    List<Beer> beers;

    public Brewery() {
    }

    /* テスト用 */
    public Brewery(String breweryName, String... beerNames) {
        this.name = breweryName;

        beers = new ArrayList<>();
        if (beerNames != null) {
            for (String beerName : beerNames) {
                beers.add(new Beer(beerName));
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Beer> getBeers() {
        return beers;
    }

    public void setBeers(List<Beer> beers) {
        this.beers = beers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || super.getClass() != o.getClass()) return false;

        Brewery brewery = (Brewery) o;

        if (beers != null ? !beers.equals(brewery.beers) : brewery.beers != null) return false;
        if (name != null ? !name.equals(brewery.name) : brewery.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (beers != null ? beers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Brewery{" +
                "name='" + name + '\'' +
                ", beers=" + beers +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeList(this.beers);
    }

    private Brewery(Parcel in) {
        this.name = in.readString();
        this.beers = new ArrayList<>();
        in.readList(this.beers, Beer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Brewery> CREATOR = new Parcelable.Creator<Brewery>() {
        public Brewery createFromParcel(Parcel source) {
            return new Brewery(source);
        }

        public Brewery[] newArray(int size) {
            return new Brewery[size];
        }
    };
}
