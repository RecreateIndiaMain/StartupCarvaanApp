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

public class UserFunctions {

    private Integer levels[] = {200, 400, 600, 800, 1000};
    public UserProfile userProfile = new UserProfile();  // user profile class for storing profile
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
// All functions decalaration :
    /**
     * 1.checkrci()
     * 2.deductRci()
     * 3.checknewUser()
     * 4.addShareforNewUser()
     * 5.addShareForPresentUser()
     * 6.giveRewards()   (private)

*/

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
        giveRewards(investment);
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
        return !ans[0];
    }

    public void add_ShareForNewUser(String shareid, Double quantity, Double price) {

        Timestamp timestamp = Timestamp.now();
        String date = timestamp.toDate().toString();
        String days = date.charAt(0) + date.charAt(1) + "";

        Double Investment=price*quantity;
        HashMap<String, Double[]> holdings = new HashMap<>();
        Double arr[] = new Double[2];
        arr[0] = quantity;
        arr[1] = price;
        holdings.put(days, arr);
        // updating the data
        ff.collection("users").document(firebaseUser.getUid()).collection("myshares").document(shareid).update("holdings", holdings);

        //FOR  updating the number of investors we need share details snapshot

        final Share[] share = {new Share()};
        ff.collection("startup").document(shareid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.exists()) {
                    share[0] = value.toObject(Share.class);
                }
            }
        });
        // setting to share[0] model also
        share[0].setInvestors(share[0].getInvestors() + 1);
        // updating number of investors
        ff.collection("startup").document(shareid).update("investors", share[0].getInvestors());

        // updating number of investments by the user
        userProfile.setInvestmentcount(userProfile.getInvestmentcount() + 1);
        ff.collection("users").document(firebaseUser.getUid()).update("investments", userProfile.getInvestmentcount());

        // give rewards
        giveRewards(Investment);
    }
        // if user laready holds shares of these startup
    public void addShareForPresentUser(String shareid, Double quantity, Double price) {

//      TODO: please look into investment of more than a month
        Double investment=quantity*price;

        Timestamp timestamp = Timestamp.now();
        String date = timestamp.toDate().toString();
        String days = date.charAt(0) + date.charAt(1) + "";


        final ShareHoldings[] shareHoldings = {new ShareHoldings()};
        ff.collection("users").document(firebaseUser.getUid()).collection("myshares").document(shareid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    shareHoldings[0] = value.toObject(ShareHoldings.class);

                }
            }
        });
        Double initaltotalAmount, finalTotalAmount;
        HashMap<String, Double[]> holdings = shareHoldings[0].getHoldings();

        if (holdings.containsKey(days)) {
            // if someone has invested on the same day in the same share
            Double n[] = new Double[2];
            n = holdings.get(days);
            initaltotalAmount = n[0] * n[1];
            n[0] = n[0] + quantity;
            finalTotalAmount = initaltotalAmount + quantity * price;
            n[1] = finalTotalAmount / n[0];
            ff.collection("users").document(firebaseUser.getUid()).collection("myshares").document(shareid).update("holdings", holdings);
        } else {
            // no shares of same day present
            Double[] p = new Double[2];
            p[0] = price;
            p[1] = quantity;
            HashMap<String, Double[]> holdings2 = new HashMap<>();
            holdings2.put(days, p);
            ff.collection("users").document(firebaseUser.getUid()).collection("myshares").document(shareid)
                    .update("holdings", holdings2);
        }

        // updating number of investments by the user
        userProfile.setInvestmentcount(userProfile.getInvestmentcount() + 1);
        ff.collection("users").document(firebaseUser.getUid()).update("investments", userProfile.getInvestmentcount());

        giveRewards(investment);
    }

    private void giveRewards(Double investment) {
        // give reward and increase level if needed
        Double points = userProfile.getCurrentpoints();
        Double prize=investment*0.1;
        points=points+prize;
        userProfile.setCurrentpoints(points);
        final Integer level = userProfile.getLevel();
        if(points>=levels[level-1]){
            userProfile.setLevel(level+1);
            userProfile.setAddedrci(userProfile.getAddedrci()+50);
        }
        // updating level
        ff.collection("users").document(firebaseUser.getUid()).update("currentpoints",points,"level",userProfile.getLevel(),"addedrci",userProfile.getAddedrci());


    }

}
