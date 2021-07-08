package recreate.india.main.startupcarvaan.allmodels.share;

import com.google.firebase.Timestamp;

import java.util.ArrayList;

public class Share {
    private String description;
    private Integer growth;
    private Integer investors;
    private String logourl;
    private String name;
    private boolean performance;
    private String pitchurl;
    private Timestamp timestamp;
    private ArrayList<String>tags=new ArrayList<>();
    private String beginner;

    public Share() {
    }


    public Share(String description,Integer growth, Integer investors, String logourl, String name, boolean performance, String pitchurl, Timestamp timestamp, ArrayList<String> tags, String beginner) {
        this.description=description;
        this.growth = growth;
        this.investors = investors;
        this.logourl = logourl;
        this.name = name;
        this.performance = performance;
        this.pitchurl = pitchurl;
        this.timestamp = timestamp;
        this.tags = tags;
        this.beginner = beginner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGrowth() {
        return growth;
    }

    public void setGrowth(Integer growth) {
        this.growth = growth;
    }

    public Integer getInvestors() {
        return investors;
    }

    public void setInvestors(Integer investors) {
        this.investors = investors;
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

    public boolean isPerformance() {
        return performance;
    }

    public void setPerformance(boolean performance) {
        this.performance = performance;
    }

    public String getPitchurl() {
        return pitchurl;
    }

    public void setPitchurl(String pitchurl) {
        this.pitchurl = pitchurl;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getBeginner() {
        return beginner;
    }

    public void setBeginner(String beginner) {
        this.beginner = beginner;
    }
}
