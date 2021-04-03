package recreate.india.main.startupcarvaan.fragments.models;

public class practicemodel {

    private Double currentvotes;
    private String  description,introvideourl,logourl,name;
    private Double neededvotes;

    public Double getCurrentvotes() {
        return currentvotes;
    }

    public void setCurrentvotes(Double currentvotes) {
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

    public Double getNeededvotes() {
        return neededvotes;
    }

    public void setNeededvotes(Double neededvotes) {
        this.neededvotes = neededvotes;
    }

    public practicemodel(Double currentvotes, String description, String introvideourl, String logourl, String name, Double neededvotes) {
        this.currentvotes = currentvotes;
        this.description = description;
        this.introvideourl = introvideourl;
        this.logourl = logourl;
        this.name = name;
        this.neededvotes = neededvotes;
    }

    public practicemodel() {
    }
}