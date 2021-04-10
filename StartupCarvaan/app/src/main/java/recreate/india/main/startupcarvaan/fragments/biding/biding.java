package recreate.india.main.startupcarvaan.fragments.biding;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.fragments.mycoins.coin;
import recreate.india.main.startupcarvaan.user.user;

public class biding extends Fragment {
    private RecyclerView myshare;
    private FirestoreRecyclerAdapter adapter;
    public biding() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_biding, container, false);
        myshare=view.findViewById(R.id.bidingrecyclerview);
        Query query= FirebaseFirestore.getInstance().collection("biding");
        FirestoreRecyclerOptions<bidingm> option=new FirestoreRecyclerOptions.Builder<bidingm>().setQuery(query,bidingm.class).build();
        adapter= new FirestoreRecyclerAdapter<bidingm, biding.viewholder>(option) {
            @NonNull
            @Override
            public biding.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.z_single_biding,parent,false);
                return new viewholder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull biding.viewholder holder, int position, @NonNull bidingm model) {
                FirebaseStorage.getInstance().getReference().child(model.getProductimage()).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        Glide.with(getContext()).load(task.getResult()).into(holder.productimage);
                    }
                });
                holder.title.setText(model.getProducttitle());
                holder.description.setText(model.getProductdesc());
                holder.price.setText(String.valueOf(model.getCurrentbid()));
                holder.winner.setText(String.valueOf(model.getCurrentwinner()));
                holder.join.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s_price=holder.bid.getText().toString();
                        if(s_price.equals("")){
                            Toast.makeText(getContext(), "enter bid amount 10 percent greater then current price ", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Integer price=Integer.valueOf(holder.bid.getText().toString());
                            if(Double.valueOf(price)>Double.valueOf(model.getCurrentbid()+(.1*model.getCurrentbid()))){
                                FirebaseFirestore.getInstance()
                                        .collection("users")
                                        .document(new user().user().getUid())
                                        .collection("others")
                                        .document("coins")
                                        .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                            @Override
                                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                                                coin coin=value.toObject(coin.class);
                                                if(price<coin.getBonus()){
                                                    Toast.makeText(getContext(), "you are currently a winner do not spend any bonus coin before next winner arrives", Toast.LENGTH_SHORT).show();
                                                    String id=getSnapshots().getSnapshot(position).getId();
                                                    FirebaseFirestore.getInstance()
                                                            .collection("biding")
                                                            .document(id)
                                                            .update("winnerid",new user().user().getUid(),"currentbid",price);
                                                }
                                                else{
                                                    Toast.makeText(getContext(), "you do not have enough coins ", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                            else{
                                Toast.makeText(getContext(), "enter bid amount 10 percent greater then current price ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        };
        myshare.setAdapter(adapter);
        myshare.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    private class viewholder extends RecyclerView.ViewHolder {
        private ImageView productimage;
        private TextView title,description,price,winner,join;
        private EditText bid;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            productimage=itemView.findViewById(R.id.productimage);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);


            price=itemView.findViewById(R.id.currentprice);
            winner=itemView.findViewById(R.id.currentwinner);
            bid=itemView.findViewById(R.id.bidprice);
            join=itemView.findViewById(R.id.join);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}