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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.aboutshare.models.blogdetails;

public class comment extends DialogFragment {

    FirebaseFirestore ff=FirebaseFirestore.getInstance();
    private ListView commentList;
    public comment(){}

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =getActivity().getLayoutInflater();
        final View view=inflater.inflate(R.layout.comment_dialogue,null,false);

        Bundle bundle=getArguments();
        String shareid=bundle.getString("shareid");
        commentList=view.findViewById(R.id.comment_list);
        Query query=ff.collection("allshares").document(shareid).collection("blogs");
        FirestoreRecyclerOptions<blogdetails> option= new FirestoreRecyclerOptions.
                Builder<blogdetails>().setQuery(query,blogdetails.class).
                build();
        HashMap<String,String> comments_map=new HashMap<>();
        blogdetails model=new blogdetails();
        comments_map= (HashMap<String, String>) model.getComments();



        return super.onCreateDialog(savedInstanceState);

    }
}
