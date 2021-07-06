package recreate.india.main.startupcarvaan.allmodels.share.sharedetails;

import com.google.firebase.Timestamp;

public class TransactionDetails {
  private Timestamp completed_time;
  private Double price;
  private Integer quantity;
  private Timestamp request_time;
  private String shareid;
  private String status ;
  private String type;
  private String userid;
  private String value;

  public TransactionDetails() {
  }

  public TransactionDetails(Timestamp completed_time, Double price, Integer quantity, Timestamp request_time, String shareid, String status, String type, String userid, String value) {
    this.completed_time = completed_time;
    this.price = price;
    this.quantity = quantity;
    this.request_time = request_time;
    this.shareid = shareid;
    this.status = status;
    this.type = type;
    this.userid = userid;
    this.value = value;
  }

  public Timestamp getCompleted_time() {
    return completed_time;
  }

  public void setCompleted_time(Timestamp completed_time) {
    this.completed_time = completed_time;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Timestamp getRequest_time() {
    return request_time;
  }

  public void setRequest_time(Timestamp request_time) {
    this.request_time = request_time;
  }

  public String getShareid() {
    return shareid;
  }

  public void setShareid(String shareid) {
    this.shareid = shareid;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
