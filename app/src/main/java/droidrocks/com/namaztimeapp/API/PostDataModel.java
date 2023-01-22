package droidrocks.com.namaztimeapp.API;

public class PostDataModel {

  private String title;
  private String image_url;
  private String date;
  private String content;

  public PostDataModel(String title, String image_url, String date, String content) {
    this.title = title;
    this.image_url = image_url;
    this.date = date;
    this.content = content;

  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getImage_url() {
    return image_url;
  }

  public void setImage_url(String image_url) {
    this.image_url = image_url;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
