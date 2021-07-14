package recreate.india.main.startupcarvaan.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.allmodels.user.UserProfile;
import recreate.india.main.startupcarvaan.fragments.CustomProgressDialogue;
import recreate.india.main.startupcarvaan.mainActivities.MainActivity;

public class CreateProfile extends AppCompatActivity {
    private static final int CROP_PIC_REQUEST_CODE = 001;
    private EditText display_name, title, desc, phone, email, address, aadharNumber;
    private ImageView userImage;
    private Button document, submit;
    private Uri imageUri;
    private String final_username = "";
    private FirebaseFirestore ff = FirebaseFirestore.getInstance();
    private FirebaseStorage fs = FirebaseStorage.getInstance();
    private String imageurl = "", documenturl = "";
    private UserProfile profile = new UserProfile();
    private int document_request_code = 002;
    private File compressFile;
    private CustomProgressDialogue cpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        //edit text declaration
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        cpd = new CustomProgressDialogue(CreateProfile.this);
        display_name = findViewById(R.id.displayName);
        title = findViewById(R.id.title);
        desc = findViewById(R.id.description);
        phone = findViewById(R.id.userPhone);
        email = findViewById(R.id.userEmail);
        address = findViewById(R.id.userAddress);
        userImage = findViewById(R.id.userImage);
        aadharNumber = findViewById(R.id.adharcard);
        imageurl = profile.getImageurl();
//        documenturl = profile.getResume();
        //getting current user all data from firestore
        ff.collection("users")
                .document(user.getUid())
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        profile = value.toObject(UserProfile.class);
                        //setting all the fields first
                        display_name.setHint(profile.getUsername());
                        title.setHint(profile.getTitle());
                        desc.setHint(profile.getDescription());
                        phone.setHint(profile.getPhonenumber());
                        email.setHint(profile.getEmail());
                        address.setHint(profile.getAddress());
                        aadharNumber.setHint(profile.getAddharnumber());
                    }
                });
        //end here
        display_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                FirebaseFirestore.getInstance().collection("usernames")
                        .document(s.toString())
                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                        if (task.getResult().exists()) {
                            Toast.makeText(CreateProfile.this, "this user name is already taken please find another one for you", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CreateProfile.this, "username available", Toast.LENGTH_SHORT).show();
                            final_username = s.toString();
                        }
                    }
                });
            }
        });
        //image upload work
        userImage = findViewById(R.id.userImage);
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, CROP_PIC_REQUEST_CODE);
            }
        });
        //end here

        //document upload work
        //end here


        //submitting the data
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (display_name.getText().toString().isEmpty()) {
                    showError(display_name, "Name is mandatory");
                } else if (phone.getText().toString().isEmpty()) {
                    showError(phone, "Your number is mandatory");
                } else if (address.getText().toString().isEmpty()) {
                    showError(address, "Address details are required");
                } else if (phone.getText().toString().length() != 10) {
                    showError(phone, "Invalid phone number");
                } else {
                    cpd.show();
                    if (imageUri != null) {
                        StorageReference userimage = fs.getReference().child("users").child(user.getUid()).child("image");
                        imageurl = userimage.getPath();
                        userimage.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                Toast.makeText(CreateProfile.this, "image successfully uploaded", Toast.LENGTH_SHORT).show();
                                cpd.dismiss();
                                String name_tobeuploaded = display_name.getText().toString().length() == 0 ? profile.getUsername() : display_name.getText().toString();
                                String title_tobeuploaded = title.getText().toString().length() == 0 ? profile.getTitle() : title.getText().toString();
                                String desc_tobeuploaded = desc.getText().toString().length() == 0 ? profile.getDescription() : desc.getText().toString();
                                String phone_tobeuploaded = phone.getText().toString().length() == 0 ? profile.getPhonenumber() : phone.getText().toString();
                                String email_tobeuploaded = email.getText().toString().length() == 0 ? profile.getEmail() : email.getText().toString();
                                String address_tobeuploaded = address.getText().toString().length() == 0 ? profile.getAddress() : address.getText().toString();
                                String aadhar_tobeuploaded = aadharNumber.getText().toString().length() == 0 ? profile.getAddharnumber() :
                                        aadharNumber.getText().toString();

                                profile.setUsername(name_tobeuploaded);
                                profile.setAddress(address_tobeuploaded);
                                profile.setDescription(desc_tobeuploaded);
                                profile.setEmail(email_tobeuploaded);
                                profile.setPhonenumber(phone_tobeuploaded);
                                profile.setTitle(title_tobeuploaded);
                                profile.setAddharnumber(aadhar_tobeuploaded);

                                if (imageUri != null)
                                    profile.setImageurl(imageurl);

                                ff.collection("users")
                                        .document(user.getUid())
                                        .set(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                                        startActivity(new Intent(CreateProfile.this, MainActivity.class));
                                    }
                                });

                                finish();
                            }
                        });
                    } else {
                        String name_tobeuploaded = display_name.getText().toString().length() == 0 ? profile.getUsername() : display_name.getText().toString();
                        String title_tobeuploaded = title.getText().toString().length() == 0 ? profile.getTitle() : title.getText().toString();
                        String desc_tobeuploaded = desc.getText().toString().length() == 0 ? profile.getDescription() : desc.getText().toString();
                        String phone_tobeuploaded = phone.getText().toString().length() == 0 ? profile.getPhonenumber() : phone.getText().toString();
                        String email_tobeuploaded = email.getText().toString().length() == 0 ? profile.getEmail() : email.getText().toString();
                        String address_tobeuploaded = address.getText().toString().length() == 0 ? profile.getAddress() : address.getText().toString();
                        String aadhar_tobeuploaded = aadharNumber.getText().toString().length() == 0 ? profile.getAddharnumber() :
                                aadharNumber.getText().toString();
                        profile.setUsername(name_tobeuploaded);
                        profile.setAddress(address_tobeuploaded);
                        profile.setDescription(desc_tobeuploaded);
                        profile.setEmail(email_tobeuploaded);
                        profile.setPhonenumber(phone_tobeuploaded);
                        profile.setTitle(title_tobeuploaded);
                        profile.setAddharnumber(aadhar_tobeuploaded);
                        if (imageUri != null)
                            profile.setImageurl(imageurl);
                        Map<String, String> m = new HashMap<>();
                        m.put("userid", FirebaseAuth.getInstance().getCurrentUser().getUid());
                        FirebaseFirestore.getInstance().collection("usernames")
                                .document(final_username)
                                .set(m);
                        ff.collection("users")
                                .document(user.getUid())
                                .set(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<Void> task) {
                                startActivity(new Intent(CreateProfile.this, MainActivity.class));
                            }
                        });
                    }
                }
            }
        });
    }

    private void showError(EditText field, String s) {
        field.setError(s);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CROP_PIC_REQUEST_CODE && resultCode == RESULT_OK && null != data) {
            imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setAspectRatio(1, 1)
                    .setCropShape(CropImageView.CropShape.RECTANGLE)
                    .setMaxCropResultSize(4000, 4000)
                    .start(this);
//            userImage.setImageURI(imageUri);

        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageUri = result.getUri();
                userImage.setImageURI(imageUri);
            }
        } else {
            Toast.makeText(this, "file not selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}