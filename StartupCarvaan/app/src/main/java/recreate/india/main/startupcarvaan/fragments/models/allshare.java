package recreate.india.main.startupcarvaan.fragments.models;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

public class allshare {
    private String desc;
    private Double growth;
    private String introvideourl,logourl,name;
    private List<String> tags;
    private Double users;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public Double getUsers() {
        return users;
    }

    public void setUsers(Double users) {
        this.users = users;
    }

    public allshare() {
    }

    public allshare(String desc, Double growth, String introvideourl, String logourl, String name, List<String> tags, Double users) {
        this.desc = desc;
        this.growth = growth;
        this.introvideourl = introvideourl;
        this.logourl = logourl;
        this.name = name;
        this.tags = tags;
        this.users = users;
    }
}
