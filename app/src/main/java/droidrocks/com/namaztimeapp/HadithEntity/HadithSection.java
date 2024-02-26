package droidrocks.com.namaztimeapp.HadithEntity;

import com.google.gson.annotations.SerializedName;

public class HadithSection {
    @SerializedName("SectionID")
    private int sectionID;
    @SerializedName("BookID")
    private int bookID;
    @SerializedName("SectionBD")
    private String sectionBD;
    @SerializedName("SectionEN")
    private String sectionEN;
    @SerializedName("SecActive")
    private boolean secActive;

    public HadithSection() {
    }

    // Getters and setters

    public int getSectionID() {
        return sectionID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getSectionBD() {
        return sectionBD;
    }

    public void setSectionBD(String sectionBD) {
        this.sectionBD = sectionBD;
    }

    public String getSectionEN() {
        return sectionEN;
    }

    public void setSectionEN(String sectionEN) {
        this.sectionEN = sectionEN;
    }

    public boolean isSecActive() {
        return secActive;
    }

    public void setSecActive(boolean secActive) {
        this.secActive = secActive;
    }
}
