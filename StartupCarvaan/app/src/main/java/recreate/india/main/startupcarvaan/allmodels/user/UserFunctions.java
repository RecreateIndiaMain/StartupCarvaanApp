package recreate.india.main.startupcarvaan.allmodels.user;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserFunctions {
    public UserProfile userProfile;
    public FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
    public FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    public FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

    public UserFunctions() {

    }
    public void getUserProfile() {
        firebaseFirestore.collection("users")
                .document(firebaseUser.getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                userProfile=documentSnapshot.toObject(UserProfile.class);
            }
        });
    }
}
