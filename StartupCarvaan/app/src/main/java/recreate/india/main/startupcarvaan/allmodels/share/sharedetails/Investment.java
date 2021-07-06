package recreate.india.main.startupcarvaan.allmodels.share.sharedetails;

public class Investment {

    private Double collectedinvestment,sellingprice,totalinvestment;
    public Investment(){

    }

    public Investment(Double collectedinvestment, Double sellingprice, Double totalinvestment) {
        this.collectedinvestment = collectedinvestment;
        this.sellingprice = sellingprice;
        this.totalinvestment = totalinvestment;
    }

    public Double getCollectedinvestment() {
        return collectedinvestment;
    }

    public void setCollectedinvestment(Double collectedinvestment) {
        this.collectedinvestment = collectedinvestment;
    }

    public Double getSellingprice() {
        return sellingprice;
    }

    public void setSellingprice(Double sellingprice) {
        this.sellingprice = sellingprice;
    }

    public Double getTotalinvestment() {
        return totalinvestment;
    }

    public void setTotalinvestment(Double totalinvestment) {
        this.totalinvestment = totalinvestment;
    }
}
