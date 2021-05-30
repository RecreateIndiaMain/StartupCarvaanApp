package recreate.india.main.startupcarvaan.mainActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import recreate.india.main.startupcarvaan.R;

public class PrivacyPolicy extends AppCompatActivity {

    private static final int RC_SIGN_IN = 001;
   private int c1=0,c2=0,c3=0,c4=0;
    private LinearLayout privacy_policy,term_conditions,payment_policy,rules;
    private ScrollView privacy_policyt,term_conditionst,payment_policyt,rulest;
    private TextView privacybutton,termsbutton,paymentbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        privacy_policy=findViewById(R.id.privacypolicy);
        term_conditions=findViewById(R.id.termsconditions);
        rules=findViewById(R.id.rule);
        payment_policy=findViewById(R.id.Refundpolicy);
        privacy_policyt=findViewById(R.id.privacypolicytext);
        term_conditionst=findViewById(R.id.termsconditionstext);
        rulest=findViewById(R.id.ruletext);
        payment_policyt=findViewById(R.id.Refundpolicytext);


        termsbutton=findViewById(R.id.termsbutton);

        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c4++;
                if(c4 %2==1)
                {rulest.setVisibility(View.VISIBLE);

                }
                else
                {
                    rulest.setVisibility(View.GONE);
                }

            }
        });
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