package recreate.india.main.startupcarvaan.user;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class user {
    public FirebaseUser user(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }

}
