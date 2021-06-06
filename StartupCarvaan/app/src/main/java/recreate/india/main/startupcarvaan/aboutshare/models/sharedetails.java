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
    private String reason;
    private Integer sellingprice;
    private Integer totalsharesatusers;

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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getSellingprice() {
        return sellingprice;
    }

    public void setSellingprice(Integer sellingprice) {
        this.sellingprice = sellingprice;
    }

    public Integer getTotalsharesatusers() {
        return totalsharesatusers;
    }

    public void setTotalsharesatusers(Integer totalsharesatusers) {
        this.totalsharesatusers = totalsharesatusers;
    }

    public sharedetails(Integer availableforbuying, Integer availableforselling, Integer buyingprice, String reason, Integer sellingprice, Integer totalsharesatusers) {
        this.availableforbuying = availableforbuying;
        this.availableforselling = availableforselling;
        this.buyingprice = buyingprice;
        this.reason = reason;
        this.sellingprice = sellingprice;
        this.totalsharesatusers = totalsharesatusers;
    }

    public sharedetails() {
    }
}
