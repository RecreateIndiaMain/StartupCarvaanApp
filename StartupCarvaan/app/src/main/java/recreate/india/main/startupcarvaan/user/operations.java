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
    shareholdings shareholdings=new shareholdings();


    // to check user has already invested in the share or not
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
    //end here

    // function to retrieve current holdings of a particular share of a particular user
    void getholdings(String shareid){
        ff.collection("users")
                .document(user.getUid())
                .collection("myshares")
                .document(shareid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                shareholdings=value.toObject(shareholdings.class);
            }
        });
    }
    // end here

    // adding and update a share when user buy some share
    void addnewshare(String shareid,shareholdings holdings){
        ff.collection("users")
                .document(user.getUid())
                .collection("myshares").document(shareid).set(holdings);
    }

    void addshares(String shareid,Number shares,Number price){
        getholdings(shareid);
        Map<Number,Number>holdings=new HashMap<>();
        holdings=this.shareholdings.getHoldings();
        if(holdings.containsKey(price)){
            Number p_shares=holdings.get(price);
            p_shares=Double.valueOf((Double) p_shares)+Double.valueOf((Double) shares);
            holdings.put(price,p_shares);
        }
        else{
            holdings.put(price,shares);
        }
        ff.collection("users")
                .document(user.getUid())
                .collection("myshares").document(shareid).set(shareholdings);
    }
    //end here


    // removes shares from a user share when user sells
    void removeshares(String shareid,Number shares,Number price){
        getholdings(shareid);
        Map<Number,Number>holdings=new HashMap<>();
        holdings=this.shareholdings.getHoldings();
        Number p_shares=holdings.get(price);
        p_shares=Double.valueOf((Double) p_shares)-Double.valueOf((Double) shares);
        if(Double.valueOf((Double) p_shares)!=0)
            holdings.put(price,p_shares);
        ff.collection("users")
                .document(user.getUid())
                .collection("myshares")
                .document(shareid)
                .set(holdings).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                deleteshare(shareid);
            }
        });
    }
    //finish here


    // deleting a share from user collection if no share is left
    void deleteshare(String shareid){
        Map<Number,Number>holdings=new HashMap<>();
        holdings=this.shareholdings.getHoldings();
        getholdings(shareid);
        if(holdings.isEmpty()){
            ff.collection("users")
                    .document(user.getUid())
                    .collection("myshares")
                    .document(shareid).delete();
        }
    }
    // done here
}
