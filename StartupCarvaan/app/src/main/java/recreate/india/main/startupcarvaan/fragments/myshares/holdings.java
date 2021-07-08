package recreate.india.main.startupcarvaan.fragments.myshares;

import java.util.HashMap;
import java.util.Map;

public class holdings {
    Map<String,Integer> holdings=new HashMap<>();

    public Map<String, Integer> getHoldings() {
        return holdings;
    }

    public void setHoldings(Map<String, Integer> holdings) {
        this.holdings = holdings;
    }

    public holdings(Map<String, Integer> holdings) {
        this.holdings = holdings;
    }

    public holdings() {
    }
}
