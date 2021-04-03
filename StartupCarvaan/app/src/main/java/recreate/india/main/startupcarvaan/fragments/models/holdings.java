package recreate.india.main.startupcarvaan.fragments.models;

import java.util.HashMap;
import java.util.Map;

public class holdings {
    Map<String,Double> share=new HashMap<>();

    public Map<String, Double> getShare() {
        return share;
    }

    public void setShare(Map<String, Double> share) {
        this.share = share;
    }

    public holdings() {
    }

    public holdings(Map<String, Double> share) {
        this.share = share;
    }
}
