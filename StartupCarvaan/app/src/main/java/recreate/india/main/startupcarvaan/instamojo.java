package recreate.india.main.startupcarvaan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import com.instamojo.android.Instamojo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.http.Url;

public class instamojo extends AppCompatActivity{
    WebView payment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instamojo);
        payment=findViewById(R.id.payment);
        payment.getSettings().setJavaScriptEnabled(true);
        payment.loadUrl("https://www.instamojo.com/@startup_carvaan/b07d022524284bb291f0c5df09a32b27");
    }
}