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
        final DocumentSnapshot[] dc = new DocumentSnapshot[1];
        FirebaseFirestore.getInstance().collection("users")
                .document(new user().user().getUid())
                .collection("others").document("coins")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        dc[0] =value;
                    }
                });
        this.bonus=Double.valueOf(dc[0].getString("bonus"));
        this.rci=Double.valueOf(dc[0].getString("rci"));
        this.winnings=Double.valueOf(dc[0].getString("winnings"));
        }
}
