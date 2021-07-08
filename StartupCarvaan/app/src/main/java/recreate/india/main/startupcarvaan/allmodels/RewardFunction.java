package recreate.india.main.startupcarvaan.allmodels;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class RewardFunction {

    FirebaseFirestore ff=FirebaseFirestore.getInstance();
    public Reward reward=new Reward();
    public Level level=new Level();
    public RewardFunction(){
        ff.collection("reward").document("level").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value!=null){
                    level=value.toObject(Level.class);

                }
                else
                    level=null;
            }
        });
        ff.collection("reward").document("reward").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value!=null){
                    reward=value.toObject(Reward.class);
                }
                else
                    reward=null;
            }
        });
    }
}
