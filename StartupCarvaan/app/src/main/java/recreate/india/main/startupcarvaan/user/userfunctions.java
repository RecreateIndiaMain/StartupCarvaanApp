package recreate.india.main.startupcarvaan.user;

import com.google.firebase.firestore.FirebaseFirestore;

public class userfunctions {
    private FirebaseFirestore ff=FirebaseFirestore.getInstance();
    public void removeRci(Double current,Double rci){
        ff.collection("users")
                .document("tupjdAJB8JcfMdzqc4P5iRIg0XE2")
                .collection("others")
                .document("coins")
                .update("rci",current-rci);
    }
    public void addRci(Double current,Double rci){
        ff.collection("users")
                .document("tupjdAJB8JcfMdzqc4P5iRIg0XE2")
                .collection("others")
                .document("coins")
                .update("rci",current+rci);
    }
}
