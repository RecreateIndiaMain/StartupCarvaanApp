package recreate.india.main.startupcarvaan.user;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class usermodel {
    private FirebaseFirestore ff=FirebaseFirestore.getInstance();
    private FirebaseAuth fauth= FirebaseAuth.getInstance();
    private FirebaseUser user=fauth.getCurrentUser();
    boolean isuserlogin(){
        if(this.user!=null)
            return true;
        return false;
    }
    FirebaseUser getuser(){
        return this.user;
    }
    coin coindetails(){
        final coin[] coinmodel = new coin[1];
        ff.collection("users")
                .document(user.getUid())
                .collection("others")
                .document("coins").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
               coinmodel[0] =value.toObject(coin.class);
            }
        });
        return coinmodel[0];
    }
    profile profiledetails(){
        final profile[] profilemodel = new profile[1];
        ff.collection("users")
                .document(user.getUid())
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        profilemodel[0] =value.toObject(profile.class);
                    }
                });
        return profilemodel[0];
    }

}
