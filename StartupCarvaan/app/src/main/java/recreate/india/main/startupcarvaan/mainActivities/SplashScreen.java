package recreate.india.main.startupcarvaan.mainActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.paperdb.Paper;
import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.loginsignup.loginActivity;

public class SplashScreen extends AppCompatActivity {
    private Button logout;
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
        Paper.book().write("version","1.10");
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(SplashScreen.this,MainActivity.class));
            finish();
        }
        else if(Paper.book().read("first")=="true") {
            startActivity(new Intent(SplashScreen.this, loginActivity.class));
            finish();
        }
        else{
            startActivity(new Intent(SplashScreen.this,IntroSliderActivity.class));
            finish();
        }
    }
    class object {
        int date;
        int quantity;
        int price;
        object(int date,int quantity,int price){
            this.date=date;
            this.quantity=quantity;
            this.price=price;
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Map<String, Array> holdings=new HashMap<>();

        FirebaseFirestore.getInstance().collection("random")
                .document("random")
                .set(holdings);
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
