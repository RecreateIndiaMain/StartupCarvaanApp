package recreate.india.main.startupcarvaan.fragments.biding;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.fragments.allshares.allshares;
import recreate.india.main.startupcarvaan.fragments.models.bidingm;
import recreate.india.main.startupcarvaan.fragments.models.holdings;
import recreate.india.main.startupcarvaan.fragments.myshares.myshares;
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

            }
        };
        myshare.setAdapter(adapter);
        myshare.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    private class viewholder extends RecyclerView.ViewHolder {
        public viewholder(@NonNull View itemView) {
            super(itemView);
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