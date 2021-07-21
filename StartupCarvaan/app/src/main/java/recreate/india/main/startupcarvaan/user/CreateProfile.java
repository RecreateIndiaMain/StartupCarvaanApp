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
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.jetbrains.annotations.NotNull;

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
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String imageurl = "";
    private UserProfile profile = new UserProfile();
    private UserProfile userProfile = new UserProfile();
    private CustomProgressDialogue cpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        //edit text declaration
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        cpd = new CustomProgressDialogue(CreateProfile.this);

        initialise();


        //getting current user all data from firestore
        /*    Not needed now as Update profile is created
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

         */
        display_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")) {
                    FirebaseFirestore.getInstance().collection("usernames")
                            .document(s.toString())
                            .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                            if (task.getResult().exists()) {
                                Toast.makeText(CreateProfile.this, "this user name is already taken please find another one for you", Toast.LENGTH_SHORT).show();
                            } else {
                                final_username = s.toString();
                            }
                        }
                    });
                }

            }
        });
        //image upload work

        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, CROP_PIC_REQUEST_CODE);
            }
        });
        //end here


        //submitting the data


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (display_name.getText().toString().isEmpty()) {
                    showError(display_name, "Name is mandatory");
                } else {
//                    OldSubmit();
                    newSubmit();
                }
            }
        });
    }

    private void newSubmit() {
        Map<String, String> m = new HashMap<>();
        m.put("userid", FirebaseAuth.getInstance().getCurrentUser().getUid());
        FirebaseFirestore.getInstance().collection("usernames")
                .document(final_username)
                .set(m);

        String username1 = display_name.getText().toString();
        String title1 = title.getText().toString();
        String number = phone.getText().toString();
        String about1 = desc.getText().toString();
        String address1 = address.getText().toString();
        String email1 = email.getText().toString();
        String aadhar1 = aadharNumber.getText().toString();

        if (imageUri != null) {
            cpd.setCancelable(false);
            fs.getReference().child("users").putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        fs.getReference().child("users").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                userProfile.setImageurl(uri.toString());
                                
                                userProfile.setUsername(final_username);
                                if (!title1.equals(""))
                                    userProfile.setTitle(title1);
                                if (number.length() > 0 && number.length() != 10)
                                    phone.setError("Please enter a valid phone number");
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
                                        startActivity(new Intent(CreateProfile.this, MainActivity.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(CreateProfile.this, "Please try again later...", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                }
            });
        } else {
            userProfile.setUsername(final_username);
            if (!title1.equals(""))
                userProfile.setTitle(title1);
            if (number.length() > 0 && number.length() != 10)
                phone.setError("Please enter a valid phone number");
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
                    startActivity(new Intent(CreateProfile.this, MainActivity.class));
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(CreateProfile.this, "Please try again later...", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
    private void OldSubmit() {

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

    private void initialise() {
        display_name = findViewById(R.id.displayName);
        title = findViewById(R.id.title);
        desc = findViewById(R.id.description);
        phone = findViewById(R.id.userPhone);
        email = findViewById(R.id.userEmail);
        address = findViewById(R.id.userAddress);
        userImage = findViewById(R.id.userImage);
        aadharNumber = findViewById(R.id.adharcard);
        userImage = findViewById(R.id.userImage);
        submit = findViewById(R.id.submit);
        imageurl = profile.getImageurl();
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
            userImage.setImageURI(imageUri);

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