package recreate.india.main.startupcarvaan.allmodels.user;

import android.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class UserShare {
    private String shareid;
    private Map<Integer, Pair<Integer,Integer>> holdings=new HashMap<>();

    public UserShare() {
    }

    public String getShareid() {
        return shareid;
    }

    public void setShareid(String shareid) {
        this.shareid = shareid;
    }

    public Map<Integer, Pair<Integer, Integer>> getHoldings() {
        return holdings;
    }

    public void setHoldings(Map<Integer, Pair<Integer, Integer>> holdings) {
        this.holdings = holdings;
    }

    public UserShare(String shareid, Map<Integer, Pair<Integer, Integer>> holdings) {
        this.shareid = shareid;
        this.holdings = holdings;
    }
}
