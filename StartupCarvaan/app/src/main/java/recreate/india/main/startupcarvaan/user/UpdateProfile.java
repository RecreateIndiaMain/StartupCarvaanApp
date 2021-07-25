package recreate.india.main.startupcarvaan.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.allmodels.user.UserProfile;
import recreate.india.main.startupcarvaan.mainActivities.MainActivity;

public class UpdateProfile extends AppCompatActivity {

    private static final int CROP_PIC_REQUEST_CODE = 001;
    private TextView username;
    private ImageView imageView;
    private EditText title, about, phoneNumber, email, address, aadhar;
    private Button submit;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore ff = FirebaseFirestore.getInstance();
    private UserProfile userProfile=new UserProfile();
    private Uri imageUri = null;
    private FirebaseStorage fs = FirebaseStorage.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        initialise();
        setTexts();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CROP_PIC_REQUEST_CODE);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });
    }


    private void updateProfile() {
        String title1 = title.getText().toString();
        String number = phoneNumber.getText().toString();
        String about1 = about.getText().toString();
        String address1 = address.getText().toString();
        String email1 = email.getText().toString();
        String aadhar1 = aadhar.getText().toString();

        if (imageUri != null) {
            fs.getReference().child("users").putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        fs.getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                userProfile.setImageurl(uri.toString());
                                if (!title1.equals(""))
                                    userProfile.setTitle(title1);
                                if (number.length() > 0 && number.length() != 10)
                                    phoneNumber.setError("Please enter a valid phone number");
                                else if (number.length() == 10)
                                    userProfile.setPhonenumber(number);
                                if (!about1.equals(""))
                                    userProfile.setDescription(about1);
                                if (!address1.equals(""))
                                    userProfile.setAddress(address1);
                                if (!email1.equals(""))
                                    userProfile.setEmail(email1);
                                if (!aadhar1.equals(""))
                                    userProfile.setAddress(aadhar1);

                                ff.collection("users").document(user.getUid()).set(userProfile).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        startActivity(new Intent(UpdateProfile.this, MainActivity.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(UpdateProfile.this, "Please try again later", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(UpdateProfile.this, MainActivity.class));
                                        finish();
                                    }
                                });
                            }
                        });
                    }
                }
            });
        }
        else{
            if (!title1.equals(""))
                userProfile.setTitle(title1);
            if (number.length() > 0 && number.length() != 10)
                phoneNumber.setError("Please enter a valid phone number");
            else if (number.length() == 10)
                userProfile.setPhonenumber(number);
            if (!about1.equals(""))
                userProfile.setDescription(about1);
            if (!address1.equals(""))
                userProfile.setAddress(address1);
            if (!email1.equals(""))
                userProfile.setEmail(email1);
            if (!aadhar1.equals(""))
                userProfile.setAddress(aadhar1);

            ff.collection("users").document(user.getUid()).set(userProfile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    startActivity(new Intent(UpdateProfile.this, MainActivity.class));
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UpdateProfile.this, "Please try again later", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateProfile.this, MainActivity.class));
                    finish();
                }
            });
        }


    }
    private void setTexts() {
        ff.collection("users").document(user.getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.exists()) {
                    userProfile = value.toObject(UserProfile.class);
                    username.setText(userProfile.getUsername());

//                    if (!userProfile.getImageurl().equals("/userImage.png"))
                        Glide.with(getBaseContext()).load(userProfile.getImageurl()).placeholder(R.drawable.userimage).into(imageView);
//                    else
//                        imageView.setImageResource(R.drawable.userimage);

                    // if no value is present then setting hint else setting the value given
                    if (!userProfile.getTitle().equals("your title"))
                        title.setText(userProfile.getTitle());

                    if (!userProfile.getPhonenumber().equals("your phone number"))
                        phoneNumber.setText(userProfile.getPhonenumber());

                    if (!userProfile.getEmail().equals("your professional email"))
                        email.setText(userProfile.getEmail());

                    if (!userProfile.getAddress().equals("your address"))
                        address.setText(userProfile.getAddress());

                    if (!userProfile.getAddharnumber().equals("your aadhar"))
                        aadhar.setText(userProfile.getAddharnumber());


                }
            }
        });
    }

    private void initialise() {

        username = findViewById(R.id.displayName_up);
        imageView = findViewById(R.id.userImage_up);
        title = findViewById(R.id.title_up);
        about = findViewById(R.id.description_up);
        phoneNumber = findViewById(R.id.userPhone_up);
        email = findViewById(R.id.userEmail_up);
        aadhar = findViewById(R.id.adharcard_up);
        submit = findViewById(R.id.submit_up);
        address=findViewById(R.id.userAddress_up);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CROP_PIC_REQUEST_CODE && resultCode == RESULT_OK && null != data) {
            imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setAspectRatio(1, 1)
                    .setCropShape(CropImageView.CropShape.RECTANGLE)
                    .setMaxCropResultSize(4000, 4000)
                    .start(this);
//            imageView.setImageURI(imageUri);

        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageUri = result.getUri();
                imageView.setImageURI(imageUri);
            }
        } else {
            Toast.makeText(this, "Photo not selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UpdateProfile.this, MainActivity.class));
        finish();
    }
}
