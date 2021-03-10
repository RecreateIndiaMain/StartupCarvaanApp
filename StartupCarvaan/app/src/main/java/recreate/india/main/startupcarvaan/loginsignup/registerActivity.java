package recreate.india.main.startupcarvaan.loginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import recreate.india.main.startupcarvaan.mainActivities.MainActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import recreate.india.main.startupcarvaan.R;

public class registerActivity extends AppCompatActivity {
    private FirebaseAuth fauth=FirebaseAuth.getInstance();
    private FirebaseFirestore ff=FirebaseFirestore.getInstance();


    private EditText username,password,cpassword;
    private TextView signin;
    private ImageView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        cpassword=findViewById(R.id.confirmpassword);
        signin=findViewById(R.id.gotologin);
        register=findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fauth.createUserWithEmailAndPassword(username.getText().toString()+"@gmail.com",password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    createUser(username.getText().toString());
                                    startActivity(new Intent(registerActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registerActivity.this, loginActivity.class));
                finish();
            }
        });
    }
    Boolean isAvailable(String username){
        DocumentReference usernameref=ff.collection("username").document(username);
        if(usernameref.get().getResult().exists())
            return false;
        return true;

    }
    void createUser(String username){
        String userid=fauth.getCurrentUser().getUid();
        Map<String,String>username_m=new HashMap<>();
        username_m.put("username",username);
        ff.collection("username").document(username).set(username_m);
        Map<String,String>profile=new HashMap<>();
        profile.put("name","name");
        ff.collection("users").document(userid).set(profile);
    }
}