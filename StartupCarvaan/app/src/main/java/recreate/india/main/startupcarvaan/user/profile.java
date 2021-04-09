package recreate.india.main.startupcarvaan.user;

import java.util.HashMap;
import java.util.Map;

public class profile {
    private String address="your address";
    private String description="your description here";
    private String email="your professional email";
    private String imageurl="/userprofile.png";
    private String name="display name";
    private String phone="your phone number";
    private Double points=0.0;
    private String rank="beginner";
    private String resume="documenturl";
    private String title="your title";

    public profile() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phonenumber) {
        this.phone = phonenumber;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public profile(String address, String description, String email, String imageurl, String name, String phone, Double points, String rank, String resume, String title) {
        this.address = address;
        this.description = description;
        this.email = email;
        this.imageurl = imageurl;
        this.name = name;
        this.phone = phone;
        this.points = points;
        this.rank = rank;
        this.resume = resume;
        this.title = title;
    }

    public Map<String,Object> giveNewUser(){
        Map<String,Object> map=new HashMap<>();
        map.put("address",this.address);
        map.put("description",this.description);
        map.put("email",this.email);
        map.put("imageurl",this.imageurl);
        map.put("name",this.name);
        map.put("points",this.points);
        map.put("rank",this.rank);
        map.put("resume",this.resume);
        map.put("title",this.title);
        map.put("phone",this.phone);
        return  map;
    }
}
