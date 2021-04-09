package recreate.india.main.startupcarvaan.fragments.mycoins;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class coin {
    protected Integer bonus,rci,winnings;

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public Integer getRci() {
        return rci;
    }

    public void setRci(Integer rci) {
        this.rci = rci;
    }

    public Integer getWinnings() {
        return winnings;
    }

    public void setWinnings(Integer winnings) {
        this.winnings = winnings;
    }

    public coin(Integer bonus, Integer rci, Integer winnings) {
        this.bonus = bonus;
        this.rci = rci;
        this.winnings = winnings;
    }

    public coin() {

    }
}
