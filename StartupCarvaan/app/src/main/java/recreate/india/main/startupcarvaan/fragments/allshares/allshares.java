package recreate.india.main.startupcarvaan.fragments.allshares;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.aboutshare.blogging;
import recreate.india.main.startupcarvaan.fragments.models.allshare;

public class allshares extends Fragment {
    private FirebaseFirestore ff= FirebaseFirestore.getInstance();
    private FirestoreRecyclerAdapter adapter;
    private RecyclerView recyclerView;
    public allshares() {
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
        View view= inflater.inflate(R.layout.fragment_allshares, container, false);
        recyclerView=view.findViewById(R.id.allsharerecyclerview);
        Query query=ff.collection("allshares");
        FirestoreRecyclerOptions<allshare> option= new FirestoreRecyclerOptions.
                Builder<allshare>().setQuery(query,allshare.class).
                build();
        adapter= new FirestoreRecyclerAdapter<allshare, PostViewHolder>(option) {
            @NonNull
            @Override
            public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.z_single_allshare,parent,false);
                return new PostViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull allshare model) {
                holder.invest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String shareid=getSnapshots().getSnapshot(position).getId();
                        startActivity(new Intent(getContext(), blogging.class).putExtra("shareid",shareid));
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
    public class PostViewHolder extends RecyclerView.ViewHolder {
        private Button invest;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            invest=itemView.findViewById(R.id.invest);

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