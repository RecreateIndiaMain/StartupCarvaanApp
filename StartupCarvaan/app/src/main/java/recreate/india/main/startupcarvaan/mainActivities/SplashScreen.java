package recreate.india.main.startupcarvaan.mainActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import io.paperdb.Paper;
import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.loginsignup.loginActivity;
import recreate.india.main.startupcarvaan.user.User;

public class SplashScreen extends AppCompatActivity {
    private Button logout;
    public User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // WRITTEN BY VISHNU

        super.onCreate(savedInstanceState);
        Paper.init(SplashScreen.this);

        setContentView(R.layout.activity_splash_screen);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gotonextpage();
            }
        }, 1000);

    }
    private void gotonextpage() {
        if(Paper.book().contains("isFirst")){
            if(user.getUser()!=null){
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
            else{
                startActivity(new Intent(SplashScreen.this, recreate.india.main.startupcarvaan.loginsignup.loginActivity.class));
                finish();
            }
        }
        else {
            startActivity(new Intent(SplashScreen.this, IntroSliderActivity.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Paper.book().write("version","1.20");
    }
        // COMMENTED OUT LOGOUT CODE FOR NOW
//        logout=findViewById(R.id.logout);
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(SplashScreen.this, loginActivity.class));
//                finish();
//            }
//        });


        // WRITTEN BY VISHNU
    }
