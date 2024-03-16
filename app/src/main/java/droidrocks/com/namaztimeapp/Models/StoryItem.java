package droidrocks.com.namaztimeapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class StoryItem implements Parcelable {

    @SerializedName("title")
    public String title;
    @SerializedName("text_file")
    public String text_file;
    @SerializedName("image")
    public String image;

    public StoryItem() {
    }

    protected StoryItem(Parcel in) {
        title = in.readString();
        text_file = in.readString();
        image = in.readString();
    }

    public static final Creator<StoryItem> CREATOR = new Creator<StoryItem>() {
        @Override
        public StoryItem createFromParcel(Parcel in) {
            return new StoryItem(in);
        }

        @Override
        public StoryItem[] newArray(int size) {
            return new StoryItem[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText_file() {
        return text_file;
    }

    public void setText_file(String text_file) {
        this.text_file = text_file;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(text_file);
        dest.writeString(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}