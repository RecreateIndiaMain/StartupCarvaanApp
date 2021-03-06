package recreate.india.main.startupcarvaan.allmodels.user;

import com.google.firebase.Timestamp;

public class UserShareTransaction {
    private Boolean status;
    private String type;
    private Double price;
    private Double quantity;
    private Double value;
    private String startupname;
    private String shareid;
    private String userid;
    private Timestamp added;
    private Timestamp completed;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }   

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() { return price; }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getShareid() {
        return shareid;
    }

    public void setShareid(String shareid) {
        this.shareid = shareid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Timestamp getAdded() {
        return added;
    }

    public void setAdded(Timestamp added) {
        this.added = added;
    }

    public Timestamp getCompleted() {
        return completed;
    }

    public void setCompleted(Timestamp completed) {
        this.completed = completed;
    }

    public UserShareTransaction(Boolean status, String type, Double price, Double quantity, Double value, String startupname, String shareid, String userid, Timestamp added, Timestamp completed) {
        this.status = status;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.value = value;
        this.startupname = startupname;
        this.shareid = shareid;
        this.userid = userid;
        this.added = added;
        this.completed = completed;
    }

    public String getStartupname() {
        return startupname;
    }

    public void setStartupname(String startupname) {
        this.startupname = startupname;
    }

    public UserShareTransaction() {
    }
}
