package droidrocks.com.namaztimeapp.HadithEntity;

import com.google.gson.annotations.SerializedName;

public class HadithBookMeta {
    @SerializedName("row_id")
    private int rowID;
    @SerializedName("book_id")
    private String bookID;
    @SerializedName("meta_key")
    private String metaKey;
    @SerializedName("meta_data")
    private String metaData;

    // Getters and setters


    public HadithBookMeta() {
    }

    public int getRowID() {
        return rowID;
    }

    public void setRowID(int rowID) {
        this.rowID = rowID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
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
