package recreate.india.main.startupcarvaan.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.net.Inet4Address;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.aboutshare.modals.transaction_details;

public class pending_transaction extends Fragment {

    private RecyclerView recyclerView;
    private FirebaseUser user;
    private FirebaseFirestore ff = FirebaseFirestore.getInstance();
    private FirestoreRecyclerAdapter adapter;

    public pending_transaction() {
// Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = FirebaseAuth.getInstance().getCurrentUser();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending, container, false);

        recyclerView = view.findViewById(R.id.pending_transactions_recyclerView);

        Query query= ff.collection("users").document(user.getUid()).collection("transactions").orderBy("details");
        FirestoreRecyclerOptions<transaction_details> options=new FirestoreRecyclerOptions.Builder<transaction_details>().setQuery(query,transaction_details.class).build();
        adapter=new FirestoreRecyclerAdapter<transaction_details,PostViewHolder>(options) {
            @NonNull
            @Override
            public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view1=LayoutInflater.from(parent.getContext()).inflate(R.layout.single_transaction,parent,false);
                return new PostViewHolder(view1);
            }

            @Override
            protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull transaction_details model) {
                holder.startupname.setText(model.getStartupName());
                holder.quantity.setText(model.getQuantity());
                holder.price.setText(model.getPrice());
                float amount_share=Float.parseFloat(model.getPrice())*(Float.parseFloat(model.getQuantity()));
                holder.amount.setText(String.valueOf(amount_share));
                if(model.isBought()){
                    holder.bought.setText("Bought");
                }
                else{
                    holder.bought.setText("Sold");
                }
            }
        };

        return view;
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        private TextView startupname,quantity,price,amount,bought;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            startupname=itemView.findViewById(R.id.startup_name_trs);
            quantity=itemView.findViewById(R.id.quantity_trs);
            price=itemView.findViewById(R.id.price_trs);
            amount=itemView.findViewById(R.id.amount_trs);
            bought=itemView.findViewById(R.id.bought_sold_trs);
        }
    }
}