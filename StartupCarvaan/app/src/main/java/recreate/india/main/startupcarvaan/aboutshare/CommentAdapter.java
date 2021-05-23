package recreate.india.main.startupcarvaan.aboutshare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.aboutshare.modals.name_comment;

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
        holder.comment.setText(display(commentArrayList.get(position).getName()));
        holder.username.setText(commentArrayList.get(position).getComment());

    }

    @Override
    public int getItemCount() {
        return commentArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView username,comment;

        public MyViewHolder(View itemview){

            super(itemview);
            comment=(TextView)itemview.findViewById(R.id.comment);
            username=(TextView)itemview.findViewById(R.id.displayname1);


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
