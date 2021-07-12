package recreate.india.main.startupcarvaan.allmodels.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShareHoldings {
    HashMap<String, ArrayList<Double>> holdings=new HashMap<>();  // String is date and at 0 it is quantity and at 1 it is price

    public ShareHoldings(){

    }
    public ShareHoldings(HashMap<String, ArrayList<Double>> holdings) {
        this.holdings = holdings;
    }

    public HashMap<String, ArrayList<Double>> getHoldings() {
        return holdings;
    }

    public void setHoldings(HashMap<String,ArrayList<Double>> holdings) {
        this.holdings = holdings;
    }
}
