package droidrocks.com.namaztimeapp.Models;

import com.google.gson.annotations.SerializedName;


import android.os.Parcel;
import android.os.Parcelable;


public class RuqyahGroupEntity implements Parcelable {

    @SerializedName("id")
    private int id;
    @SerializedName("subtitle")
    private String subtitle;
    @SerializedName("title")
    private String title;

    public RuqyahGroupEntity() {
    }

    public RuqyahGroupEntity(int id, String subtitle, String title) {
        this.id = id;
        this.subtitle = subtitle;
        this.title = title;
    }

    protected RuqyahGroupEntity(Parcel in) {
        id = in.readInt();
        subtitle = in.readString();
        title = in.readString();
    }

    public static final Creator<RuqyahGroupEntity> CREATOR = new Creator<RuqyahGroupEntity>() {
        @Override
        public RuqyahGroupEntity createFromParcel(Parcel in) {
            return new RuqyahGroupEntity(in);
        }

        @Override
        public RuqyahGroupEntity[] newArray(int size) {
            return new RuqyahGroupEntity[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(subtitle);
        dest.writeString(title);
    }
}
