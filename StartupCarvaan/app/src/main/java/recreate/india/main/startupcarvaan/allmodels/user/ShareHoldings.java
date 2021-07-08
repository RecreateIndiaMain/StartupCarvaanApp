package recreate.india.main.startupcarvaan.allmodels.user;

import java.util.HashMap;

public class ShareHoldings {
    HashMap<String,Double[]> holdings=new HashMap<>();

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
