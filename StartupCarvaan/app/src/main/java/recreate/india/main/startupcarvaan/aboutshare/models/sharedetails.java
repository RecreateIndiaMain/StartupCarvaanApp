package recreate.india.main.startupcarvaan.aboutshare.models;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;
import java.util.PrimitiveIterator;

public class sharedetails {
    private Integer availableforbuying,availableforselling,buyingprice;
    private String buyingtips;
    private Boolean increased;
    private Map<String,String> price;

    public sharedetails() {
    }

    public Integer getAvailableforbuying() {
        return availableforbuying;
    }

    public void setAvailableforbuying(Integer availableforbuying) {
        this.availableforbuying = availableforbuying;
    }

    public Integer getAvailableforselling() {
        return availableforselling;
    }

    public void setAvailableforselling(Integer availableforselling) {
        this.availableforselling = availableforselling;
    }

    public Integer getBuyingprice() {
        return buyingprice;
    }

    public void setBuyingprice(Integer buyingprice) {
        this.buyingprice = buyingprice;
    }

    public String getBuyingtips() {
        return buyingtips;
    }

    public void setBuyingtips(String buyingtips) {
        this.buyingtips = buyingtips;
    }

    public Boolean getIncreased() {
        return increased;
    }

    public void setIncreased(Boolean increased) {
        this.increased = increased;
    }

    public Map<String, String> getPrice() {
        return price;
    }

    public void setPrice(Map<String, String> price) {
        this.price = price;
    }

    public Integer getSellingprice() {
        return sellingprice;
    }

    public void setSellingprice(Integer sellingprice) {
        this.sellingprice = sellingprice;
    }

    public String getSellingtips() {
        return sellingtips;
    }

    public void setSellingtips(String sellingtips) {
        this.sellingtips = sellingtips;
    }

    public Integer getTotalsharesatusers() {
        return totalsharesatusers;
    }

    public void setTotalsharesatusers(Integer totalsharesatusers) {
        this.totalsharesatusers = totalsharesatusers;
    }

    public Integer getTotalsharesonmarket() {
        return totalsharesonmarket;
    }

    public void setTotalsharesonmarket(Integer totalsharesonmarket) {
        this.totalsharesonmarket = totalsharesonmarket;
    }

    public sharedetails(Integer availableforbuying, Integer availableforselling, Integer buyingprice, String buyingtips, Boolean increased, Map<String, String> price, Integer sellingprice, String sellingtips, Integer totalsharesatusers, Integer totalsharesonmarket) {
        this.availableforbuying = availableforbuying;
        this.availableforselling = availableforselling;
        this.buyingprice = buyingprice;
        this.buyingtips = buyingtips;
        this.increased = increased;
        this.price = price;
        this.sellingprice = sellingprice;
        this.sellingtips = sellingtips;
        this.totalsharesatusers = totalsharesatusers;
        this.totalsharesonmarket = totalsharesonmarket;
    }

    private Integer sellingprice;
    private String sellingtips;
    private Integer totalsharesatusers,totalsharesonmarket;
}
