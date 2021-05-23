package recreate.india.main.startupcarvaan.aboutshare;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import java.util.HashMap;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.aboutshare.models.blogdetails;

public class comment extends DialogFragment {

    FirebaseFirestore ff=FirebaseFirestore.getInstance();
    private ListView commentList;
    private blogdetails blogdetails=new blogdetails();
    public comment(){}

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =getActivity().getLayoutInflater();
        final View view=inflater.inflate(R.layout.comment_dialogue,null,false);

        Bundle bundle=getArguments();
        String blogid=bundle.getString("blogid");
        String shareid=bundle.getString("shareid");
        ff.collection("allshares")
                .document(shareid)
                .collection("blogs")
                .document(blogid)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable @org.jetbrains.annotations.Nullable DocumentSnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                        blogdetails=value.toObject(recreate.india.main.startupcarvaan.aboutshare.models.blogdetails.class);

                    }
                });

        builder.setView(view);
        return builder.create();

    }
}
