package recreate.india.main.startupcarvaan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.instamojo.android.Instamojo;

public class instamojo extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instamojo);
        String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
        int GOOGLE_PAY_REQUEST_CODE = 123;

        Uri uri =
                new Uri.Builder()
                        .scheme("upi")
                        .authority("pay")
                        .appendQueryParameter("pa", "goyalanubhav2@ybl")
                        .appendQueryParameter("pn", "Startup Carvaan")
                        .appendQueryParameter("mc", "BCR2DN6TZ7ENB4ZW")
                        .appendQueryParameter("tr", "817171711771171717171")
                        .appendQueryParameter("tn", "your-transaction-note")
                        .appendQueryParameter("am", "11")
                        .appendQueryParameter("cu", "INR")
                        .appendQueryParameter("url", "your-transaction-url")
                        .build();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setPackage(GOOGLE_PAY_PACKAGE_NAME);
        this.startActivityForResult(intent, GOOGLE_PAY_REQUEST_CODE);
    }
}