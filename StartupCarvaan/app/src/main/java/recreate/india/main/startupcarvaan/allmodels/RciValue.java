package recreate.india.main.startupcarvaan.allmodels;

import java.util.ArrayList;

public class RciValue {
    Double currentvalue;
    String description;
    ArrayList<Double> graph;

    public RciValue(){
    }
    public RciValue(Double currentvalue, String description, ArrayList<Double> graph) {
        this.currentvalue = currentvalue;
        this.description = description;
        this.graph = graph;
    }

    public Double getCurrentvalue() {
        return currentvalue;
    }

    public void setCurrentvalue(Double currentvalue) {
        this.currentvalue = currentvalue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Double> getGraph() {
        return graph;
    }

    public void setGraph(ArrayList<Double> graph) {
        this.graph = graph;
    }
}
