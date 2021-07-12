package recreate.india.main.startupcarvaan.allmodels.share;

import com.google.firebase.Timestamp;

import java.util.HashMap;
import java.util.Map;

public class BloggingDetails {
    String blogurl;
    Map<String,String> comments=new HashMap<>();
    Timestamp date;
    String description;
    Map<String, Boolean>likes=new HashMap<>();
    String title;
    String type;


    public String getBlogurl() {
        return blogurl;
    }

    public void setBlogurl(String blogurl) {
        this.blogurl = blogurl;
    }

    public Map<String, String> getComments() {
        return comments;
    }

    public void setComments(Map<String, String> comments) {
        this.comments = comments;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Boolean> getLikes() {
        return likes;
    }

    public void setLikes(Map<String, Boolean> likes) {
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

  public BloggingDetails(){

  }

    public BloggingDetails(String blogurl, Map<String, String> comments, Timestamp date, String description, Map<String, Boolean> likes, String title, String type) {
        this.blogurl = blogurl;
        this.comments = comments;
        this.date = date;
        this.description = description;
        this.likes = likes;
        this.title = title;
        this.type = type;
    }
}
