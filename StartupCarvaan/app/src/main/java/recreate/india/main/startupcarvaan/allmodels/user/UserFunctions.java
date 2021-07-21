package recreate.india.main.startupcarvaan.allmodels.user;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import recreate.india.main.startupcarvaan.allmodels.reward.Level;
import recreate.india.main.startupcarvaan.allmodels.reward.Reward;
import recreate.india.main.startupcarvaan.allmodels.share.Share;

public class UserFunctions {

    public UserProfile userProfile = new UserProfile();  // user profile class for storing profile
    public FirebaseFirestore ff = FirebaseFirestore.getInstance();
    public FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    public Boolean new_user;

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
    // investment (1)
    public boolean check_rci(Double investment) {
        // Updating RCI after investing this much amount will be done by calling deduct Functions
        return userProfile.getAddedrci() + userProfile.getProfit() >= investment;
    }


    // Function to deduct rci upon buying is confirmed or investing
    // investment (2)
    public void deduct_rci(Double investment) {
        if (userProfile.getAddedrci() >= investment) {
            userProfile.setAddedrci(userProfile.getAddedrci() - investment);
        } else {
            userProfile.setProfit(userProfile.getProfit() - (investment - userProfile.getAddedrci()));
            userProfile.setAddedrci(0.0);
        }
        ff.collection("users").document(firebaseUser.getUid()).update("addedrci", userProfile.getAddedrci(), "profit", userProfile.getProfit());
    }

    public void addRci(Double investment) {
        ff.collection("users").document(firebaseUser.getUid()).update("profit", new UserFunctions().userProfile.getProfit() + investment);
    }

