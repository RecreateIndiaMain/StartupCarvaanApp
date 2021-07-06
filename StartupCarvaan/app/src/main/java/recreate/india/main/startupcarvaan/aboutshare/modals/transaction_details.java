package recreate.india.main.startupcarvaan.aboutshare.modals;

import com.google.firebase.Timestamp;

public class transaction_details {
    String startupName,quantity,price;
    boolean bought;
    Timestamp timestamp;

    public transaction_details(String startupName, String quantity, String price, boolean bought, Timestamp timestamp) {
        this.startupName = startupName;
        this.quantity = quantity;
        this.price = price;
        this.bought = bought;
        this.timestamp = timestamp;
    }

    public String getStartupName() {
        return startupName;
    }

    public void setStartupName(String startupName) {
        this.startupName = startupName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
