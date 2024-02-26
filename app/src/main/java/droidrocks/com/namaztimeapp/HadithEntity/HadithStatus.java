package droidrocks.com.namaztimeapp.HadithEntity;

import com.google.gson.annotations.SerializedName;

public class HadithStatus {
    @SerializedName("StatusID")
    private int statusID;
    @SerializedName("StatusBG")
    private String statusBG;
    @SerializedName("StatusEN")
    private String statusEN;
    @SerializedName("ColCode")
    private String colCode;
    @SerializedName("Active")
    private boolean active;

    // Getters and setters


    public HadithStatus() {
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getStatusBG() {
        return statusBG;
    }

    public void setStatusBG(String statusBG) {
        this.statusBG = statusBG;
    }

    public String getStatusEN() {
        return statusEN;
    }

    public void setStatusEN(String statusEN) {
        this.statusEN = statusEN;
    }

    public String getColCode() {
        return colCode;
    }

    public void setColCode(String colCode) {
        this.colCode = colCode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
