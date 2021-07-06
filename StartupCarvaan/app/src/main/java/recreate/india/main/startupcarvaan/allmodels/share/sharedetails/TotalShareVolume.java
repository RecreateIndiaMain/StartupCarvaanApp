package recreate.india.main.startupcarvaan.allmodels.share.sharedetails;

public class TotalShareVolume {

    private Double amountcollected;
    private Integer numberofshares;

    public TotalShareVolume(){

    }

    public TotalShareVolume(Double amountcollected, Integer numberofshares) {
        this.amountcollected = amountcollected;
        this.numberofshares = numberofshares;
    }

    public Double getAmountcollected() {
        return amountcollected;
    }

    public void setAmountcollected(Double amountcollected) {
        this.amountcollected = amountcollected;
    }

    public Integer getNumberofshares() {
        return numberofshares;
    }

    public void setNumberofshares(Integer numberofshares) {
        this.numberofshares = numberofshares;
    }
}
