package recreate.india.main.startupcarvaan.allmodels.share.sharedetails;

import java.util.ArrayList;

public class Trading {
    private Double buyingprice;
    private Integer buyvolume;
    private ArrayList<Double> pricelist=new ArrayList<>();
    private Double sellingprice;
    private Integer sellvolume;

    public Trading(Double buyingprice, Integer buyvolume, ArrayList<Double> pricelist, Double sellingprice, Integer sellvolume) {
        this.buyingprice = buyingprice;
        this.buyvolume = buyvolume;
        this.pricelist = pricelist;
        this.sellingprice = sellingprice;
        this.sellvolume = sellvolume;
    }

    public Double getBuyingprice() {
        return buyingprice;
    }

    public void setBuyingprice(Double buyingprice) {
        this.buyingprice = buyingprice;
    }

    public Integer getBuyvolume() {
        return buyvolume;
    }

    public void setBuyvolume(Integer buyvolume) {
        this.buyvolume = buyvolume;
    }

    public ArrayList<Double> getPricelist() {
        return pricelist;
    }

    public void setPricelist(ArrayList<Double> pricelist) {
        this.pricelist = pricelist;
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
