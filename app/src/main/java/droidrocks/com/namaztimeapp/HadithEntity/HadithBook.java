package droidrocks.com.namaztimeapp.HadithEntity;

import com.google.gson.annotations.SerializedName;

public class HadithBook {
    @SerializedName("BookID")
    private int bookID;
    @SerializedName("PubID")
    private int pubID;
    @SerializedName("BookNameBD")
    private String bookNameBD;
    @SerializedName("BookNameEN")
    private String bookNameEN;
    @SerializedName("picture")
    private String picture;
    @SerializedName("priority")
    private int priority;
    @SerializedName("Active")
    private int active;

    // Getters and setters


    public HadithBook() {
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getPubID() {
        return pubID;
    }

    public void setPubID(int pubID) {
        this.pubID = pubID;
    }

    public String getBookNameBD() {
        return bookNameBD;
    }

    public void setBookNameBD(String bookNameBD) {
        this.bookNameBD = bookNameBD;
    }

    public String getBookNameEN() {
        return bookNameEN;
    }

    public void setBookNameEN(String bookNameEN) {
        this.bookNameEN = bookNameEN;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
