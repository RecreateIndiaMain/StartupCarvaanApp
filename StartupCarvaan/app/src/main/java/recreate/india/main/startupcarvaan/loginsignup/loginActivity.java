package recreate.india.main.startupcarvaan.loginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import recreate.india.main.startupcarvaan.allmodels.user.UserProfile;
import recreate.india.main.startupcarvaan.mainActivities.MainActivity;
import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.mainActivities.PrivacyPolicy;
import recreate.india.main.startupcarvaan.user.CreateProfile;
import recreate.india.main.startupcarvaan.user.profile;

public class loginActivity extends AppCompatActivity {
private CheckBox privacy;
    private static final int RC_SIGN_IN = 001;
    private static final String TAG = "startup carvaan";
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    FirebaseFirestore ff=FirebaseFirestore.getInstance();

    private EditText username,password;
    private ImageView login;
    private TextView register;

    private FirebaseAuth mAuth=FirebaseAuth.getInstance();

    //google signin
    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton signInButton;
    private TextView phonebutton,logintext;
    private recreate.india.main.startupcarvaan.user.profile profile=new profile();
    private UserProfile userProfile=new UserProfile();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        signInButton = findViewById(R.id.sign_in_button);
        logintext=findViewById(R.id.logintext);
        phonebutton=findViewById(R.id.phonebutton);
        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this, PrivacyPolicy.class));
                finish();
            }
        });
        privacy=findViewById(R.id.checkprivacy);
        privacy.setChecked(true);
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(privacy.isChecked()){
                    signInButton.setEnabled(true);
                    phonebutton.setEnabled(true);
                    privacy.setTextColor(getResources().getColor(R.color.green));

                }
                else{
                    signInButton.setEnabled(false);
                    phonebutton.setEnabled(false);
                    privacy.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        // Set the dimensions of the sign-in button.

        signInButton.setSize(SignInButton.SIZE_STANDARD);
        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        phonebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this,PhoneAuthActivity.class));
                finish();
            }
        });


    }
    private void signIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if(user!=null){
            mGoogleSignInClient.signOut();
            FirebaseFirestore.getInstance().collection("users").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(!task.getResult().exists()){
                        FirebaseFirestore.getInstance().collection("users").document(user.getUid()).set(userProfile.giveNewUser());
                        startActivity(new Intent(loginActivity.this, CreateProfile.class));
                        finish();
                    }
                    else{
                        startActivity(new Intent(loginActivity.this,MainActivity.class));
                        finish();
                    }
                }
            });
        }
        else{
            Toast.makeText(loginActivity.this,"there is some error in logging into your account",Toast.LENGTH_LONG).show();
        }
    }
}