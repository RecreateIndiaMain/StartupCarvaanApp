package recreate.india.main.startupcarvaan.aboutshare.models;

import java.util.List;

public class work {
    private Boolean freelance;
    private List<String> freelanceapply;
    private Boolean intern;
    private List<String> internapply;

    public Boolean getFreelance() {
        return freelance;
    }

    public void setFreelance(Boolean freelance) {
        this.freelance = freelance;
    }

    public List<String> getFreelanceapply() {
        return freelanceapply;
    }

    public void setFreelanceapply(List<String> freelanceapply) {
        this.freelanceapply = freelanceapply;
    }

    public Boolean getIntern() {
        return intern;
    }

    public void setIntern(Boolean intern) {
        this.intern = intern;
    }

    public List<String> getInternapply() {
        return internapply;
    }

    public void setInternapply(List<String> internapply) {
        this.internapply = internapply;
    }

    public work(Boolean freelance, List<String> freelanceapply, Boolean intern, List<String> internapply) {
        this.freelance = freelance;
        this.freelanceapply = freelanceapply;
        this.intern = intern;
        this.internapply = internapply;
    }

    public work() {
    }
}
