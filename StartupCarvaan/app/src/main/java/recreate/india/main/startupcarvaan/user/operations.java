package recreate.india.main.startupcarvaan.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class operations {
    private FirebaseFirestore ff=FirebaseFirestore.getInstance();
    private FirebaseAuth fauth= FirebaseAuth.getInstance();
    private FirebaseUser user=fauth.getCurrentUser();
    private Map<Number,Number>holdings=new HashMap<>();

    Boolean isalreadyuser(String shareid){
        final boolean[] check = {false};
        ff.collection("users")
                .document(user.getUid())
                .collection("myshares")
                .document(shareid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.getResult().exists()){
                    check[0] =true;
                }
            }
        });
        return  check[0];
    }
    void getholdings(String shareid){
        ff.collection("users")
                .document(user.getUid())
                .collection("myshares")
                .document(shareid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                
            }
        });
    }
}
