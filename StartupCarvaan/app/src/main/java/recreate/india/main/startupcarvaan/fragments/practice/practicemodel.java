package recreate.india.main.startupcarvaan.fragments.practice;

import java.util.Map;

public class practicemodel {
    private Map<String,Boolean> currentvotes;
    private String  description;
    private Map<String,Integer>holders;
    private String introvideourl,logourl,name;
    private Integer rate;

    public practicemodel(Map<String, Boolean> currentvotes, String description, Map<String, Integer> holders, String introvideourl, String logourl, String name, Integer rate) {
        this.currentvotes = currentvotes;
        this.description = description;
        this.holders = holders;
        this.introvideourl = introvideourl;
        this.logourl = logourl;
        this.name = name;
        this.rate = rate;
    }

    public Map<String, Boolean> getCurrentvotes() {
        return currentvotes;
    }

    public void setCurrentvotes(Map<String, Boolean> currentvotes) {
        this.currentvotes = currentvotes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Integer> getHolders() {
        return holders;
    }

    public void setHolders(Map<String, Integer> holders) {
        this.holders = holders;
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

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public practicemodel() {
    }
}