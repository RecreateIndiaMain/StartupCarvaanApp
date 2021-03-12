package recreate.india.main.startupcarvaan.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;

import android.webkit.WebView;

import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;



import recreate.india.main.startupcarvaan.R;

public class ProfileActivity extends AppCompatActivity {
    private ImageView user_image;
    private TextView name,title,points,level,desc,phone,email,address;
    private WebView webView;
    private FirebaseFirestore ff=FirebaseFirestore.getInstance();
    private FirebaseStorage fs= FirebaseStorage.getInstance();

    private profile profile=new profile();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_main);
        webView=findViewById(R.id.webview);
        user_image=findViewById(R.id.userImage);
        name=findViewById(R.id.name);
        title=findViewById(R.id.title);
        points=findViewById(R.id.points);
        level=findViewById(R.id.level);
        desc=findViewById(R.id.description);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        address=findViewById(R.id.address);

        ff.collection("users")
                .document("fVL1fk8rYrIONx7qp2pP")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        profile=value.toObject(recreate.india.main.startupcarvaan.user.profile.class);
                        //setting all the fields first
                        name.setText(profile.getName());
                        title.setText(profile.getTitle());
                        desc.setText(profile.getDescription());
                        phone.setText(profile.getPhone());
                        email.setText(profile.getEmail());
                        address.setText(profile.getAddress());
                        level.setText(profile.getRank());
                        points.setText(String.valueOf(profile.getPoints()));
                        StorageReference imageurl=fs.getReference().child(profile.getImageurl());
                        imageurl.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if(task.getResult()!=null)
                                    Glide.with(ProfileActivity.this)
                                            .load(task.getResult())
                                            .into(user_image);
                                else{
                                    Toast.makeText(ProfileActivity.this, "file does not exists", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });


    }
}
