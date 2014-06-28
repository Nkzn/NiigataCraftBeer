package info.nkzn.niigatacraftbeer;

import android.net.Uri;

import java.util.Comparator;
import java.util.Date;

public class DrunkListItem {
    String beerName;
    String breweryName;
    Uri imageUri;
    Date lastDrunk;

    public DrunkListItem(String beerName, String breweryName, Uri imageUri, Date lastDrunk) {
        this.beerName = beerName;
        this.breweryName = breweryName;
        this.imageUri = imageUri;
        this.lastDrunk = lastDrunk;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public String getBreweryName() {
        return breweryName;
    }

    public void setBreweryName(String breweryName) {
        this.breweryName = breweryName;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public Date getLastDrunk() {
        return lastDrunk;
    }

    public void setLastDrunk(Date lastDrunk) {
        this.lastDrunk = lastDrunk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DrunkListItem that = (DrunkListItem) o;

        if (beerName != null ? !beerName.equals(that.beerName) : that.beerName != null)
            return false;
        if (breweryName != null ? !breweryName.equals(that.breweryName) : that.breweryName != null)
            return false;
        if (imageUri != null ? !imageUri.equals(that.imageUri) : that.imageUri != null)
            return false;
        if (lastDrunk != null ? !lastDrunk.equals(that.lastDrunk) : that.lastDrunk != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = beerName != null ? beerName.hashCode() : 0;
        result = 31 * result + (breweryName != null ? breweryName.hashCode() : 0);
        result = 31 * result + (imageUri != null ? imageUri.hashCode() : 0);
        result = 31 * result + (lastDrunk != null ? lastDrunk.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DrunkListItem{" +
                "beerName='" + beerName + '\'' +
                ", breweryName='" + breweryName + '\'' +
                ", imageUri=" + imageUri +
                ", lastDrunk=" + lastDrunk +
                '}';
    }

    public static Comparator<DrunkListItem> getComparator() {
        return new DrunkListItemComparator();
    }

    public static class DrunkListItemComparator implements Comparator<DrunkListItem> {
        @Override
        public int compare(DrunkListItem lhs, DrunkListItem rhs) {
            Date lhsDate = lhs.getLastDrunk();
            Date rhsDate = rhs.getLastDrunk();
            return lhsDate.compareTo(rhsDate);
        }
    }
}