package recreate.india.main.startupcarvaan.allmodels.reward;

import java.util.ArrayList;

public class Level {
    ArrayList<Integer> level=new ArrayList<>();
    public Level(){

    }

    public Level(ArrayList<Integer> level) {
        this.level = level;
    }

    public ArrayList<Integer> getLevel() {
        return level;
    }

    public void setLevel(ArrayList<Integer> level) {
        this.level = level;
    }
}
