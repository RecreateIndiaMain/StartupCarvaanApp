package recreate.india.main.startupcarvaan.fragments.allshares;

import android.widget.LinearLayout;

import com.google.firebase.Timestamp;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class allshare {
    String advice,description;
    List<Integer>graph;
    Integer growth;
    String introvideourl,logourl,name;
    Timestamp nextslot;
    List<String>tags=new ArrayList<>();
    String type;
    Integer users;


    public allshare() {
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getGraph() {
        return graph;
    }

    public void setGraph(List<Integer> graph) {
        this.graph = graph;
    }

    public Integer getGrowth() {
        return growth;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }

    public String getIntrovideourl() {
        return introvideourl;
    }

    public void setIntrovideourl(String introvideourl) {
        this.introvideourl = introvideourl;
    }

    public String getLogourl() {
        return logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getNextslot() {
        return nextslot;
    }

    public void setNextslot(Timestamp nextslot) {
        this.nextslot = nextslot;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUsers() {
        return users;
    }

    public void setUsers(Integer users) {
        this.users = users;
    }

    public allshare(String advice, String description, List<Integer> graph, Integer growth, String introvideourl, String logourl, String name, Timestamp nextslot, List<String> tags, String type, Integer users) {
        this.advice = advice;
        this.description = description;
        this.graph = graph;
        this.growth = growth;
        this.introvideourl = introvideourl;
        this.logourl = logourl;
        this.name = name;
        this.nextslot = nextslot;
        this.tags = tags;
        this.type = type;
        this.users = users;
    }
}
