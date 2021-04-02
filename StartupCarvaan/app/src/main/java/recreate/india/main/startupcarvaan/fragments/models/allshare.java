package recreate.india.main.startupcarvaan.fragments.models;

import com.google.firebase.Timestamp;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class allshare {
    String advice,description;
    Map<String, Timestamp> graph;
    Double growth;
    String introvideourl,logourl,name;
    List<String>tags=new ArrayList<>();
    String type;
    Double users;


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

    public Map<String, Timestamp> getGraph() {
        return graph;
    }

    public void setGraph(Map<String, Timestamp> graph) {
        this.graph = graph;
    }

    public Double getGrowth() {
        return growth;
    }

    public void setGrowth(Double growth) {
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public allshare(String advice, String description, Map<String, Timestamp> graph, Double growth, String introvideourl, String logourl, String name, List<String> tags, String type, Double users) {
        this.advice = advice;
        this.description = description;
        this.graph = graph;
        this.growth = growth;
        this.introvideourl = introvideourl;
        this.logourl = logourl;
        this.name = name;
        this.tags = tags;
        this.type = type;
        this.users = users;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getUsers() {
        return users;
    }

    public void setUsers(Double users) {
        this.users = users;
    }
}
