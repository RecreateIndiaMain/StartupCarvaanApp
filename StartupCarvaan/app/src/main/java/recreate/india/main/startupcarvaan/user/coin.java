package recreate.india.main.startupcarvaan.user;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class coin {
    protected Double bonus,rci,winnings;

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Double getRci() {
        return rci;
    }

    public void setRci(Double rci) {
        this.rci = rci;
    }

    public Double getWinnings() {
        return winnings;
    }

    public void setWinnings(Double winnings) {
        this.winnings = winnings;
    }

    public coin(Double bonus, Double rci, Double winnings) {
        this.bonus = bonus;
        this.rci = rci;
        this.winnings = winnings;
    }

    public coin() {

    }
}
