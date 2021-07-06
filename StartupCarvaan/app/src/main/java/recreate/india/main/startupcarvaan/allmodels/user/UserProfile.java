package recreate.india.main.startupcarvaan.allmodels.user;

import java.util.HashMap;
import java.util.Map;

public class UserProfile {
    private String addharnumber="your aadhar";
    private String address="your address";
    private Double currentpoints=0.0;
    private String description="your description here";
    private String email="your professional email";
    private String imageurl="/userimage.png";
    private Integer level=1;
    private String phonenumber="your phone number";
    private String title="your title";
    private Double totalpoints=0.0;
    private String username="username";
    private Double rci=0.0;
    private Double bonus=0.0;
    public UserProfile() {
    }

    public String getAddharnumber() {
        return addharnumber;
    }

    public void setAddharnumber(String addharnumber) {
        this.addharnumber = addharnumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getCurrentpoints() {
        return currentpoints;
    }

    public void setCurrentpoints(Double currentpoints) {
        this.currentpoints = currentpoints;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getTotalpoints() {
        return totalpoints;
    }

    public void setTotalpoints(Double totalpoints) {
        this.totalpoints = totalpoints;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getRci() {
        return rci;
    }

    public void setRci(Double rci) {
        this.rci = rci;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public UserProfile(String addharnumber, String address, Double currentpoints, String description, String email, String imageurl, Integer level, String phonenumber, String title, Double totalpoints, String username, Double rci, Double bonus) {
        this.addharnumber = addharnumber;
        this.address = address;
        this.currentpoints = currentpoints;
        this.description = description;
        this.email = email;
        this.imageurl = imageurl;
        this.level = level;
        this.phonenumber = phonenumber;
        this.title = title;
        this.totalpoints = totalpoints;
        this.username = username;
        this.rci = rci;
        this.bonus = bonus;
    }


    public Map<String,Object> giveNewUser(){
        Map<String,Object> map=new HashMap<>();
        map.put("addharnumber",this.addharnumber);
        map.put("address",this.address);
        map.put("currentpoints",this.currentpoints);
        map.put("description",this.description);
        map.put("email",this.email);
        map.put("imageurl",this.imageurl);
        map.put("level",this.level);
        map.put("username",this.username);
        map.put("title",this.title);
        map.put("phonenumber",this.phonenumber);
        map.put("totalpoints",this.totalpoints);
        map.put("rci",this.rci);
        map.put("bonus",this.bonus);
        return  map;
    }
}
