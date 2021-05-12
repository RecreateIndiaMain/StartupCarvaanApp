package recreate.india.main.startupcarvaan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

public class Levels extends AppCompatActivity {
ProgressBar pro;
int status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        pro=findViewById(R.id.progressid);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                startProgress();
            }
        });
        thread.start();

    }

    public void startProgress() {
        status=40;
        try {
            Thread.sleep(50);
            pro.setProgress(status);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}