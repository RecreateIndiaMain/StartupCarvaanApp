package recreate.india.main.startupcarvaan.fragments.models;

import java.util.HashMap;
import java.util.Map;

public class holdings {
    Map<String,Double> holdings=new HashMap<>();

    public Map<String, Double> getHoldings() {
        return holdings;
    }

    public void setHoldings(Map<String, Double> holdings) {
        this.holdings = holdings;
    }

    public holdings() {
    }

    public holdings(Map<String, Double> holdings) {
        this.holdings = holdings;
    }
}
