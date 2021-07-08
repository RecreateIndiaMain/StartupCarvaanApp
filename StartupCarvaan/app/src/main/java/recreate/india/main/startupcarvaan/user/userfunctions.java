package recreate.india.main.startupcarvaan.user;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;

import recreate.india.main.startupcarvaan.fragments.mycoins.coin;

public class userfunctions {
    private FirebaseFirestore ff=FirebaseFirestore.getInstance();
    private Integer level[]={200,400,600,800,1000};
    private profile profile=new profile();
    public void userfunctions(){
        ff.collection("users").document(new user().user().getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                profile=value.toObject(profile.class);
            }
        });
    }

    public void removeRci(Integer current,Integer rci){
        ff.collection("users")
                .document(new user().user().getUid())
                .update("rci",current-rci);
    }
    public void addRci(Integer current,Integer rci){
        ff.collection("users")
                .document(new user().user().getUid())
                .collection("others")
                .document("coins")
                .update("rci",current+rci);
    }
    public void addBonus(Integer current,Integer bonus){
        ff.collection("users")
                .document(new user().user().getUid())
                .collection("others")
                .document("coins")
                .update("bonus",current+bonus);
    }
    public void removeBonus(Integer current,Integer bonus){
        ff.collection("users")
                .document(new user().user().getUid())
                .collection("others")
                .document("coins")
                .update("bonus",current-bonus);
    }
    public void addPoints(Double increase){
        ff.collection("users")
                .document(new user().user().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                profile profile=task.getResult().toObject(profile.class);

                Double currentpoints=profile.getCurrentpoints();
                currentpoints=currentpoints+increase;

                if(currentpoints>=level[profile.getI()-1]){
                    currentpoints=currentpoints-level[profile.getI()-1];
                    profile.setI(profile.getI()+1);
                    final coin[] coin = {new coin()};
                    Double finalCurrentpoints = currentpoints;
                    FirebaseFirestore.getInstance()
                            .collection("users")
                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .collection("others").document("coins").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                            coin[0]=task.getResult().toObject(coin.class);
                            addRci(coin[0].getRci(),profile.getReward().get(String.valueOf(profile.getI()-1)));
                            ff.collection("users")
                                    .document(new user().user().getUid())
                                    .update("points",profile.getPoints()+increase,"currentpoints", finalCurrentpoints,"i",profile.getI());
                        }
                    });
                }
                else{
                    ff.collection("users")
                            .document(new user().user().getUid())
                            .update("points",profile.getPoints()+increase,"currentpoints", currentpoints);
                }
            }
        });
    }
}
