package recreate.india.main.startupcarvaan.fragments.mycoins;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.payments.payin;
import recreate.india.main.startupcarvaan.payments.payout;
import recreate.india.main.startupcarvaan.user.user;

public class mycoins extends Fragment {


    private TextView rci,bonus,winnings;
    private Button buy,sell;
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
        winnings=view.findViewById(R.id.winnings);
        buy=view.findViewById(R.id.buy);
        sell=view.findViewById(R.id.sell);
        FirebaseFirestore.getInstance().collection("users").document(new user().user().getUid()).collection("others").document("coins")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        coin coin=new coin();
                        coin=value.toObject(coin.class);
                        rci.setText(String.valueOf(coin.getRci()));
                        bonus.setText(String.valueOf(coin.getBonus()));
                        winnings.setText(String.valueOf(coin.getWinnings()));
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
        return view;
    }
}