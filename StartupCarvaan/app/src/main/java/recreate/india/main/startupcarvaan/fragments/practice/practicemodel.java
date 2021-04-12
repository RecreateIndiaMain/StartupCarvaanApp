package recreate.india.main.startupcarvaan.fragments.practice;

import java.util.Map;

public class practicemodel {
    private Integer buyingprice;
    private Integer currentvotes;
    private String  description;
    private Map<String,String>holdings;
    private String introvideourl,logourl,name;
    private Integer neededvotes;

    public Integer getCurrentvotes() {
        return currentvotes;
    }

    public void setCurrentvotes(Integer currentvotes) {
        this.currentvotes = currentvotes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getHoldings() {
        return holdings;
    }

    public void setHoldings(Map<String, String> holdings) {
        this.holdings = holdings;
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

    public Integer getNeededvotes() {
        return neededvotes;
    }

    public void setNeededvotes(Integer neededvotes) {
        this.neededvotes = neededvotes;
    }

    public Integer getBuyingprice() {
        return buyingprice;
    }

    public void setBuyingprice(Integer buyingprice) {
        this.buyingprice = buyingprice;
    }

    public practicemodel(Integer buyingprice, Integer currentvotes, String description, Map<String, String> holdings, String introvideourl, String logourl, String name, Integer neededvotes) {
        this.buyingprice = buyingprice;
        this.currentvotes = currentvotes;
        this.description = description;
        this.holdings = holdings;
        this.introvideourl = introvideourl;
        this.logourl = logourl;
        this.name = name;
        this.neededvotes = neededvotes;
    }

    public practicemodel() {
    }
}