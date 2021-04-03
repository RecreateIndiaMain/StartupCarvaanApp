package recreate.india.main.startupcarvaan.user;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import recreate.india.main.startupcarvaan.fragments.models.allshare;
import recreate.india.main.startupcarvaan.fragments.models.holdings;

public class user_share_functions {
    private FirebaseFirestore ff=FirebaseFirestore.getInstance();
    private FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
    public boolean isUser(String shareid){
        final boolean[] yes = {false};
        ff.collection("users")
                .document(user.getUid())
                .collection("myshares")
                .document(shareid)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.getResult().exists()){
                    yes[0] =true;
                }

            }
        });
        return yes[0];
    }
    public void addNewShare(String shareid,Double price,Double quantity){
        Map<String,Double> newshare=new HashMap<>();
        newshare.put(String.valueOf(price),quantity);
        ff.collection("users")
                .document(user.getUid())
                .collection("myshares")
                .document(shareid)
                .set(newshare);
    }
    public void updateShare(String shareid,Double price,Double quantity){
        ff.collection("users")
                .document(user.getUid())
                .collection("myshares")
                .document(shareid).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                holdings holdings=new holdings();
                holdings=task.getResult().toObject(holdings.class);
                Map<String ,Double> updateShare=holdings.getShare();
                updateShare.put(String.valueOf(price),quantity);
                ff.collection("users")
                        .document(user.getUid())
                        .collection("myshares")
                        .document(shareid).update("holdings",updateShare);
            }
        });
    }
    public void addUser(String shareid){
        ff.collection("allshares")
                .document(shareid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        allshare allshare=new allshare();
                        allshare=task.getResult().toObject(allshare.class);
                        ff.collection("allshares")
                                .document(shareid)
                                .update("users",allshare.getUsers()+1);
                    }
                });
    }
}
