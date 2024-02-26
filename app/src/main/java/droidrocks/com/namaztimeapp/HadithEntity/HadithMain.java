package droidrocks.com.namaztimeapp.HadithEntity;

import com.google.gson.annotations.SerializedName;

public class HadithMain {
    @SerializedName("HadithID")
    private long hadithID;
    @SerializedName("DateInsert")
    private String dateInsert;
    @SerializedName("DateUpdate")
    private String dateUpdate;
    @SerializedName("EntryBy")
    private int entryBy;
    @SerializedName("UpdateBy")
    private int updateBy;
    @SerializedName("CheckStatus")
    private int checkStatus;
    @SerializedName("RabiID")
    private int rabiID;
    @SerializedName("BookID")
    private int bookID;
    @SerializedName("SourceID")
    private int sourceID;
    @SerializedName("chapterID")
    private int chapterID;
    @SerializedName("PartID")
    private int partID;
    @SerializedName("SectionID")
    private int sectionID;
    @SerializedName("HadithNo")
    private int hadithNo;
    @SerializedName("ArabicHadith")
    private String arabicHadith;
    @SerializedName("BanglaHadith")
    private String banglaHadith;
    @SerializedName("EnglishHadith")
    private String englishHadith;
    @SerializedName("HadithImage")
    private String hadithImage;
    @SerializedName("HadithNote")
    private String hadithNote;
    @SerializedName("HadithActive")
    private int hadithActive;
    @SerializedName("HadithStatus")
    private int hadithStatus;
    @SerializedName("hadithTag")
    private String hadithTag;

    // Getters and setters

    public HadithMain() {
    }

    public long getHadithID() {
        return hadithID;
    }

    public void setHadithID(long hadithID) {
        this.hadithID = hadithID;
    }

    public String getDateInsert() {
        return dateInsert;
    }

    public void setDateInsert(String dateInsert) {
        this.dateInsert = dateInsert;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public int getEntryBy() {
        return entryBy;
    }

    public void setEntryBy(int entryBy) {
        this.entryBy = entryBy;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }

    public int getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }

    public int getRabiID() {
        return rabiID;
    }

    public void setRabiID(int rabiID) {
        this.rabiID = rabiID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getSourceID() {
        return sourceID;
    }

    public void setSourceID(int sourceID) {
        this.sourceID = sourceID;
    }

    public int getChapterID() {
        return chapterID;
    }

    public void setChapterID(int chapterID) {
        this.chapterID = chapterID;
    }

    public int getPartID() {
        return partID;
    }

    public void setPartID(int partID) {
        this.partID = partID;
    }

    public int getSectionID() {
        return sectionID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    public int getHadithNo() {
        return hadithNo;
    }

    public void setHadithNo(int hadithNo) {
        this.hadithNo = hadithNo;
    }

    public String getArabicHadith() {
        return arabicHadith;
    }

    public void setArabicHadith(String arabicHadith) {
        this.arabicHadith = arabicHadith;
    }

    public String getBanglaHadith() {
        return banglaHadith;
    }

    public void setBanglaHadith(String banglaHadith) {
        this.banglaHadith = banglaHadith;
    }

    public String getEnglishHadith() {
        return englishHadith;
    }

    public void setEnglishHadith(String englishHadith) {
        this.englishHadith = englishHadith;
    }

    public String getHadithImage() {
        return hadithImage;
    }

    public void setHadithImage(String hadithImage) {
        this.hadithImage = hadithImage;
    }

    public String getHadithNote() {
        return hadithNote;
    }

    public void setHadithNote(String hadithNote) {
        this.hadithNote = hadithNote;
    }

    public int getHadithActive() {
        return hadithActive;
    }

    public void setHadithActive(int hadithActive) {
        this.hadithActive = hadithActive;
    }

    public int getHadithStatus() {
        return hadithStatus;
    }

    public void setHadithStatus(int hadithStatus) {
        this.hadithStatus = hadithStatus;
    }

    public String getHadithTag() {
        return hadithTag;
    }

    public void setHadithTag(String hadithTag) {
        this.hadithTag = hadithTag;
    }
}
