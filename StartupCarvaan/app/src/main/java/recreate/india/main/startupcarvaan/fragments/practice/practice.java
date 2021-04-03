package recreate.india.main.startupcarvaan.fragments.practice;

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
import recreate.india.main.startupcarvaan.fragments.biding.biding;
import recreate.india.main.startupcarvaan.fragments.models.bidingm;
import recreate.india.main.startupcarvaan.fragments.models.practicemodel;

public class practice extends Fragment {
    private RecyclerView myshare;
    private FirestoreRecyclerAdapter adapter;
    public practice() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_practice, container, false);
        myshare=view.findViewById(R.id.practicerecyclerview);
        Query query= FirebaseFirestore.getInstance().collection("validators");
        FirestoreRecyclerOptions<practicemodel> option=new FirestoreRecyclerOptions.Builder<practicemodel>().setQuery(query,practicemodel.class).build();
        adapter= new FirestoreRecyclerAdapter<practicemodel, practice.viewholder>(option) {
            @NonNull
            @Override
            public practice.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.z_single_practice,parent,false);
                return new viewholder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull practice.viewholder holder, int position, @NonNull practicemodel model) {

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