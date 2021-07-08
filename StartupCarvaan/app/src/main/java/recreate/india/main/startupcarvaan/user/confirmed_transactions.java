package recreate.india.main.startupcarvaan.user;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.allmodels.share.sharedetails.TransactionDetails;
import recreate.india.main.startupcarvaan.allmodels.user.UserShareTransaction;

public class confirmed_transactions extends Fragment {

    private RecyclerView recyclerView;
    private FirebaseUser user;
    private FirebaseFirestore ff = FirebaseFirestore.getInstance();
    private FirestoreRecyclerAdapter adapter;

    public confirmed_transactions() {
// Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = FirebaseAuth.getInstance().getCurrentUser();
    }
// model is changed

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comfirmed_transactions, container, false);

        recyclerView = view.findViewById(R.id.confirmed_transactions_recyclerView);

        Query query= ff.collection("users").document(user.getUid()).collection("completedtransactions");
        FirestoreRecyclerOptions<UserShareTransaction> options=new FirestoreRecyclerOptions.Builder<UserShareTransaction>().setQuery(query, UserShareTransaction.class).build();
        adapter=new FirestoreRecyclerAdapter<UserShareTransaction,PostViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull PostViewHolder holder, int position, @NonNull @NotNull UserShareTransaction model) {
                holder.startupname.setText(model.getShareid()); // share id is visible need to figure it out
                holder.quantity.setText(model.getQuantity().toString());
                holder.price.setText(model.getPrice().toString());
                holder.amount.setText(String.valueOf(model.getPrice()*model.getQuantity()));
                holder.deletebtn.setVisibility(View.GONE);
                holder.bought.setText(model.getType());

            }

            @NonNull
            @Override
            public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view1=LayoutInflater.from(parent.getContext()).inflate(R.layout.single_transaction,parent,false);
                return new PostViewHolder(view1);
            }
        };

        return view;
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        private TextView startupname,quantity,price,amount,bought;
        private Button deletebtn;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            startupname=itemView.findViewById(R.id.startup_name_trs);
            quantity=itemView.findViewById(R.id.quantity_trs);
            price=itemView.findViewById(R.id.price_trs);
            amount=itemView.findViewById(R.id.amount_trs);
            bought=itemView.findViewById(R.id.bought_sold_trs);
            deletebtn=itemView.findViewById(R.id.delete_btn_trs);
        }
    }

}