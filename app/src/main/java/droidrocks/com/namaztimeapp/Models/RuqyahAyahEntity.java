package droidrocks.com.namaztimeapp.Models;

import com.google.gson.annotations.SerializedName;

public class RuqyahAyahEntity {

    @SerializedName("audiopath")
    private String audiopath;

    @SerializedName("ayah_arabic")
    private String ayah_arabic;

    @SerializedName("ayah_bangla")
    private String ayah_bangla;

    @SerializedName("ayah_note")
    private String ayah_note;

    @SerializedName("ayah_number")
    private int ayah_number;

    @SerializedName("ayah_title")
    private String ayah_title;

    @SerializedName("group_id")
    private int group_id;

    @SerializedName("id")
    private int id;

    public RuqyahAyahEntity() {
    }



    public String getAudiopath() {
        return audiopath;
    }

    public void setAudiopath(String audiopath) {
        this.audiopath = audiopath;
    }

    public String getAyah_arabic() {
        return ayah_arabic;
    }

    public void setAyah_arabic(String ayah_arabic) {
        this.ayah_arabic = ayah_arabic;
    }

    public String getAyah_bangla() {
        return ayah_bangla;
    }

    public void setAyah_bangla(String ayah_bangla) {
        this.ayah_bangla = ayah_bangla;
    }

    public String getAyah_note() {
        return ayah_note;
    }

    public void setAyah_note(String ayah_note) {
        this.ayah_note = ayah_note;
    }

    public int getAyah_number() {
        return ayah_number;
    }

    public void setAyah_number(int ayah_number) {
        this.ayah_number = ayah_number;
    }

    public String getAyah_title() {
        return ayah_title;
    }

    public void setAyah_title(String ayah_title) {
        this.ayah_title = ayah_title;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
