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
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
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

// Siddharth -->date :23rd may 2021
public class comment extends DialogFragment {

    FirebaseFirestore ff=FirebaseFirestore.getInstance();
    private RecyclerView commentList;
    private blogdetails blogdetails=new blogdetails();
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
        ff.collection("allshares")
                .document(shareid)
                .collection("blogs")
                .document(blogid)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable @org.jetbrains.annotations.Nullable DocumentSnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                        blogdetails=value.toObject(recreate.india.main.startupcarvaan.aboutshare.models.blogdetails.class);
                        comment_map[0] =blogdetails.getComments();
                    }
                });


        ArrayList<name_comment> content=new ArrayList<>();
//        if(comment_map[0]==null)
//            Toast.makeText(getActivity(), "NULLLLLLLL", Toast.LENGTH_SHORT).show();
//        name_comment name1 = new name_comment("Hello","HII");
//        name_comment name2 = new name_comment("Hello","HII");
//        name_comment name3 = new name_comment("Hello","HII");
//        name_comment name4 = new name_comment("Hello","HII");
//        name_comment name5 = new name_comment("Hello","HII");
//        name_comment name6 = new name_comment("Hello","HII");
//        name_comment name7 = new name_comment("Hello","HII");
//        content.add(name1);
//        content.add(name2);
//        content.add(name3);
//        content.add(name4);
//        content.add(name5);
//        content.add(name6);

        for (Map.Entry<String,String> set: comment_map[0].entrySet()) {
//            Toast.makeText(getContext(), set.getKey(), Toast.LENGTH_SHORT).show();
            name_comment name = new name_comment(set.getKey(),set.getValue());
//            Log.d("tag",name.getComment());
            content.add(name);
        }
            RecyclerView.Adapter commentAdapter=new CommentAdapter(getContext(),content);
            commentList.setAdapter(commentAdapter);
        builder.setView(view);
        return builder.create();

    }
}