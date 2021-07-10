package recreate.india.main.startupcarvaan.allmodels.reward;

import java.util.ArrayList;

public class Reward {
    public Reward(){

    }
    ArrayList<Integer>reward=new ArrayList<>();


    public Reward(ArrayList<Integer> reward) {
        this.reward = reward;
    }

    public ArrayList<Integer> getReward() {
        return reward;
    }

    public void setReward(ArrayList<Integer> reward) {
        this.reward = reward;
    }
}
