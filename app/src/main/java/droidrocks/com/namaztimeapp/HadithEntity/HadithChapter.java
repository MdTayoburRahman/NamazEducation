package droidrocks.com.namaztimeapp.HadithEntity;

import com.google.gson.annotations.SerializedName;

public class HadithChapter {
    @SerializedName("chapID")
    private long chapID;
    @SerializedName("BookID")
    private int bookID;
    @SerializedName("SectionID")
    private int sectionID;
    @SerializedName("ChapterBG")
    private String chapterBG;
    @SerializedName("ChapterAR")
    private String chapterAR;
    @SerializedName("ChapterEN")
    private String chapterEN;
    @SerializedName("StatusActive")
    private boolean statusActive;

    // Getters and setters

    public HadithChapter() {
    }

    public long getChapID() {
        return chapID;
    }

    public void setChapID(long chapID) {
        this.chapID = chapID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getSectionID() {
        return sectionID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    public String getChapterBG() {
        return chapterBG;
    }

    public void setChapterBG(String chapterBG) {
        this.chapterBG = chapterBG;
    }

    public String getChapterAR() {
        return chapterAR;
    }

    public void setChapterAR(String chapterAR) {
        this.chapterAR = chapterAR;
    }

    public String getChapterEN() {
        return chapterEN;
    }

    public void setChapterEN(String chapterEN) {
        this.chapterEN = chapterEN;
    }

    public boolean getStatusActive() {
        return statusActive;
    }

    public void setStatusActive(boolean statusActive) {
        this.statusActive = statusActive;
    }
}
