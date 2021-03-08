package recreate.india.main.startupcarvaan.user;

import java.util.HashMap;
import java.util.Map;

public class shareholdings {

    private Map<Number,Number>holdings=new HashMap<>();
    private Map<Number,Number>sold=new HashMap<>();
    private Number totalinvested,totalprofit;

    public Map<Number, Number> getHoldings() {
        return holdings;
    }

    public void setHoldings(Map<Number, Number> holdings) {
        this.holdings = holdings;
    }

    public Map<Number, Number> getSold() {
        return sold;
    }

    public void setSold(Map<Number, Number> sold) {
        this.sold = sold;
    }

    public Number getTotalinvested() {
        return totalinvested;
    }

    public void setTotalinvested(Number totalinvested) {
        this.totalinvested = totalinvested;
    }

    public Number getTotalprofit() {
        return totalprofit;
    }

    public void setTotalprofit(Number totalprofit) {
        this.totalprofit = totalprofit;
    }

    public shareholdings(Map<Number, Number> holdings, Map<Number, Number> sold, Number totalinvested, Number totalprofit) {
        this.holdings = holdings;
        this.sold = sold;
        this.totalinvested = totalinvested;
        this.totalprofit = totalprofit;
    }

    public shareholdings() {
    }
}
