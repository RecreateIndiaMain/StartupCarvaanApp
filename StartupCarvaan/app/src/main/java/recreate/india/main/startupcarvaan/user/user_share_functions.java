package recreate.india.main.startupcarvaan.user;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import recreate.india.main.startupcarvaan.fragments.models.allshare;
import recreate.india.main.startupcarvaan.fragments.models.holdings;

public class user_share_functions {
    public user_share_functions() {
    }

    private FirebaseFirestore ff=FirebaseFirestore.getInstance();
    private FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
    public boolean isUser(String shareid){
        final boolean[] yes = {true};
        try{
            ff.collection("users")
                    .document("tupjdAJB8JcfMdzqc4P5iRIg0XE2")
                    .collection("myshares")
                    .document(shareid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    yes[0]=false;
                }
            });
        }
        catch (Exception e){
            return yes[0];
        }

        return true;
    }
    public void addNewShare(String shareid,Double price,Double quantity){
        Map<String,Object> newshare=new HashMap<>();
        Map<String,Double> holdings=new HashMap<>();
        holdings.put(String.valueOf(price),quantity);
        newshare.put("holdings",holdings);
        ff.collection("users")
                .document("tupjdAJB8JcfMdzqc4P5iRIg0XE2")
                .collection("myshares")
                .document(shareid)
                .set(newshare);
    }
    public void removeSomeShares(String shareid,Double share,Double price){
        ff.collection("users")
                .document("tupjdAJB8JcfMdzqc4P5iRIg0XE2")
                .collection("myshares")
                .document(shareid).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        holdings holdings=new holdings();
                        holdings=task.getResult().toObject(holdings.class);
                        Map<String ,Double> updateShare=new HashMap<>();
                        updateShare.putAll(holdings.getHoldings());
                        Double to_share=updateShare.get(String.valueOf(price));
                        if(to_share-share==0.0 || to_share-share==0)
                            updateShare.remove(String.valueOf(price));
                        else{
                            updateShare.put(String.valueOf(price),to_share-share);
                        }
                        ff.collection("users")
                                .document("tupjdAJB8JcfMdzqc4P5iRIg0XE2")
                                .collection("myshares")
                                .document(shareid).update("holdings",updateShare);
                    }
                });
    }
    public void updateShare(String shareid,Double price,Double quantity){
        ff.collection("users")
                .document("tupjdAJB8JcfMdzqc4P5iRIg0XE2")
                .collection("myshares")
                .document(shareid).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                holdings holdings=new holdings();
                holdings=task.getResult().toObject(holdings.class);
                Map<String ,Double> updateShare=new HashMap<>();
                updateShare.putAll(holdings.getHoldings());
                if(updateShare.containsKey(String.valueOf(price))) {
                    Double value=updateShare.get(String.valueOf(price));
                    updateShare.put(String.valueOf(price),value+quantity);
                }
                else updateShare.put(String.valueOf(price),Double.valueOf(quantity));
                ff.collection("users")
                        .document("tupjdAJB8JcfMdzqc4P5iRIg0XE2")
                        .collection("myshares")
                        .document(shareid).update("holdings",updateShare);
            }
        });
    }
    public void addUser(String shareid){
        ff.collection("allshares")
                .document("shareid")
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
