package recreate.india.main.startupcarvaan.fragments.myshares;

import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.aboutshare.blogging;
import recreate.india.main.startupcarvaan.aboutshare.models.sharedetails;
import recreate.india.main.startupcarvaan.fragments.allshares.allshare;
import recreate.india.main.startupcarvaan.fragments.progressdialogue.CustomProgressDialogue;
import recreate.india.main.startupcarvaan.user.user;

public class myshares extends Fragment {
    private RecyclerView myshare;
    private FirestoreRecyclerAdapter adapter;
    private CustomProgressDialogue cpd;
    public myshares() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_myshares, container, false);
        myshare=view.findViewById(R.id.mysharerecyclerview);

        cpd= new CustomProgressDialogue(getActivity());
        Query query= FirebaseFirestore.getInstance().collection("users").document(new user().user().getUid()).collection("myshares");
        FirestoreRecyclerOptions<holdings> option=new FirestoreRecyclerOptions.Builder<holdings>().setQuery(query,holdings.class).build();
        adapter= new FirestoreRecyclerAdapter<holdings, viewholder>(option) {
            @NonNull
            @Override
            public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.z_single_myshare,parent,false);
                return new viewholder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull viewholder holder, int position, @NonNull holdings model) {
                cpd.show();
                List<String> list = new ArrayList<String>();
                list.add(0, "Price of a share : No. of shares");
                Map<String,Integer> holding=model.getHoldings();
                for (Map.Entry<String,Integer> entry : holding.entrySet())
                    list.add(String.valueOf(entry.getKey()+" : "+entry.getValue()));
                final String[] item = new String[1];
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                holder.spin1.setAdapter(arrayAdapter);
                holder.spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        if (parent.getItemAtPosition(position).equals("Price of a share : No. of shares")) {
//                        } else {
//                            item[0] = parent.getItemAtPosition(position).toString();
//                            int i;
//                            for(i=0; i< item[0].length(); i++){
//                                if(item[0].charAt(i)==' '){
//                                    break;
//                                }
//                                price_+= item[0].charAt(i);
//                            }
//                            i+=3    ;
//                            share_= item[0].substring(i, item[0].length());
//                            price.setText("price: "+price_);
//                            share.setText(share_);
//                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                String shareid=getSnapshots().getSnapshot(position).getId();
                final sharedetails[] sharedetails = {new sharedetails()};
                final allshare[] allshare = {new allshare()};
                FirebaseFirestore.getInstance().collection("allshares")
                        .document(shareid)
                        .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                                allshare[0] =value.toObject(allshare.class);
                                holder.sharename.setText(allshare[0].getName());
                                FirebaseStorage.getInstance().getReference().child(allshare[0].getLogourl()).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        if(task.isSuccessful()){
                                            Glide.with(getContext()).load(task.getResult()).into(holder.companylogo);
                                            cpd.dismiss();
                                        }
                                    }
                                });
                            }
                        });
                FirebaseFirestore.getInstance().collection("allshares")
                        .document(shareid)
                        .collection("sharedetails")
                        .document("sharedetails")
                        .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                                sharedetails[0] =value.toObject(sharedetails.class);
                                holder.buyp.setText(String.valueOf(sharedetails[0].getBuyingprice()));
                                holder.sellp.setText(String.valueOf(sharedetails[0].getSellingprice()));
                            }
                        });

                // loading values
                holder.trade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getContext().startActivity(new Intent(getContext(), blogging.class).putExtra("shareid",shareid));
                    }
                });

            }
        };
        myshare.setAdapter(adapter);
        myshare.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    private class viewholder extends RecyclerView.ViewHolder {
        private CircleImageView companylogo;
        private TextView sharename,buyp,sellp;
        private Button trade;
        private Spinner spin1;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            sharename=itemView.findViewById(R.id.sharename);
            companylogo=itemView.findViewById(R.id.companylogo);
            buyp=itemView.findViewById(R.id.buyingPrice);
            sellp=itemView.findViewById(R.id.sellingPrice);
            trade=itemView.findViewById(R.id.trade);
            spin1=itemView.findViewById(R.id.spinner4);
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