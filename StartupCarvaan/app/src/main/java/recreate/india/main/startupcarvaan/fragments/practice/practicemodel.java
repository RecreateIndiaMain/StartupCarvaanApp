package recreate.india.main.startupcarvaan.fragments.practice;

public class practicemodel {
    private Integer buyingprice;
    private Integer currentvotes;
    private String  description,introvideourl,logourl,name;
    private Integer neededvotes;
    private Integer sellingprice;

    public Integer getBuyingprice() {
        return buyingprice;
    }

    public void setBuyingprice(Integer buyingprice) {
        this.buyingprice = buyingprice;
    }

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

    public Integer getSellingprice() {
        return sellingprice;
    }

    public void setSellingprice(Integer sellingprice) {
        this.sellingprice = sellingprice;
    }

    public practicemodel(Integer buyingprice, Integer currentvotes, String description, String introvideourl, String logourl, String name, Integer neededvotes, Integer sellingprice) {
        this.buyingprice = buyingprice;
        this.currentvotes = currentvotes;
        this.description = description;
        this.introvideourl = introvideourl;
        this.logourl = logourl;
        this.name = name;
        this.neededvotes = neededvotes;
        this.sellingprice = sellingprice;
    }

    public practicemodel() {
    }
}