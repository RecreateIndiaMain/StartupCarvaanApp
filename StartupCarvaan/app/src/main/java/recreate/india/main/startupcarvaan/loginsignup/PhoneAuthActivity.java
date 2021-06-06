package recreate.india.main.startupcarvaan.loginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.apache.commons.codec.language.bm.PhoneticEngine;

import java.sql.Time;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.fragments.progressdialogue.CustomProgressDialogue;
import recreate.india.main.startupcarvaan.mainActivities.MainActivity;
import recreate.india.main.startupcarvaan.user.CreateProfile;
import recreate.india.main.startupcarvaan.user.profile;

public class PhoneAuthActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    private EditText number,otp;
    private static final long start_time=30000;
    private Button getotp,login;
    private PhoneAuthProvider.ForceResendingToken forceResendingToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationStateChangedCallbacks;
    private String verificationId;
    private profile profile=new profile();
private TextView counter;
private CountDownTimer mcounter;
private boolean timer;
private long mtimeleft=start_time;
    private LinearLayout phone_layout,otp_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);

        phone_layout=findViewById(R.id.phoneverify);
        otp_layout=findViewById(R.id.otpverify);
        number=findViewById(R.id.number);
        getotp=findViewById(R.id.getotp);
        otp=findViewById(R.id.otp);
        login=findViewById(R.id.login);
        counter=findViewById(R.id.counter);

        verificationStateChangedCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signinUser(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(PhoneAuthActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationId=s;
                phone_layout.setVisibility(View.GONE);
                otp_layout.setVisibility(View.VISIBLE);

            }
        };
        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=number.getText().toString();
                if(phone.length()!=10){
                    number.setError("Invalid Number");
                }
                else {
                    startPhoneVerification(phone);
                    startTimer();
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code=otp.getText().toString();
                verifyPhoneNumberWithCode(verificationId,code);
            }
        });
    }

    private void signinUser(PhoneAuthCredential phoneAuthCredential) {
        firebaseAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    updateUI(firebaseAuth.getCurrentUser());
                }
            }
        });
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationId,code);
        signinUser(credential);
    }

    private void startPhoneVerification(String phone) {
        PhoneAuthOptions options=PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber("+91"+phone)
                .setTimeout(60l, TimeUnit.SECONDS)
                .setCallbacks(verificationStateChangedCallbacks)
                .setActivity(PhoneAuthActivity.this)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            FirebaseFirestore.getInstance().collection("users").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (!task.getResult().exists()) {
                        FirebaseFirestore.getInstance().collection("users").document(user.getUid()).set(profile.giveNewUser());
                        Map<String,Double> coins=new HashMap<>();
                        coins.put("bonus", Double.valueOf(1000));
                        coins.put("rci", Double.valueOf(0));
                        coins.put("winnings", Double.valueOf(0));
                        FirebaseFirestore.getInstance().collection("users").document(user.getUid())
                                .collection("others").document("coins").set(coins);
                        startActivity(new Intent(PhoneAuthActivity.this, CreateProfile.class));
                        finish();
                    }
                    else {
                        startActivity(new Intent(PhoneAuthActivity.this, MainActivity.class));
                        finish();
                    }
                }
            });
        } else {
            Toast.makeText(PhoneAuthActivity.this, "there is some error in logging into your account", Toast.LENGTH_LONG).show();
        }
    }
private void startTimer()
{
    mcounter=new CountDownTimer(mtimeleft,1000) {
        @Override
        public void onTick(long l) {
            mtimeleft=l;
            updatecountdowntext();

        }

        @Override
        public void onFinish() {

        }
    }.start();

}

    private void updatecountdowntext() {
        int minutes=(int)(mtimeleft/1000)/60;
        int second=(int)(mtimeleft/1000)%60;
        String time=String.format(Locale.getDefault(),"%02d:%02d",minutes,second);
        counter.setText(time);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PhoneAuthActivity.this,loginActivity.class));
    }
}