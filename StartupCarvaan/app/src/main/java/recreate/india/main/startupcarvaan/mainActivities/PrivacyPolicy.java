package recreate.india.main.startupcarvaan.mainActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
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
    private WebView privacy,refund,terms;
    private TextView privacybutton,termsbutton,paymentbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        privacy=findViewById(R.id.privacypolicy);
        terms=findViewById(R.id.termsandcondition);
        refund=findViewById(R.id.refud_policy);

        privacy.getSettings().setJavaScriptEnabled(true);
        privacy.loadUrl("https://www.privacypolicygenerator.info/live.php?token=Qk5tYKKzXzNauXqnmPWOD7pXpxOG8xQL ");

        terms.getSettings().setJavaScriptEnabled(true);
        terms.loadUrl("https://www.termsandconditionsgenerator.com/live.php?token=0WwI2zNxvD2pJTzuKS5Jqu4k9xKVzVoH ");

        refund.getSettings().setJavaScriptEnabled(true);
        refund.loadUrl("https://help.getoccasion.com/article/506-sample-cancellation-and-refund-policies");

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