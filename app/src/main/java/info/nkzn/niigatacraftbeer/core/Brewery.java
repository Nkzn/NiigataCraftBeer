package info.nkzn.niigatacraftbeer.core;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

import java.util.Arrays;
import java.util.List;

@JsonModel
public class Brewery {

    @JsonKey(value = "brewery")
    String name;

    @JsonKey
    List<String> beers;

    public Brewery() {
    }

    /* テスト用 */
    public Brewery(String name, String... beers) {
        this.name = name;
        this.beers = Arrays.asList(beers);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBeers() {
        return beers;
    }

    public void setBeers(List<String> beers) {
        this.beers = beers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

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
}
