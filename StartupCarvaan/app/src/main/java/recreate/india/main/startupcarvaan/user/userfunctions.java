package recreate.india.main.startupcarvaan.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class userfunctions {
    private FirebaseFirestore ff=FirebaseFirestore.getInstance();
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
                .collection("others")
                .document("coins")
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
                .update("bonus",current+bonus);
    }
    public void addPoints(Double increase){
        ff.collection("users")
                .document(new user().user().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                profile profile=task.getResult().toObject(profile.class);
                ff.collection("users")
                        .document(new user().user().getUid())
                        .update("points",profile.getPoints()+increase);
            }
        });
    }
}
