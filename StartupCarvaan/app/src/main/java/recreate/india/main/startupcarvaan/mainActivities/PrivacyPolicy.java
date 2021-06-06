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
    private LinearLayout rules,newly;
    private LinearLayout rulest;
    private TextView privacybutton,termsbutton,paymentbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        rules=findViewById(R.id.rule);
        newly=findViewById(R.id.newly);

        rulest=findViewById(R.id.ruletext);

        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c4++;
                if(c4 %2==1)
                {
                    rulest.setVisibility(View.VISIBLE);
                    newly.setVisibility(View.GONE);

                }
                else
                {
                    rulest.setVisibility(View.GONE);
                    newly.setVisibility(View.VISIBLE);
                }

            }
        });


        // Choose authentication providers
    }
}