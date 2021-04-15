package recreate.india.main.startupcarvaan.aboutshare.modals;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.aboutshare.models.coinExchange;
import recreate.india.main.startupcarvaan.aboutshare.models.sharedetails;

public class rates extends DialogFragment {

    private TextView rateText;
    private recreate.india.main.startupcarvaan.aboutshare.models.coinExchange coinExchange=new coinExchange();
    public rates() {
    }


    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.activity_recycler, null, false);
        // from intent
        Bundle bundle = getArguments();
        String rateID = bundle.getString("rateID");

        //assigning id to variables

        rateText = view.findViewById(R.id.rateText);


        // retrieving share details
        FirebaseFirestore.getInstance()
                .collection("rateExchange")
                .document("rateID")
                .collection("coinExchange").document("coinExchange")
               .addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                coinExchange = value.toObject(coinExchange.class);
                rateText.setText(String.valueOf("1 INR ="+ " "+ coinExchange.getExchangeRate()+ " RCI"));
            }
        });

        builder.setView(view);
        return builder.create();
    }
}
