    package recreate.india.main.startupcarvaan.aboutshare;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.aboutshare.modals.name_comment;
import recreate.india.main.startupcarvaan.aboutshare.models.blogdetails;
import recreate.india.main.startupcarvaan.allmodels.share.BloggingDetails;

    // Siddharth -->date :23rd may 2021
public class comment extends DialogFragment {

    FirebaseFirestore ff=FirebaseFirestore.getInstance();
    private RecyclerView commentList;
    private BloggingDetails blogdetails=new BloggingDetails();


    public comment(){}


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =getActivity().getLayoutInflater();
        final View view=inflater.inflate(R.layout.comment_dialogue,null,false);

        commentList=view.findViewById(R.id.comment_list);
        builder.setCancelable(true);
        final Map<String, String>[] comment_map = new Map[]{new HashMap<>()};
        Bundle bundle=getArguments();
        String blogid=bundle.getString("blogid");
        String shareid=bundle.getString("shareid");
        ff.collection("startup")
                .document(shareid)
                .collection("bloggings")
                .document(blogid)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                blogdetails=documentSnapshot.toObject(BloggingDetails.class);
                comment_map[0] =blogdetails.getComments();
                ArrayList<name_comment> content=new ArrayList<>();
                for (Map.Entry<String,String> set: comment_map[0].entrySet()) {
                    name_comment name = new name_comment(set.getKey(),set.getValue());
                    content.add(name);
                }
//                        Toast.makeText(getContext(), "size of comment map"+String.valueOf(comment_map[0].size()), Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getContext(), "size of content is"+String.valueOf(content.size()), Toast.LENGTH_SHORT).show();
                CommentAdapter commentAdapter=new CommentAdapter(getContext(),content);
                commentList.setAdapter(commentAdapter);
                commentList.setLayoutManager(new LinearLayoutManager(getContext()));
                DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
                commentList.addItemDecoration(dividerItemDecoration);
                    }
                });
        builder.setView(view);
        return builder.create();

    }
}
