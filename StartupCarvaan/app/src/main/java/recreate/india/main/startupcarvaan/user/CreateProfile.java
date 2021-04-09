package recreate.india.main.startupcarvaan.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

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

import java.io.File;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.compressor.Compressor;
import recreate.india.main.startupcarvaan.mainActivities.MainActivity;

public class CreateProfile extends AppCompatActivity {
    private static final int CROP_PIC_REQUEST_CODE = 001 ;
    private EditText display_name,title,desc,phone,email,address;
    private ImageView userImage;
    private Button document,submit;
    private Uri imageUri,documentUri;

    private FirebaseFirestore ff=FirebaseFirestore.getInstance();
    private FirebaseStorage fs= FirebaseStorage.getInstance();
    private String imageurl="",documenturl="";
    private profile profile=new profile();
    private int document_request_code=002;
    private File compressFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        //edit text declaration
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        display_name=findViewById(R.id.displayName);
        title=findViewById(R.id.title);
        desc=findViewById(R.id.description);
        phone=findViewById(R.id.userPhone);
        email=findViewById(R.id.userEmail);
        address=findViewById(R.id.userAddress);
        // end here

        imageurl=profile.getImageurl();
        documenturl=profile.getResume();
        //getting current user all data from firestore
        ff.collection("users")
                .document(user.getUid())
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        profile=value.toObject(recreate.india.main.startupcarvaan.user.profile.class);
                        //setting all the fields first
                        display_name.setText(profile.getName());
                        title.setText(profile.getTitle());
                        desc.setText(profile.getDescription());
                        phone.setText(profile.getPhone());
                        email.setText(profile.getEmail());
                        address.setText(profile.getAddress());
                    }
                });
        //end here

        //image upload work
        userImage=findViewById(R.id.userImage);
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, CROP_PIC_REQUEST_CODE);
            }
        });
        //end here

        //document upload work
        document=findViewById(R.id.document);
        document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

                // We will be redirected to choose pdf
                galleryIntent.setType("application/pdf");
                startActivityForResult(galleryIntent, document_request_code);
            }
        });
        //end here


        //submitting the data
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog newDailog = new Dialog(CreateProfile.this);
                if(imageUri!=null){
                    StorageReference userimage=fs.getReference().child("users").child(user.getUid()).child("image");
                    imageurl=userimage.getPath();
                    userimage.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            Toast.makeText(CreateProfile.this, "image successfully uploaded", Toast.LENGTH_SHORT).show();
                            if(documentUri!=null){
                                StorageReference userdoc=fs.getReference().child("users").child(user.getUid()).child("doc");
                                documenturl=userdoc.getPath();
                                userdoc.putFile(documentUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                        Toast.makeText(CreateProfile.this, "document successfully", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            startActivity(new Intent(CreateProfile.this, MainActivity.class));
                            finish();
                        }
                    });
                }
                else{
                    if(documentUri!=null){
                        StorageReference userdoc=fs.getReference().child("users").child(user.getUid()).child("doc");
                        documenturl=userdoc.getPath();
                        userdoc.putFile(documentUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                Toast.makeText(CreateProfile.this, "document successfully uploaded ", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(CreateProfile.this, MainActivity.class));
                                finish();
                            }
                        });
                    }
                }

                    //getting all the required fields
                    String name_tobeuploaded=display_name.getText().toString();
                    String title_tobeuploaded=title.getText().toString();
                    String desc_tobeuploaded=desc.getText().toString();
                    String phone_tobeuploaded=phone.getText().toString();
                    String email_tobeuploaded=email.getText().toString();
                    String address_tobeuploaded=address.getText().toString();

                    profile.setName(name_tobeuploaded);
                    profile.setAddress(address_tobeuploaded);
                    profile.setDescription(desc_tobeuploaded);
                    profile.setEmail(email_tobeuploaded);
                    profile.setPhone(phone_tobeuploaded);
                    profile.setTitle(title_tobeuploaded);
                    if(imageUri!=null)
                        profile.setImageurl(imageurl);
                    if(documentUri!=null)
                        profile.setResume(documenturl);

                    ff.collection("users")
                            .document(user.getUid())
                            .set(profile);
                    if(imageUri==null && documentUri==null)
                        startActivity(new Intent(CreateProfile.this, MainActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CROP_PIC_REQUEST_CODE && resultCode == RESULT_OK && null != data) {
            imageUri=data.getData();
            CropImage.activity(imageUri)
                    .setAspectRatio(1,1)
                    .setCropShape(CropImageView.CropShape.RECTANGLE)
                    .start(this);
//            userImage.setImageURI(imageUri);

        }
        else if(requestCode==document_request_code&& resultCode==RESULT_OK && data!=null){
            documentUri=data.getData();
            document.setText(documentUri.getLastPathSegment());
        }
        else if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageUri = result.getUri();
                userImage.setImageURI(imageUri);
            }
        }
        else{
            Toast.makeText(this, "file not selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}