package recreate.india.main.startupcarvaan.allmodels.share;

import android.security.identity.EphemeralPublicKeyNotFoundException;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import recreate.india.main.startupcarvaan.allmodels.share.sharedetails.Investment;
import recreate.india.main.startupcarvaan.allmodels.share.sharedetails.TotalShareVolume;

public class ShareFunctions {

    public Investment investment=new Investment();  // for all investment details

    public TotalShareVolume totalShareVolume=new TotalShareVolume();
    public FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
    public FirebaseFirestore ff=FirebaseFirestore.getInstance();

    // constructor gets share id and gets the share details
    public ShareFunctions(String shareid){
            // getting investment details
        ff.collection("startup").document(shareid).collection("sharedetails").document("investment").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value!=null)
                    investment=value.toObject(Investment.class);
                else investment=null;
            }
        });
        // getting total share volume details
        ff.collection("startup").document(shareid).collection("sharedetails").document("totalsharevolume").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value!=null){
                    totalShareVolume=value.toObject(TotalShareVolume.class);
                }
                else{
                    totalShareVolume=null;
                }
            }
        });
    }


    public void update_collectedInvestment(String shareid,Double investment_amount){
        investment.setCollectedinvestment(investment_amount+investment.getCollectedinvestment());
        ff.collection("startup").document(shareid).collection("sharedetails").document("investment").update("collectedinvestment",investment.getCollectedinvestment());

    }

}
