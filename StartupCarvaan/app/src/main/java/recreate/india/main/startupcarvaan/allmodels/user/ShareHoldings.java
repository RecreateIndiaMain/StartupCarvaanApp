package recreate.india.main.startupcarvaan.allmodels.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShareHoldings {
    Map<String,ArrayList<Double>> holdings=new HashMap<>();  // String is date and at 0 it is quantity and at 1 it is price
    // here object is Map<String,ArrayList<Double>>

    // ab ek baar update share dekh
    public ShareHoldings(){

    }

    public Map<String,ArrayList<Double>> getHoldings() {
        return holdings;
    }

    public void setHoldings(Map<String,ArrayList<Double>> holdings) {
        this.holdings = holdings;
    }

    public ShareHoldings(Map<String,ArrayList<Double>> holdings) {
        this.holdings = holdings;
    }
}
