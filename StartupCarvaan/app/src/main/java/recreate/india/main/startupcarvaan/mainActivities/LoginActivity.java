package recreate.india.main.startupcarvaan.mainActivities;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.PhoneNumberVerificationRequiredException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import recreate.india.main.startupcarvaan.MainActivity;
import recreate.india.main.startupcarvaan.R;

public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 001;
    private FirebaseAuth auth=FirebaseAuth.getInstance();
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack;

    private EditText phone_number,otp;
    private Button request_otp,verify_otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone_number=findViewById(R.id.phoneNumber);
        otp=findViewById(R.id.otp);
        request_otp=findViewById(R.id.requestOtp);
        verify_otp=findViewById(R.id.verifyOtp);
        request_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(phone_number.getText().toString()==null)
                    Toast.makeText(LoginActivity.this,"Please enter phone number",Toast.LENGTH_LONG).show();
                else{
                    PhoneAuthProvider.getInstance().
                            verifyPhoneNumber("+91"+phone_number.getText().toString(), 60, TimeUnit.SECONDS, LoginActivity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            signinUser(phoneAuthCredential);
                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            Toast.makeText(LoginActivity.this,"phone number is not valid",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCodeSent(@NonNull String verificationotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            super.onCodeSent(verificationotp, forceResendingToken);
                            verify_otp.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String entered_otp=otp.getText().toString();
                                    if(entered_otp==null)
                                        return;
                                    PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationotp,entered_otp);
                                    signinUser(credential);
                                }
                            });
                        }

                        @Override
                        public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                            super.onCodeAutoRetrievalTimeOut(s);
                        }
                                });
                }
            }
        });
    }

    private void signinUser(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                    if(user!=null)
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this, "sorry to verify please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}