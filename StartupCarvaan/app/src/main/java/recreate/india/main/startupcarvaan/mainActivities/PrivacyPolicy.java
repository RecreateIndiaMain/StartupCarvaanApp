package recreate.india.main.startupcarvaan.mainActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
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
    private LinearLayout rules,newly,privacy,terms,refund;
    private LinearLayout rulest;
    private ImageView im11,im22,im33,im44;
    private WebView privacypolicy,termand,refundpolicy;
    private TextView privacybutton,termsbutton,paymentbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        rules=findViewById(R.id.rule);
        newly=findViewById(R.id.newly);
        im11=findViewById(R.id.im11);
        im22=findViewById(R.id.im22);
        im33=findViewById(R.id.im33);
        im44=findViewById(R.id.im44);
privacy=findViewById(R.id.privacy);
terms=findViewById(R.id.terms);
refund=findViewById(R.id.refund);
privacypolicy=findViewById(R.id.privacypolicy);
termand=findViewById(R.id.termsandcondition);
refundpolicy=findViewById(R.id.refud_policy);
        rulest=findViewById(R.id.ruletext);

        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c4++;
                if(c4 %2==1)
                {
                    rulest.setVisibility(View.VISIBLE);
                    newly.setVisibility(View.GONE);
                    im11.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);

                }
                else
                {
                    rulest.setVisibility(View.GONE);
                    newly.setVisibility(View.VISIBLE);
                    im11.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }

            }
        });
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c3++;
                if(c3 %2==1)
                {
                    privacypolicy.setVisibility(View.VISIBLE);
                    newly.setVisibility(View.GONE);
                    im22.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);

                }
                else
                {
                    privacypolicy.setVisibility(View.GONE);
                    newly.setVisibility(View.VISIBLE);
                    im22.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }

            }
        });
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c2++;
                if(c2 %2==1)
                {
                    termand.setVisibility(View.VISIBLE);
                    newly.setVisibility(View.GONE);
                    im33.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);

                }
                else
                {
                    termand.setVisibility(View.GONE);
                    newly.setVisibility(View.VISIBLE);
                    im33.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }

            }
        });
        refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c1++;
                if(c1 %2==1)
                {
                    refundpolicy.setVisibility(View.VISIBLE);
                    newly.setVisibility(View.GONE);
                    im44.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);

                }
                else
                {
                    refundpolicy.setVisibility(View.GONE);
                    newly.setVisibility(View.VISIBLE);
                    im44.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
                }

            }
        });


        // Choose authentication providers
    }
}