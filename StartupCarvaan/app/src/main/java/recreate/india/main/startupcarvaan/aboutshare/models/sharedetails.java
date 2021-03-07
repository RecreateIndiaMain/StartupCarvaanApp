package recreate.india.main.startupcarvaan.aboutshare.models;

public class sharedetails {
    private Double availableforbuying,
            availableforselling,
            buyingprice,
            sellingprice,
            totalsharesatusers,
            totalsharesonmarket;

    public sharedetails() {
    }

    public sharedetails(Double availableforbuying, Double availableforselling, Double buyingprice, Double sellingprice, Double totalsharesatusers, Double totalsharesonmarket) {
        this.availableforbuying = availableforbuying;
        this.availableforselling = availableforselling;
        this.buyingprice = buyingprice;
        this.sellingprice = sellingprice;
        this.totalsharesatusers = totalsharesatusers;
        this.totalsharesonmarket = totalsharesonmarket;
    }

    public Double getAvailableforbuying() {
        return availableforbuying;
    }

    public void setAvailableforbuying(Double availableforbuying) {
        this.availableforbuying = availableforbuying;
    }

    public Double getAvailableforselling() {
        return availableforselling;
    }

    public void setAvailableforselling(Double availableforselling) {
        this.availableforselling = availableforselling;
    }

    public Double getBuyingprice() {
        return buyingprice;
    }

    public void setBuyingprice(Double buyingprice) {
        this.buyingprice = buyingprice;
    }

    public Double getSellingprice() {
        return sellingprice;
    }

    public void setSellingprice(Double sellingprice) {
        this.sellingprice = sellingprice;
    }

    public Double getTotalsharesatusers() {
        return totalsharesatusers;
    }

    public void setTotalsharesatusers(Double totalsharesatusers) {
        this.totalsharesatusers = totalsharesatusers;
    }

    public Double getTotalsharesonmarket() {
        return totalsharesonmarket;
    }

    public void setTotalsharesonmarket(Double totalsharesonmarket) {
        this.totalsharesonmarket = totalsharesonmarket;
    }
}
