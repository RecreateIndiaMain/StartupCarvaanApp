package recreate.india.main.startupcarvaan.payments;


import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.fragments.models.RciValue;
import recreate.india.main.startupcarvaan.fragments.mycoins.coin;
import recreate.india.main.startupcarvaan.user.user;
import recreate.india.main.startupcarvaan.user.userfunctions;

public class payin extends AppCompatActivity implements PaymentResultListener {
    private static final String TAG = payin.class.getSimpleName();
    private Checkout checkout=new Checkout();
    private coin coin=new coin();
    private Integer pay=10000;
    private EditText name,coins,phoneNumber;
    private TextView convertedMoney;
    private RciValue rciValue=new RciValue();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payin);
        name=findViewById(R.id.upiname);
        coins=findViewById(R.id.coins);
        phoneNumber=findViewById(R.id.phonenumber);
        convertedMoney=findViewById(R.id.convertedMoney);

        FirebaseFirestore.getInstance().collection("users").document(new user().user().getUid())
                .collection("others").document("coins")
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
        checkout.setKeyID("rzp_test_49JR35RUaeyRyr");
        // Payment button created by you in XML layout
        Button button = (Button) findViewById(R.id.btn_pay);

//        Toast.makeText(this, String.valueOf(rciValue.getCurrentvalue()), Toast.LENGTH_SHORT).show();
        coins.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                convertedMoney.setText("0");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0)
                convertedMoney.setText(String.valueOf(Integer.parseInt(coins.getText().toString())*rciValue.getCurrentvalue()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=name.getText().toString();
                String userPhone=phoneNumber.getText().toString();
                String user_coins=coins.getText().toString();

                if(checkDetails(username,userPhone,user_coins))
                    startPayment();
            }
        });
    }

    private boolean checkDetails(String username, String userPhone, String user_coins) {
        if (username.isEmpty()){
            showError(name,"This is mandatory");
            return false;
        }
        else if(userPhone.length() != 10){
            showError(phoneNumber,"Check this field again");
            return false;
        }
        else if(user_coins.isEmpty()|| user_coins.contains(".")){
            showError(coins,"Invalid coins");
            return false;
        }
        else if(Integer.parseInt(user_coins) <500 || Integer.parseInt(user_coins)>10000){
            showError(coins,"Please enter in range");
            return false;
        }
        else{
            return true;
        }
    }

    private void showError(EditText field, String s) {
    field.setError(s);
    }

    public void startPayment() {
        try {
            JSONObject options = new JSONObject();
            options.put("name", "Razorpay Corp");
            options.put("description", "Demoing Charges");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", pay);

            JSONObject preFill = new JSONObject();
            preFill.put("email", "test@razorpay.com");
            preFill.put("contact", "8171365728");

            options.put("prefill", preFill);

            checkout.open(payin.this, options);
        } catch (Exception e) {
            Toast.makeText(payin.this, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unused")
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            increment();

        }
        catch (Exception e){

        }

    }
    @SuppressWarnings("unused")
    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }
    private void increment(){
        FirebaseFirestore.getInstance().collection("users")
                .document(new user().user().getUid())
                .collection("others")
                .document("coins")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                coin coin=task.getResult().toObject(coin.class);
                FirebaseFirestore.getInstance().collection("users")
                        .document(new user().user().getUid())
                        .collection("others")
                        .document("coins")
                        .update("rci",coin.getRci()+(pay/100)).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        Toast.makeText(payin.this, "your current rci" + (coin.getRci()+(pay/100)), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
