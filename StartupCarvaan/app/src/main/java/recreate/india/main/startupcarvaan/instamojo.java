package recreate.india.main.startupcarvaan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.instamojo.android.Instamojo;

public class instamojo extends AppCompatActivity implements Instamojo.InstamojoPaymentCallback{
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instamojo);
        tv=findViewById(R.id.test);
        Instamojo.getInstance().initialize(this, Instamojo.Environment.PRODUCTION);
        Instamojo.getInstance().initiatePayment(this, "03d58c35-cab1-48f4-a1d3-ae7a338f807d", this);
    }

    @Override
    public void onInstamojoPaymentComplete(String s, String s1, String s2, String s3) {
        tv.setText("\n s: "+s+"\n s1: "+s1+"\n s2: "+s2+"\n s3: "+s3);
    }
    @Override
    public void onPaymentCancelled() {
        Toast.makeText(this, "cancelled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInitiatePaymentFailure(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}