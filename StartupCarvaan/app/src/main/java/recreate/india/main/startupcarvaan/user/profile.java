package recreate.india.main.startupcarvaan.user;

public class profile {
    private  Number age;
    private String desciption;
    private String name;
    private  Number phonenumber;
    private  Number points;
    private String rank,resume;

    public profile(Number age, String desciption, String name, Number phonenumber, Number points, String rank, String resume) {
        this.age = age;
        this.desciption = desciption;
        this.name = name;
        this.phonenumber = phonenumber;
        this.points = points;
        this.rank = rank;
        this.resume = resume;
    }

    public profile() {
    }

    public Number getAge() {
        return age;
    }

    public void setAge(Number age) {
        this.age = age;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Number phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Number getPoints() {
        return points;
    }

    public void setPoints(Number points) {
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
}
