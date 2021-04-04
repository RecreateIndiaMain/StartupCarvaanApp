package recreate.india.main.startupcarvaan.aboutshare.models;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;
import java.util.PrimitiveIterator;

public class sharedetails {
    private Double availableforbuying,availableforselling,buyingprice;
    private String buyingtips;
    private Boolean increased;
    private Map<String,String> price;

    public sharedetails() {
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

    public Double getSellingprice() {
        return sellingprice;
    }

    public void setSellingprice(Double sellingprice) {
        this.sellingprice = sellingprice;
    }

    public String getSellingtips() {
        return sellingtips;
    }

    public void setSellingtips(String sellingtips) {
        this.sellingtips = sellingtips;
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

    public sharedetails(Double availableforbuying, Double availableforselling, Double buyingprice, String buyingtips, Boolean increased, Map<String, String> price, Double sellingprice, String sellingtips, Double totalsharesatusers, Double totalsharesonmarket) {
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

    private Double sellingprice;
    private String sellingtips;
    private Double totalsharesatusers,totalsharesonmarket;
}
