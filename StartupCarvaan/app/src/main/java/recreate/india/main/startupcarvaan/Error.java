package recreate.india.main.startupcarvaan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Error extends AppCompatActivity {

    TextView textView;
    FirebaseFirestore ff=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        textView=findViewById(R.id.textView7);
        textView.setVisibility(View.GONE);
        ff.collection("error").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
            textView.setVisibility(View.VISIBLE);
//                textView.setText("We will get back by "+task.getResult().toString());
            }
        });
 

    }
}