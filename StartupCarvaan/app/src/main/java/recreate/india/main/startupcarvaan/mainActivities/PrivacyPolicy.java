package recreate.india.main.startupcarvaan.mainActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import recreate.india.main.startupcarvaan.R;

public class PrivacyPolicy extends AppCompatActivity {

    private static final int RC_SIGN_IN = 001;
    private LinearLayout privacy_policy,term_conditions,payment_policy;
    private TextView privacybutton,termsbutton,paymentbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        privacy_policy=findViewById(R.id.privacypolicy);
        term_conditions=findViewById(R.id.terms_condition);
        payment_policy=findViewById(R.id.payment_policy);
        privacybutton=findViewById(R.id.privacybutton);
        termsbutton=findViewById(R.id.termsbutton);
        paymentbutton=findViewById(R.id.paymentbutton);
        termsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                term_conditions.setVisibility(View.GONE);
                privacy_policy.setVisibility(View.VISIBLE);
            }
        });
        privacybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                privacy_policy.setVisibility(View.GONE);
                payment_policy.setVisibility(View.VISIBLE);
            }
        });
        paymentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PrivacyPolicy.this,MainActivity.class));
            }
        });
        // Choose authentication providers
    }
}