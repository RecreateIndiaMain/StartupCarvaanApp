package recreate.india.main.startupcarvaan.allmodels.share.sharedetails;

public class Selling {
    private Double sellingprice;
    private Integer sellvolume;
    public Selling(){

    }

    public Selling(Double sellingprice, Integer sellvolume) {
        this.sellingprice = sellingprice;
        this.sellvolume = sellvolume;
    }

    public Double getSellingprice() {
        return sellingprice;
    }

    public void setSellingprice(Double sellingprice) {
        this.sellingprice = sellingprice;
    }

    public Integer getSellvolume() {
        return sellvolume;
    }

    public void setSellvolume(Integer sellvolume) {
        this.sellvolume = sellvolume;
    }
}
