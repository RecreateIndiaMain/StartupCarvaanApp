package recreate.india.main.startupcarvaan.user;

import java.util.HashMap;
import java.util.Map;

public class holdings {
    private Map<Number,Number> holdings=new HashMap<>();

    public Map<Number, Number> getHoldings() {
        return holdings;
    }

    public void setHoldings(Map<Number, Number> holdings) {
        this.holdings = holdings;
    }

    public holdings(Map<Number, Number> holdings) {
        this.holdings = holdings;
    }

    public holdings() {
    }
}
