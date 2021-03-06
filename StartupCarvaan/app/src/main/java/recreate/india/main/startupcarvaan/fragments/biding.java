package recreate.india.main.startupcarvaan.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.allmodels.fragmentmodel.BiddingModel;
import recreate.india.main.startupcarvaan.allmodels.user.UserProfile;

public class biding extends Fragment {
    private RecyclerView myshare;
    private FirestoreRecyclerAdapter adapter;


    UserProfile userProfile = new UserProfile();
    private FirebaseFirestore ff = FirebaseFirestore.getInstance();
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private CustomProgressDialogue cpd;

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
        // 11-05-21 Written by Siddharth start
        cpd = new CustomProgressDialogue(getActivity());
        // 11-05-21 Written by Siddharth end
        View view = inflater.inflate(R.layout.fragment_biding, container, false);
        // 11-05-21 Written by Siddharth start
        // 11-05-21 Written by Siddharth end
        myshare = view.findViewById(R.id.bidingrecyclerview);
        Query query = ff.collection("bidding");
        FirestoreRecyclerOptions<BiddingModel> option = new FirestoreRecyclerOptions.Builder<BiddingModel>().setQuery(query, BiddingModel.class).build();
        adapter = new FirestoreRecyclerAdapter<BiddingModel, biding.viewholder>(option) {
            @NonNull
            @Override
            public biding.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_single_biding, parent, false);
                return new viewholder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull biding.viewholder holder, int position, @NonNull BiddingModel model) {
                String biddingid = getSnapshots().getSnapshot(position).getId();

                FirebaseStorage.getInstance().getReference().child(model.getProductimage()).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.getResult() != null)
                            Glide.with(view.getContext()).load(task.getResult()).placeholder(R.drawable.loginhy).into(holder.productimage);
                        cpd.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Glide.with(view.getContext()).load(R.drawable.loginhy).into(holder.productimage);
                    }
                });
                holder.timer.setText(model.getClosedin());
                holder.title.setText(model.getProducttitle());
                holder.description.setText(model.getProductdesc());
                holder.price.setText(String.valueOf(model.getCurrentbid()));
                holder.winner.setText(String.valueOf(model.getCurrentwinner()));
                holder.join.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s_price = holder.bid.getText().toString();
                        if (s_price.equals("")) {
                            Toast.makeText(getContext(), "enter bid amount greater than"+ String.format("%.2f",(1.1 * model.getCurrentbid())), Toast.LENGTH_SHORT).show();
                        } else if (s_price.contains("."))
                            holder.bid.setError("invalid amount");
                        else {
                            Integer price = Integer.valueOf(holder.bid.getText().toString());
                            if (Double.valueOf(price) > 1.1 * model.getCurrentbid()) {


                                ff.collection("users").document(firebaseUser.getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                                        if (value != null) {
                                            userProfile = value.toObject(UserProfile.class);
                                            Double bonus = userProfile.getBonus();
                                            String name = userProfile.getUsername();
                                            if (price < bonus) {
                                                Toast.makeText(getContext(), "you are currently a winner do not spend any bonus coin before next winner arrives", Toast.LENGTH_SHORT).show();

                                                ff.collection("bidding").document(biddingid).update("currentbid", price, "currentwinner", name, "winnerid", firebaseUser.getUid());
                                                // reduce the bonus
//                                                userProfile.setBonus(bonus-price);
//                                                ff.collection("users").document(firebaseUser.getUid()).update("bonus",userProfile.getBonus());


                                            } else {
                                                Toast.makeText(getContext(), "You dont have enough bonus coins", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                });

                                // TODO: check and change
                                /*

                                FirebaseFirestore.getInstance()
                                        .collection("users")
                                        .document(new user().user().getUid())
                                        .collection("others")
                                        .document("coins")
                                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                                        coin coin=task.getResult().toObject(coin.class);
                                                if(price<coin.getBonus()){
                                                    Toast.makeText(getContext(), "you are currently a winner do not spend any bonus coin before next winner arrives", Toast.LENGTH_SHORT).show();
                                                    String id=getSnapshots().getSnapshot(position).getId();
                                                    FirebaseFirestore.getInstance()
                                                            .collection("biding")
                                                            .document(id)
                                                            .update("winnerid",new user().user().getUid(),"currentbid",price);
                                                    new userfunctions().removeBonus(coin.getBonus(),price);
                                                }
                                                else{
                                                    Toast.makeText(getContext(), "you do not have enough coins ", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });


                                 */
                            } else {
                                Toast.makeText(getContext(), "enter bid amount greater than"+ String.format("%.2f", 1.1 * model.getCurrentbid()), Toast.LENGTH_SHORT).show();

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
        private TextView title, description, price, winner, join, timer;
        private EditText bid;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            productimage = itemView.findViewById(R.id.productimage);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            timer = itemView.findViewById(R.id.timer);

            price = itemView.findViewById(R.id.currentprice);
            winner = itemView.findViewById(R.id.currentwinner);
            bid = itemView.findViewById(R.id.bidprice);
            join = itemView.findViewById(R.id.join);
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