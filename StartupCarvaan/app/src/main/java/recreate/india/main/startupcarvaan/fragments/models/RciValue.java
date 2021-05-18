package recreate.india.main.startupcarvaan.fragments.models;

import java.util.HashMap;
public class RciValue {
    private double currentvalue;
    private String description;
    private HashMap<String, Double> graph=new HashMap<>();

    public RciValue() {}

    public double getCurrentvalue() {
        return currentvalue;
    }

    public void setCurrentvalue(double currentvalue) {
        this.currentvalue = currentvalue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String, Double> getGraph() {
        return graph;
    }

    public void setGraph(HashMap<String, Double> graph) {
        this.graph = graph;
    }

    public RciValue(double currentvalue, String description, HashMap<String, Double> graph) {
        this.currentvalue = currentvalue;
        this.description = description;
        this.graph = graph;
    }
}
