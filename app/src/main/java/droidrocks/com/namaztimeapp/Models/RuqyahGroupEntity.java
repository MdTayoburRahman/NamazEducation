package droidrocks.com.namaztimeapp.Models;

import com.google.gson.annotations.SerializedName;

public class RuqyahGroupEntity {

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
}
