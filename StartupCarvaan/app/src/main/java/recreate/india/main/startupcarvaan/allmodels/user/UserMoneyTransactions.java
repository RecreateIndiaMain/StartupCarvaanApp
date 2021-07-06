package recreate.india.main.startupcarvaan.allmodels.user;

import java.util.Date;

public class UserMoneyTransactions {
    private String userid;
    private Double amount;
    private Date date;
    private String paymentmethod;// upi,account
    private String destination; // upi id, account number
    private String type;

    public UserMoneyTransactions() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserMoneyTransactions(String userid, Double amount, Date date, String paymentmethod, String destination, String type) {
        this.userid = userid;
        this.amount = amount;
        this.date = date;
        this.paymentmethod = paymentmethod;
        this.destination = destination;
        this.type = type;
    }
}
