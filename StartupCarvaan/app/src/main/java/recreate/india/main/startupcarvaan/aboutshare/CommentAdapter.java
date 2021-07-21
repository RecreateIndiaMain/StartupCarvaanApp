package recreate.india.main.startupcarvaan.aboutshare;

import android.content.Context;
import android.gesture.GestureLibraries;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;
import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.aboutshare.modals.name_comment;
import recreate.india.main.startupcarvaan.allmodels.user.UserProfile;
import recreate.india.main.startupcarvaan.user.ProfileActivity;
import recreate.india.main.startupcarvaan.user.profile;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<name_comment> commentArrayList;
    private LayoutInflater inflater;

    public CommentAdapter(Context context, ArrayList<name_comment> commentArrayList) {
        this.context = context;
        this.commentArrayList = commentArrayList;
    }

    @NonNull
    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.singlecomment,parent,false);
        return new CommentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.MyViewHolder holder, int position) {
        String id = commentArrayList.get(position).getName().split("java")[0];
        final UserProfile[] profile = {new UserProfile()};
        FirebaseFirestore.getInstance().collection("users")
                .document(id).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable DocumentSnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                if(value!=null) {
                    profile[0] = value.toObject(UserProfile.class);
                    holder.comment.setText(display(commentArrayList.get(position).getComment()));
                    holder.username.setText(profile[0].getUsername());
                    try {
                        Glide.with(context).load(profile[0].getImageurl()).placeholder(R.drawable.userimage).into(holder.imageView);
                    } catch (Exception e) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
//                StorageReference imageurl= FirebaseStorage.getInstance().getReference().child(profile[0].getImageurl());
//                imageurl.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Uri> task) {
//                        if(task.getResult()!=null)
//                            Glide.with(context)
//                                    .load(task.getResult())
//                                    .into(holder.imageView);
//                        else{
//                            Toast.makeText(context, "file does not exists", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
            }
        });
    }
    @Override
    public int getItemCount() {
        return commentArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView username,comment;
        private CircleImageView imageView;

        public MyViewHolder(View itemview){

            super(itemview);
            comment=(TextView)itemview.findViewById(R.id.comment);
            username=(TextView)itemview.findViewById(R.id.displayname1);
            imageView=itemview.findViewById(R.id.image);


        }
    }
        public String display(String username){
            return  username;
        }

    // Siddharth -->date :23rd may 2021



//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//    convertView= LayoutInflater.from(context).inflate(R.layout.singlecomment,parent,false);
//        TextView name=(TextView) convertView.findViewById(R.id.displayname1);
//        TextView comment_desc=convertView.findViewById(R.id.comment);
//
//        String n=getItem(position).getName();
//        String c=getItem(position).getComment();
////        name_comment ex=new name_comment(n,c);
//        name.setText(n);
//        comment_desc.setText(c);
//        return convertView;
//    }




}