    // cheking new user for given share id or not
    //investment (4)
    public Boolean check_newUser(String shareid) {
        new_user = true;
        ff.collection("users").document(firebaseUser.getUid()).collection("myshares").document(shareid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                if (task.getResult().exists() == true)
                    new_user = false;
            }
        });
        return new_user;
    }

    public String getDay() {
        Date day = Timestamp.now().toDate();
        String days = String.valueOf(day.toString().charAt(8)) + String.valueOf(day.toString().charAt(9));
        return days;
    }


    public void addShareNewUser(String shareid, Double quantity, Double price) {
        String days = getDay();
        Double investment = price * quantity;
        Map<String, Map<String, ArrayList<Double>>> holdings = new HashMap<>();
        Map<String, ArrayList<Double>> holding = new HashMap<>();
        ArrayList<Double> arr = new ArrayList<Double>();
        arr.add(quantity);
        arr.add(price);
        holding.put(days, arr);
        holdings.put("holdings", holding);
        // updating the data
        ff.collection("users").document(firebaseUser.getUid()).collection("myshares").document(shareid).set(holdings);

        //FOR  updating the number of investors we need share details snapshot
        ff.collection("startup").document(shareid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Share share = new Share();
                share = documentSnapshot.toObject(Share.class);
                ff.collection("startup").document(shareid).update("investors", share.getInvestors() + 1);
            }
        });


        // updating number of investments by the user
        //    userProfile.setInvestmentcount(userProfile.getInvestmentcount() + 1);
        //   ff.collection("users").document(firebaseUser.getUid()).update("investments", userProfile.getInvestmentcount());
    }


    // if user laready holds shares of these startup
    public void updateUserShare(String shareid, Double quantity, Double price) {

//      TODO: please look into investment of more than a month

        Double investment = quantity * price;
        String days = getDay();
        final ShareHoldings[] shareHoldings = {new ShareHoldings()};
        ff.collection("users").document(firebaseUser.getUid()).collection("myshares").document(shareid).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                        shareHoldings[0] = task.getResult().toObject(ShareHoldings.class);
                        Double initaltotalAmount, finalTotalAmount;

                        Map<String, ArrayList<Double>> holdings = shareHoldings[0].getHoldings();
                        if (holdings.containsKey(days)) {
                            // if someone has invested on the same day in the same share
                            ArrayList<Double> n = new ArrayList<Double>(2);
                            n.add(holdings.get(days).get(0));
                            n.add(holdings.get(days).get(1));
                            initaltotalAmount = n.get(0) * n.get(1);
                            n.set(0, n.get(0) + quantity);
                            finalTotalAmount = initaltotalAmount + (quantity * price);
                            n.set(1, finalTotalAmount / n.get(0));
                            holdings.put(days, n);

                            ff.collection("users").document(firebaseUser.getUid()).collection("myshares").document(shareid).update("holdings", holdings);
                        } else {
                            // no shares of same day present
                            ArrayList<Double> p = new ArrayList<Double>(2);
                            p.add(quantity);
                            p.add(price);
                            holdings.put(days, p);
                            ff.collection("users").document(firebaseUser.getUid()).collection("myshares").document(shareid)
                                    .update("holdings", holdings);
                        }
                    }
                });
        // updating number of investments by the user
        // userProfile.setInvestmentcount(userProfile.getInvestmentcount() + 1);
        // ff.collection("users").document(firebaseUser.getUid()).update("investments", userProfile.getInvestmentcount());
    }

    public void giveRewards(Double investment) {
        //TODO: Testing is pending
        final UserProfile[] userProfile = {new UserProfile()};
        ff.collection("users").document(firebaseUser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                userProfile[0] = documentSnapshot.toObject(UserProfile.class);

                final Level[] level = {new Level()};
                final Reward[] reward = {new Reward()};
                ff.collection("reward").document("level").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        level[0] = documentSnapshot.toObject(Level.class);
                        ff.collection("reward").document("reward").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                reward[0] = documentSnapshot.toObject(Reward.class);
                                userProfile[0].setCurrentpoints(userProfile[0].getCurrentpoints() + (investment * 0.1));
                                userProfile[0].setTotalpoints(userProfile[0].getTotalpoints() + (investment * 0.1));
                                userProfile[0].setBonus(userProfile[0].getBonus() + (investment * 0.1));
                                final Integer userlevel = userProfile[0].getLevel();
                                Integer points = level[0].getLevel().get(userlevel - 1);
                                Log.v("Reward", points.toString());
                                if (userProfile[0].getCurrentpoints() >= points) {
                                    userProfile[0].setLevel(userlevel + 1);
                                    userProfile[0].setAddedrci(userProfile[0].getAddedrci() + reward[0].getReward().get(userlevel - 1));
                                    userProfile[0].setCurrentpoints(userProfile[0].getCurrentpoints() - points);
                                    // updating level
                                }
                                ff.collection("users").document(firebaseUser.getUid()).
                                        update("currentpoints", userProfile[0].getCurrentpoints(),
                                                "level", userProfile[0].getLevel(),
                                                "totalpoints", userProfile[0].getTotalpoints(),
                                                "bonus", userProfile[0].getBonus())
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful())
                                                    Log.v("Reward", "User rewarded");
                                                else
                                                    Log.v("Reward", "cannot give reward");
                                            }
                                        });
                            }
                        });
                    }
                });
            }
        });
    }


    public void removeShares(String shareid, String day, Double quantity, Double price) {
        final ShareHoldings[] shareHoldings = {new ShareHoldings()};
        ff.collection("users").document(firebaseUser.getUid()).collection("myshares").document(shareid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                if (task.getResult().exists()) {
                    shareHoldings[0] = task.getResult().toObject(ShareHoldings.class);
                    Map<String, ArrayList<Double>> holdings = shareHoldings[0].getHoldings();
                    ArrayList<Double> two = holdings.get(day);
                    two.set(0, two.get(0) - quantity);
                    if (two.get(0) == 0) {
                        holdings.remove(day);
                    } else holdings.put(day, two);
                    ff.collection("users").document(firebaseUser.getUid()).collection("myshares")
                            .document(shareid)
                            .update("holdings", holdings);
                }
            }
        });
    }

    //
    public void addPendingTransaction(String shareid, Double quantity, Double price, String type) {
        UserShareTransaction userShareTransaction = new UserShareTransaction();
        final Share[] share = {new Share()};

        ff.collection("startup").document(shareid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                share[0] = documentSnapshot.toObject(Share.class);
                String startupName = share[0].getName();
                userShareTransaction.setStatus(false);
                userShareTransaction.setType(type);
                userShareTransaction.setPrice(price);
                userShareTransaction.setQuantity(quantity);
                userShareTransaction.setValue(price * quantity);
                userShareTransaction.setShareid(shareid);
                userShareTransaction.setUserid(firebaseUser.getUid());
                userShareTransaction.setStartupname(startupName);
                userShareTransaction.setAdded(Timestamp.now());
                userShareTransaction.setAdded(Timestamp.now());
                ff.collection("users").document(firebaseUser.getUid())
                        .collection("pendingtransactions")
                        .document().set(userShareTransaction);
                ff.collection("startup").document(shareid)
                        .collection("pendingtransactions")
                        .document().set(userShareTransaction);
            }
        });


    }

    public void addCompletedTransaction(UserShareTransaction userShareTransaction, String shareid) {
        ff.collection("users").document(firebaseUser.getUid())
                .collection("completedtransactions")
                .document().set(userShareTransaction);
        ff.collection("startup").document(shareid)
                .collection("completedtransactions")
                .document().set(userShareTransaction);
    }

    public void delete(String id) {
        // delete the transaction from share also from web
        ff.collection("users").document(firebaseUser.getUid())
                .collection("pendingtransactions")
                .document(id).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {

            }
        });
    }

    public void checkIfDelete(String shareid) {
        ff.collection("users").document(firebaseUser.getUid()).collection("myshares").document(shareid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {

                        if (task.getResult().toObject(ShareHoldings.class).getHoldings().size() == 0) {
                            FirebaseFirestore.getInstance().collection("users")
                                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .collection("myshares")
                                    .document(shareid)
                                    .delete();
                        }
                    }
                });
    }
}