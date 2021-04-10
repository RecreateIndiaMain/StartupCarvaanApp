package recreate.india.main.startupcarvaan.aboutshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.aboutshare.modals.buy;
import recreate.india.main.startupcarvaan.aboutshare.modals.sell;
import recreate.india.main.startupcarvaan.aboutshare.models.blogdetails;
import recreate.india.main.startupcarvaan.fragments.allshares.allshare;
import recreate.india.main.startupcarvaan.fragments.allshares.allshares;

public class blogging extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private FirebaseFirestore ff=FirebaseFirestore.getInstance();
    private FirestoreRecyclerAdapter adapter;
    private FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
    private String shareid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogging);
        bottomNavigationView=findViewById(R.id.buy_sell_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemReselectedListener);
        recyclerView=findViewById(R.id.aboutshare);
        shareid=getIntent().getStringExtra("shareid");
        Query query=ff.collection("allshares").document(shareid).collection("blogs");
        FirestoreRecyclerOptions<blogdetails> option= new FirestoreRecyclerOptions.
                Builder<blogdetails>().setQuery(query,blogdetails.class).
                build();
        adapter= new FirestoreRecyclerAdapter<blogdetails, PostViewHolder>(option) {
            @NonNull
            @Override
            public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.z_single_blogfile,parent,false);
                return new PostViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull blogdetails model) {

            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(blogging.this));
    }
    public class PostViewHolder extends RecyclerView.ViewHolder {
        private Button invest;
        private ImageView switchLayout;
        private LinearLayout first,second;
        private boolean on=true;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemReselectedListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.buy:buy buy=new buy();
                    Bundle bundle1=new Bundle();
                    bundle1.putString("shareid",shareid);
                    buy.setArguments(bundle1);
                    buy.show(getSupportFragmentManager(),"Dailog buy");
                    break;
                case R.id.sell:
                    FirebaseFirestore.getInstance().collection("users")
                            .document(firebaseUser.getUid()).collection("myshares").document(shareid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.getResult().exists()){
                                sell sell=new sell();
                                Bundle bundle2=new Bundle();
                                bundle2.putString("shareid",shareid);
                                sell.setArguments(bundle2);
                                sell.show(getSupportFragmentManager(),"Dailog sell");
                            }
                            else Toast.makeText(blogging.this, "you do not have any holdings of this share", Toast.LENGTH_SHORT).show();
                        }
                    });

                    break;
            }
            return false;
        }
    };
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