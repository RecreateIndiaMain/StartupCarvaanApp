package recreate.india.main.startupcarvaan;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.telecom.Call;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import recreate.india.main.startupcarvaan.aboutshare.blogging;
import recreate.india.main.startupcarvaan.mainActivities.MainActivity;
import recreate.india.main.startupcarvaan.user.profile;
import recreate.india.main.startupcarvaan.user.user;

public class Levels extends AppCompatActivity {
    ProgressBar pro;
    int status;
    // Date: 19th May 2021
    // Written By Siddharth
    private int coins = 250,level;
    private ProgressBar l[]=new ProgressBar[12];
    private TextView lt[]=new TextView[12];
    private TextView rt[]=new TextView[12];
    private TextView txt3[]=new TextView[12];
    private ImageView img[]=new ImageView[12];
    private  int []levels={0,100,500,1000,2000,5000,10000,20000,30000,40000,50000,70000,850000,100000};
//    private Double TestPoints[]={0d,80d,400d,950d,1500d};
    FirebaseFirestore ff=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        pro = findViewById(R.id.progressid);
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
////                startProgress();
//            }
//        });
//        thread.start();


        initialise();

//        showLevel(5,4000d);
//        l[1].setProgress(100);
//        l[2].setProgress(80);
        ff.collection("users")
                .document(new user().user().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                profile profile=task.getResult().toObject(profile.class);
                showLevel(profile.getI(),profile.getCurrentpoints());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Levels.this, "Failed to load\n Check you Internet Connection and try again ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void initialise() {
        // Progress Bars
        l[1]=findViewById(R.id.progressid);
        l[2]=findViewById(R.id.progressid2);
        l[3]=findViewById(R.id.progressid3);
        l[4]=findViewById(R.id.progressid4);
        l[5]=findViewById(R.id.progressid5);
        l[6]=findViewById(R.id.progressid6);
        l[7]=findViewById(R.id.progressid7);
        l[8]=findViewById(R.id.progressid8);
        l[9]=findViewById(R.id.progressid9);
        l[10]=findViewById(R.id.progressid10);
        l[11]=findViewById(R.id.progressid11);
        // Text Views
        lt[1]=findViewById(R.id.L1txt1);
        rt[1]=findViewById(R.id.L1txt2);
        lt[2]=findViewById(R.id.L2txt1);
        rt[2]=findViewById(R.id.L2txt2);
        lt[3]=findViewById(R.id.L3txt1);
        rt[3]=findViewById(R.id.L3txt2);
        lt[4]=findViewById(R.id.L4txt1);
        rt[4]=findViewById(R.id.L4txt2);
        lt[5]=findViewById(R.id.L5txt1);
        rt[5]=findViewById(R.id.L5txt2);
        lt[6]=findViewById(R.id.L6txt1);
        rt[6]=findViewById(R.id.L6txt2);
        lt[7]=findViewById(R.id.L7txt1);
        rt[7]=findViewById(R.id.L7txt2);
        lt[8]=findViewById(R.id.L8txt1);
        rt[8]=findViewById(R.id.L8txt2);
        lt[9]=findViewById(R.id.L9txt1);
        rt[9]=findViewById(R.id.L9txt2);
        lt[10]=findViewById(R.id.L10txt1);
        rt[10]=findViewById(R.id.L10txt2);
        lt[11]=findViewById(R.id.L11txt1);
        rt[11]=findViewById(R.id.L11txt2);

        //Locks Image Views
        img[1]=findViewById(R.id.L1Lock);
        img[2]=findViewById(R.id.L2Lock);
        img[3]=findViewById(R.id.L3Lock);
        img[4]=findViewById(R.id.L4Lock);
        img[5]=findViewById(R.id.L5Lock);
        img[6]=findViewById(R.id.L6Lock);
        img[7]=findViewById(R.id.L7Lock);
        img[8]=findViewById(R.id.L8Lock);
        img[9]=findViewById(R.id.L9Lock);
        img[10]=findViewById(R.id.L10Lock);
        img[11]=findViewById(R.id.L11Lock);
        // Congratulations Text.....
        txt3[1]=findViewById(R.id.L1txt3);
        txt3[2]=findViewById(R.id.L2txt3);
        txt3[3]=findViewById(R.id.L3txt3);
        txt3[4]=findViewById(R.id.L4txt3);
        txt3[5]=findViewById(R.id.L5txt3);
        txt3[6]=findViewById(R.id.L6txt3);
        txt3[7]=findViewById(R.id.L7txt3);
        txt3[8]=findViewById(R.id.L8txt3);
        txt3[9]=findViewById(R.id.L9txt3);
        txt3[10]=findViewById(R.id.L10txt3);
        txt3[11]=findViewById(R.id.L11txt3);


        for (int i=1;i<12;i++){
            rt[i].setText("/ "+ levels[i]);
            lt[i].setText("0");
            img[i].setImageResource(R.drawable.padlock);
//            l[i].setMax(levels[i]);
        }

    }

    public void showLevel(int level, Double currentPoints){
        int IntValue;
        for (int i=1;i<level;i++){
            l[i].setProgress(100);
             IntValue = (int) Math. round(levels[i]);
            lt[i].setText(String.valueOf(IntValue));
            img[i].setImageResource(R.drawable.unlock);
            txt3[i].setText("Congratulations..");
        }
        double Points=currentPoints/levels[level]*100;
        IntValue = (int) Math. round(Points);
        l[level].setProgress(IntValue);
        IntValue = (int) Math. round(currentPoints);
        lt[level].setText(String.valueOf(IntValue));
    }
    public void startProgress() {
        status = 40;
        try {
            Thread.sleep(50);
            pro.setProgress(status);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Levels.this, MainActivity.class));
    }
}