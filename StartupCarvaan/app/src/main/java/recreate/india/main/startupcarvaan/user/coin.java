package recreate.india.main.startupcarvaan.user;

public class coin {
    private Number bonus,rci,winnings;

    public Number getBonus() {
        return bonus;
    }

    public void setBonus(Number bonus) {
        this.bonus = bonus;
    }
    public Number getRci() {
        return rci;
    }

    public void setRci(Number rci) {
        this.rci = rci;
    }

    public Number getWinnings() {
        return winnings;
    }

    public void setWinnings(Number winnings) {
        this.winnings = winnings;
    }

    public coin(Number bonus, Number rci, Number winnings) {
        this.bonus = bonus;
        this.rci = rci;
        this.winnings = winnings;
    }
    public coin() {
    }
}
