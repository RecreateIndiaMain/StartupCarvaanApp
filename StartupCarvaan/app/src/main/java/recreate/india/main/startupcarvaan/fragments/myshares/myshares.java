package recreate.india.main.startupcarvaan.fragments.myshares;

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
import recreate.india.main.startupcarvaan.fragments.models.holdings;

public class myshares extends Fragment {
    private RecyclerView myshare;
    private FirestoreRecyclerAdapter adapter;

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
        Query query= FirebaseFirestore.getInstance().collection("users").document("tupjdAJB8JcfMdzqc4P5iRIg0XE2").collection("myshares");
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