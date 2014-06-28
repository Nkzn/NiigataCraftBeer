package info.nkzn.niigatacraftbeer.core;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import net.vvakame.util.jsonpullparser.annotation.JsonKey;
import net.vvakame.util.jsonpullparser.annotation.JsonModel;

import java.util.Date;

import info.nkzn.niigatacraftbeer.core.tokenconverter.UriConverter;

@JsonModel
public class Beer implements Parcelable {

    @JsonKey
    String name;

    @JsonKey
    Date lastDrunk;

    @JsonKey(converter = UriConverter.class)
    Uri photoUri;

    public Beer() {
    }

    /*テスト用*/
    public Beer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastDrunk() {
        return lastDrunk;
    }

    public void setLastDrunk(Date lastDrunk) {
        this.lastDrunk = lastDrunk;
    }

    public Uri getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(Uri photoUri) {
        this.photoUri = photoUri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || super.getClass() != o.getClass()) return false;

        Beer beer = (Beer) o;

        if (lastDrunk != null ? !lastDrunk.equals(beer.lastDrunk) : beer.lastDrunk != null)
            return false;
        if (name != null ? !name.equals(beer.name) : beer.name != null) return false;
        if (photoUri != null ? !photoUri.equals(beer.photoUri) : beer.photoUri != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastDrunk != null ? lastDrunk.hashCode() : 0);
        result = 31 * result + (photoUri != null ? photoUri.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "name='" + name + '\'' +
                ", lastDrunk=" + lastDrunk +
                ", photoUri=" + photoUri +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeLong(lastDrunk != null ? lastDrunk.getTime() : -1);
        dest.writeParcelable(this.photoUri, 0);
    }

    private Beer(Parcel in) {
        this.name = in.readString();
        long tmpLastDrunk = in.readLong();
        this.lastDrunk = tmpLastDrunk == -1 ? null : new Date(tmpLastDrunk);
        this.photoUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Parcelable.Creator<Beer> CREATOR = new Parcelable.Creator<Beer>() {
        public Beer createFromParcel(Parcel source) {
            return new Beer(source);
        }

        public Beer[] newArray(int size) {
            return new Beer[size];
        }
    };
}
