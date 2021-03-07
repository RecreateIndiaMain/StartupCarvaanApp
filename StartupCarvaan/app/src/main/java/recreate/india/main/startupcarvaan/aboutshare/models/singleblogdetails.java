package recreate.india.main.startupcarvaan.aboutshare.models;

import android.media.audiofx.AudioEffect;
import android.text.BoringLayout;

import com.google.firebase.Timestamp;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.Destroyable;

public class singleblogdetails {
    private String blogurl;
    private Map<String,String>comments=new HashMap<>();
    private Timestamp date;
    private String decription;
    private Map<String,Boolean>likes=new HashMap<>();
    private String title,type;

    public singleblogdetails(String blogurl, Map<String, String> comments, Timestamp date, String decription, Map<String, Boolean> likes, String title, String type) {
        this.blogurl = blogurl;
        this.comments = comments;
        this.date = date;
        this.decription = decription;
        this.likes = likes;
        this.title = title;
        this.type = type;
    }

    public singleblogdetails() {
    }

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

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
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
}
