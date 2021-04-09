package recreate.india.main.startupcarvaan.fragments.models;

import com.google.firebase.firestore.FirebaseFirestore;

public class sharefunctions {
    private FirebaseFirestore ff=FirebaseFirestore.getInstance();
    public void removeAvailableSell(String shareid,Integer total,Integer share){
        ff.collection("allshares")
                .document(shareid)
                .collection("sharedetails")
                .document("sharedetails")
                .update("availableforselling",total-share);
    }
    public void removeAvailableBuy(String shareid,Integer total,Integer share) {
        ff.collection("allshares")
                .document(shareid)
                .collection("sharedetails")
                .document("sharedetails")
                .update("availableforbuying",total-share);
    }
    public void addSell(String shareid,Integer total,Integer share){
        ff.collection("allshares")
                .document(shareid)
                .collection("sharedetails")
                .document("sharedetails")
                .update("availableforselling",total+share);
    }
    public void addBuy(String shareid,Integer total,Integer share) {
        ff.collection("allshares")
                .document(shareid)
                .collection("sharedetails")
                .document("sharedetails")
                .update("availableforbuying",total+share);
    }

    public sharefunctions() {
    }
}
