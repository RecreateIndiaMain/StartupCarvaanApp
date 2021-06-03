package recreate.india.main.startupcarvaan.fragments.mycoins;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.mainActivities.MainActivity;
import recreate.india.main.startupcarvaan.payments.payin;
import recreate.india.main.startupcarvaan.payments.payout;
import recreate.india.main.startupcarvaan.user.user;
import recreate.india.main.startupcarvaan.user.userfunctions;

public class mycoins extends Fragment {


    private TextView rci,bonus,winnings;
    private Button buy,sell,btnBonus;
    private RewardedAd mRewardedAd;
//    private final String TAG = "MycoinsFragment";
    public mycoins() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mycoins, container, false);
        rci=view.findViewById(R.id.rci);
        bonus=view.findViewById(R.id.bonus);
        buy=view.findViewById(R.id.buy);
        sell=view.findViewById(R.id.sell);
        btnBonus=view.findViewById(R.id.btn_earnBonus);
        FirebaseFirestore.getInstance().collection("users").document(new user().user().getUid()).collection("others").document("coins")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        coin coin=new coin();
                        coin=value.toObject(coin.class);
                        rci.setText(String.valueOf(coin.getRci()));
                        bonus.setText(String.valueOf(coin.getBonus()));
                    }
                });
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), payin.class));
            }
        });
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), payout.class));
            }
        });

// Written by siddharth 2nd june 2021
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }

        });
        revAds();
        btnBonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRewardedAd != null) {
                    Activity activityContext = getActivity();
                    mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            // Handle the reward.
//                            Log.d(TAG, "The user earned the reward.");
//                            int rewardAmount = rewardItem.getAmount();
//                            String rewardType = rewardItem.getType();
                            mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                                @Override
                                public void onAdShowedFullScreenContent() {
                                    // Called when ad is shown.
//                                    Log.d(TAG, "Ad was shown.");
                                    final coin[] coin = {new coin()};
                                    FirebaseFirestore.getInstance().collection("users")
                                            .document(new user().user().getUid())
                                            .collection("others")
                                            .document("coins")
                                            .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            coin[0] =task.getResult().toObject(coin.class);
                                            userfunctions userfunctions=new userfunctions();
                                            userfunctions.addBonus(coin[0].getBonus(),50);
                                        }
                                    });
                                    mRewardedAd = null;
                                }

                                @Override
                                public void onAdFailedToShowFullScreenContent(AdError adError) {
                                    // Called when ad fails to show.
                                    Toast.makeText(getContext(), "Please check your internet \nTry again later", Toast.LENGTH_SHORT).show();
                                    mRewardedAd=null;
                                    revAds();
                                    super.onAdFailedToShowFullScreenContent(adError);
                                }

                                @Override
                                public void onAdDismissedFullScreenContent() {
                                    revAds();
                                    mRewardedAd=null;
//                                    Log.d(TAG, "Ad was dismissed.");

                                }
                            });
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "The rewarded ad wasn't ready yet.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }


    public void revAds(){
        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(getContext(),"ca-app-pub-5245859690369666/5581449141",
                adRequest, new RewardedAdLoadCallback(){
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
//                        Log.d(TAG, loadAdError.getMessage());
                        Toast.makeText(getContext(), "Error: " +loadAdError.getMessage(), Toast.LENGTH_SHORT).show();

                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        Toast.makeText(getContext(), "Add loaded", Toast.LENGTH_SHORT).show();
//                        Log.d(TAG, "Ad was loaded.");
                    }
                });
    }

}