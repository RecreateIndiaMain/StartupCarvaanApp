package recreate.india.main.startupcarvaan.payments;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.fragments.models.RciValue;
import recreate.india.main.startupcarvaan.fragments.mycoins.coin;
import recreate.india.main.startupcarvaan.user.user;

public class payout extends AppCompatActivity {

    private EditText transact_coins,userName,userphone,userupi;
    private TextView convertedmoney;
    private Button btnWithdraw;
    private Integer user_current_rci;
    private Integer rci_current_price;
    private double to_get;
    private RciValue rciValue = new RciValue();
    private coin coin=new coin();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payout);
        transact_coins=findViewById(R.id.coins);
        userName=findViewById(R.id.upiname);
        userphone=findViewById(R.id.upiphonenumber);
        userupi=findViewById(R.id.upi_id);
        btnWithdraw=findViewById(R.id.withdrawButton);
        convertedmoney=findViewById(R.id.convertedMoney);

        FirebaseFirestore.getInstance().collection("users").document(new user().user().getUid()).collection("others").document("coins")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        coin=value.toObject(coin.class);
                    }
                });
        FirebaseFirestore.getInstance().collection("AboutRci").document("details").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value!=null)
                {
                    rciValue=value.toObject(RciValue.class);
                }
            }
        });
        transact_coins.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                convertedmoney.setText("0");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0){
                    convertedmoney.setText(String.valueOf(Integer.parseInt(transact_coins.getText().toString())*rciValue.getCurrentvalue()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String coins=String.valueOf(transact_coins.getText());
                    String name=String.valueOf(userName.getText());
                    String phoneNumber=String.valueOf(userphone.getText());
                    String upi=String.valueOf(userupi.getText());
                    if( coins.length()==0|| coins.length()>5 || coins.contains(".")){
                        showError(transact_coins,"Please input valid number of coins");
                    }
                    else if(name.isEmpty())
                        showError(userName,"This field is mandatory");
                    else if(phoneNumber.length() != 10)
                        showError(userphone,"This field is mandatory");
                    else if(upi.isEmpty())
                        showError(userupi,"This field is mandatory");
                    else{
                        Integer int_coins=Integer.valueOf(coins);
                        if(int_coins<500 || int_coins >10000)
                            showError(transact_coins,"Please input in range of 500 to 10000");
                        else{
                            if(int_coins>coin.getRci()){
                                Toast.makeText(payout.this, "You dont have enough coins", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                to_get=rciValue.getCurrentvalue()*int_coins;
                                convertedmoney.setText(String.valueOf(to_get));

                            }
                        }
                    }

            }
        });


    }

    private void showError(EditText field, String s) {
        field.setError(s);
    }
}