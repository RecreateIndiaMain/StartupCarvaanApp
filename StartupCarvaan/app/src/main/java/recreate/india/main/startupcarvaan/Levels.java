package recreate.india.main.startupcarvaan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.PropertyResourceBundle;

public class Levels extends AppCompatActivity {
    ProgressBar pro;
    int status;
    private int coins = 250,level;
    ProgressBar l2,l3,l4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        pro = findViewById(R.id.progressid);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
//                startProgress();
            }
        });
        thread.start();
        l2=findViewById(R.id.progressid2);
        l3=findViewById(R.id.progressid3);
        l4=findViewById(R.id.progressid4);

        level=coins/100 + 1;
        if(level>1){
        pro.setProgress(100);
        }
        else
            pro.setProgress(coins);
        if(level>2){
            l2.setProgress(100);
        }
        else{
            l2.setProgress(coins-100);
        }
        if(level>3){
            l3.setProgress(100);
        }
        else{
            l3.setProgress(coins-200);
        }
        if(level>4){
            l4.setProgress(100);
        }
        else{
            l4.setProgress(coins-300);
        }


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
}