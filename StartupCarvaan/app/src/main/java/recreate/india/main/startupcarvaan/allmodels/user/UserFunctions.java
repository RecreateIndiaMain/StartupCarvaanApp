package recreate.india.main.startupcarvaan.allmodels.user;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;

import recreate.india.main.startupcarvaan.allmodels.share.Share;
import recreate.india.main.startupcarvaan.allmodels.share.ShareFunctions;

public class UserFunctions {
    public UserProfile userProfile = new UserProfile();
    public FirebaseFirestore ff = FirebaseFirestore.getInstance();
    public FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    // gets the complete profile and sets it to userProfile variable in the constructor
    public UserFunctions() {
        ff.collection("users")
                .document(firebaseUser.getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                userProfile = documentSnapshot.toObject(UserProfile.class);
            }
        });
    }


    // check Function for Rci
    public boolean check_rci(Double investment) {
        // Updating RCI after investing this much amount will be done by calling deduct Functions
        return userProfile.getAddedrci() + userProfile.getProfit() >= investment;
    }

    // Function to deduct rci upon buying is confirmed or investing
    public void deduct_rci(Double investment) {
        if (userProfile.getAddedrci() >= investment) {
            userProfile.setAddedrci(userProfile.getAddedrci() - investment);
        } else {
            userProfile.setProfit(userProfile.getProfit() - (investment - userProfile.getAddedrci()));
            userProfile.setAddedrci(0.0);
        }
        ff.collection("users").document(firebaseUser.getUid()).update("addedrci", userProfile.getAddedrci(), "profit", userProfile.getProfit());
    }

    // cheking new user for given share id or not

    public boolean check_newUser(String shareid) {

        final boolean[] ans = {false};
        ff.collection("users").document(firebaseUser.getUid()).collection("myshares").document(shareid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.exists()) {
                    ans[0] = true;
                }
            }
        });
        return ans[0];
    }

    public void add_ShareForNewUser(String shareid, Integer quantity, Integer price) {

        Timestamp timestamp = Timestamp.now();
        String date = timestamp.toDate().toString();
        String days = date.charAt(0) + date.charAt(1) + "";

        HashMap<String, Integer[]> holdings = new HashMap<>();
        Integer arr[] = new Integer[2];
        arr[0] = quantity;
        arr[1] = price;
        holdings.put(days, arr);
        // updating the data
        ff.collection("users").document(firebaseUser.getUid()).collection("myshares").document(shareid).update("holdings",holdings);

        //FOR  updating the number of investors we need share details snapshot

        final Share[] share = {new Share()};
        ff.collection("startup").document(shareid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value.exists()){
                share[0] =value.toObject(Share.class);
                }
            }
        });
        // setting to share[0] model also
        share[0].setInvestors(share[0].getInvestors()+1);
        // updating number of investors
        ff.collection("startup").document(shareid).update("investors",share[0].getInvestors());
   }
   public void addShareForPresentUser(String shareid,Integer quantity,Integer price){

        if(check_sameDate_sameShare(shareid)){

       }

   }
   public boolean check_sameDate_sameShare(String shareid){
       final HashMap<String, Integer[]>[] holdings = new HashMap[]{new HashMap<>()};
        ff.collection("users").document(firebaseUser.getUid()).collection("myshares").document(shareid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
            if(value!=null) {
            // Yet to complete
            }
            }
        });

        return false;
   }

}
