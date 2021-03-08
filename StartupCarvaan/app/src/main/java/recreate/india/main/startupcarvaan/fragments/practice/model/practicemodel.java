package recreate.india.main.startupcarvaan.fragments.practice.model;

public class practicemodel {

    private Number currentvotes;
    private String  description,introvideourl,logourl,name;
    private Number neededvotes;

    public Number getCurrentvotes() {
        return currentvotes;
    }

    public void setCurrentvotes(Number currentvotes) {
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

    public Number getNeededvotes() {
        return neededvotes;
    }

    public void setNeededvotes(Number neededvotes) {
        this.neededvotes = neededvotes;
    }

    public practicemodel(Number currentvotes, String description, String introvideourl, String logourl, String name, Number neededvotes) {
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