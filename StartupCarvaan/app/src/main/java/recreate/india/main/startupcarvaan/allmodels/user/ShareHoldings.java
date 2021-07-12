package recreate.india.main.startupcarvaan.allmodels.user;

import java.util.HashMap;

public class ShareHoldings {
    HashMap<String,Double[]> holdings=new HashMap<>();  // String is date and at 0 it is quantity and at 1 it is price

    public ShareHoldings(){

    }
    public ShareHoldings(HashMap<String, Double[]> holdings) {
        this.holdings = holdings;
    }

    public HashMap<String, Double[]> getHoldings() {
        return holdings;
    }

    public void setHoldings(HashMap<String,Double[]> holdings) {
        this.holdings = holdings;
    }
}
