package recreate.india.main.startupcarvaan.allmodels.fragmentmodel;

import java.util.HashMap;
import java.util.Map;

public class PractiseModel {
    Map<String,Boolean> Vote=new HashMap<>();
    Map<String,Integer> betting=new HashMap<>();
    String description;
    String logo;
    String name;
    Integer rate;
    String url;
    PractiseModel(){
    }

    public PractiseModel(Map<String, Boolean> vote, Map<String, Integer> betting, String description, String logo, String name, Integer rate, String url) {
        Vote = vote;
        this.betting = betting;
        this.description = description;
        this.logo = logo;
        this.name = name;
        this.rate = rate;
        this.url = url;
    }

    public Map<String, Boolean> getVote() {
        return Vote;
    }

    public void setVote(Map<String, Boolean> vote) {
        Vote = vote;
    }

    public Map<String, Integer> getBetting() {
        return betting;
    }

    public void setBetting(Map<String, Integer> betting) {
        this.betting = betting;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
