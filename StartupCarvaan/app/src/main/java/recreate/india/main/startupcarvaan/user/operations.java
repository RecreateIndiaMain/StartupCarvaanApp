package recreate.india.main.startupcarvaan.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class operations {
    private FirebaseFirestore ff=FirebaseFirestore.getInstance();
    private FirebaseAuth fauth= FirebaseAuth.getInstance();
    private FirebaseUser user=fauth.getCurrentUser();
    private Map<Number,Number>holdings=new HashMap<>();

    Boolean isalreadyuser(String shareid){
        final boolean[] check = {false};
        ff.collection("users")
                .document(user.getUid())
                .collection("myshares")
                .document(shareid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.getResult().exists()){
                    check[0] =true;
                }
            }
        });
        return  check[0];
    }
    void getholdings(String shareid){
        ff.collection("users")
                .document(user.getUid())
                .collection("myshares")
                .document(shareid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                holdings= (Map<Number, Number>) value.toObject(holdings.class);
            }
        });
    }

    void addnewshare(String shareid,Map<Number,Number> holdings){
        ff.collection("users")
                .document(user.getUid())
                .collection("myshares").document(shareid).set(holdings);
    }
    void addshares(String shareid,Number shares,Number price){
        getholdings(shareid);
        if(this.holdings.containsKey(price)){
            Number p_shares=this.holdings.get(price);
            p_shares=Double.valueOf((Double) p_shares)+Double.valueOf((Double) shares);
            this.holdings.put(price,p_shares);
        }
        else{
            this.holdings.put(price,shares);
        }
        ff.collection("users")
                .document(user.getUid())
                .collection("myshares").document(shareid).set(this.holdings);
    }
    void removeshares(String shareid,Number shares,Number price){
        getholdings(shareid);
        Number p_shares=this.holdings.get(price);
        p_shares=Double.valueOf((Double) p_shares)-Double.valueOf((Double) shares);
        if(Double.valueOf((Double) p_shares)!=0)
            this.holdings.put(price,p_shares);
        ff.collection("users")
                .document(user.getUid())
                .collection("myshares")
                .document(shareid)
                .set(this.holdings);
    }
    void deleteshare(String shareid){
        getholdings(shareid);
        if(this.holdings.isEmpty()){
            ff.collection("users")
                    .document(user.getUid())
                    .collection("myshares")
                    .document(shareid).delete();
        }
    }
}
