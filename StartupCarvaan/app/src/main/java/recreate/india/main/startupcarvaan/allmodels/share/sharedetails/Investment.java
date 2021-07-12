package recreate.india.main.startupcarvaan.allmodels.share.sharedetails;

public class Investment {

    private Double collectedinvestment,buyingprice,totalinvestment;
    public Investment(){

    }

    public Investment(Double collectedinvestment, Double sellingprice, Double totalinvestment) {
        this.collectedinvestment = collectedinvestment;
        this.buyingprice = sellingprice;
        this.totalinvestment = totalinvestment;
    }

    public Double getCollectedinvestment() {
        return collectedinvestment;
    }

    public void setCollectedinvestment(Double collectedinvestment) {
        this.collectedinvestment = collectedinvestment;
    }

    public Double getBuyingprice() {
        return buyingprice;
    }

    public void setBuyingprice(Double sellingprice) {
        this.buyingprice = sellingprice;
    }

    public Double getTotalinvestment() {
        return totalinvestment;
    }

    public void setTotalinvestment(Double totalinvestment) {
        this.totalinvestment = totalinvestment;
    }
}
