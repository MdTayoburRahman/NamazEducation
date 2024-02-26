package droidrocks.com.namaztimeapp.HadithEntity;

import com.google.gson.annotations.SerializedName;

public class HadithSectionMeta {
    @SerializedName("row_id")
    private int rowID;
    @SerializedName("section_id")
    private String sectionID;
    @SerializedName("meta_key")
    private String metaKey;
    @SerializedName("meta_data")
    private String metaData;

    // Getters and setters


    public HadithSectionMeta() {
    }

    public int getRowID() {
        return rowID;
    }

    public void setRowID(int rowID) {
        this.rowID = rowID;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getMetaKey() {
        return metaKey;
    }

    public void setMetaKey(String metaKey) {
        this.metaKey = metaKey;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }
}
