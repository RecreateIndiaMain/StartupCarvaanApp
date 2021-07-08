package recreate.india.main.startupcarvaan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.instamojo.android.Instamojo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import retrofit2.http.Url;

public class instamojo extends AppCompatActivity{
    WebView payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instamojo);
        payment = findViewById(R.id.payment);
        payment.getSettings().setJavaScriptEnabled(true);
        // some button . click
        //
        FirebaseFirestore.getInstance().collection("users")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("payment")
                .document("payment")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable @org.jetbrains.annotations.Nullable DocumentSnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                        if (value.getBoolean("hasNewPayment")) {
                            payment.loadUrl(value.getString("link"));
                        }
                    }
                });
    }
    void btn_add(){
        Map<String, Object>m=new HashMap<>();
            m.put("hasNewPayment",true);
            m.put("link","alelelelelel");
        FirebaseFirestore.getInstance().collection("payments")
                .document()
                .set(m);
        FirebaseFirestore.getInstance().collection("users")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("payment")
                .document("payment")
                .set(m);
    }
}