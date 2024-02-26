package droidrocks.com.namaztimeapp.HadithEntity;

import com.google.gson.annotations.SerializedName;

public class HadithSource {
    @SerializedName("SourceID")
    private int sourceID;
    @SerializedName("SourceNameBD")
    private String sourceNameBD;
    @SerializedName("SourceNameEN")
    private String sourceNameEN;
    @SerializedName("SourceActive")
    private boolean sourceActive;

    // Getters and setters


    public HadithSource() {
    }

    public int getSourceID() {
        return sourceID;
    }

    public void setSourceID(int sourceID) {
        this.sourceID = sourceID;
    }

    public String getSourceNameBD() {
        return sourceNameBD;
    }

    public void setSourceNameBD(String sourceNameBD) {
        this.sourceNameBD = sourceNameBD;
    }

    public String getSourceNameEN() {
        return sourceNameEN;
    }

    public void setSourceNameEN(String sourceNameEN) {
        this.sourceNameEN = sourceNameEN;
    }

    public boolean isSourceActive() {
        return sourceActive;
    }

    public void setSourceActive(boolean sourceActive) {
        this.sourceActive = sourceActive;
    }
}
