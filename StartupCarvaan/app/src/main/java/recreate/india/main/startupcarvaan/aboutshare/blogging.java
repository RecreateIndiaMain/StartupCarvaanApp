package recreate.india.main.startupcarvaan.aboutshare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
import com.google.firebase.storage.FirebaseStorage;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.aboutshare.modals.buy;
import recreate.india.main.startupcarvaan.aboutshare.modals.investment;
import recreate.india.main.startupcarvaan.aboutshare.modals.sell;
import recreate.india.main.startupcarvaan.allmodels.share.BloggingDetails;
import recreate.india.main.startupcarvaan.allmodels.user.UserFunctions;
import recreate.india.main.startupcarvaan.mainActivities.MainActivity;

public class blogging extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private FirebaseFirestore ff = FirebaseFirestore.getInstance();
    private FirestoreRecyclerAdapter adapter;
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private String shareid;
    String ans;
    private Button invest;
    private UserFunctions userFunctions = new UserFunctions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogging);
        ProgressDialog pd = new ProgressDialog(blogging.this);
        pd.setMessage("This is BloggingDetails");
        pd.show();
        bottomNavigationView = findViewById(R.id.buy_sell_bottom_nav);
        invest = findViewById(R.id.bla);
        shareid = getIntent().getStringExtra("shareid");
        ans = getIntent().getStringExtra("type");
        invest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(blogging.this, "invest", Toast.LENGTH_SHORT).show();
                investment investment = new investment();
                Bundle bundle1 = new Bundle();
                bundle1.putString("shareid", shareid);
                investment.setArguments(bundle1);
                investment.show(getSupportFragmentManager(), "Invest");
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemReselectedListener);
        recyclerView = findViewById(R.id.aboutshare);
        if (ans.equalsIgnoreCase("Investment")) {
            invest.setVisibility(View.VISIBLE);
            bottomNavigationView.setVisibility(View.GONE);
        } else {
            invest.setVisibility(View.GONE);
            bottomNavigationView.setVisibility(View.VISIBLE);
        }

        Query query = ff.collection("startup").document(shareid).collection("bloggings");

        FirestoreRecyclerOptions<BloggingDetails> option = new FirestoreRecyclerOptions.
                Builder<BloggingDetails>().setQuery(query, BloggingDetails.class).
                build();
        adapter = new FirestoreRecyclerAdapter<BloggingDetails, PostViewHolder>(option) {
            @NonNull
            @Override
            public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_single_blogfile, parent, false);
                return new PostViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull BloggingDetails model) {

                holder.title.setText(model.getTitle());
                holder.description.setText(model.getDescription());
                holder.num_likes.setText(String.valueOf(model.getLikes().size()));
                holder.num_comments.setText(String.valueOf(model.getComments().size()));
                holder.commentbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.addcomment.getText().toString().isEmpty())
                            Toast.makeText(blogging.this, "please enter some text", Toast.LENGTH_SHORT).show();
                        else {
                            model.getComments().put(firebaseUser.getUid() + new Random().toString(), holder.addcomment.getText().toString());
                            ff.collection("startup")
                                    .document(shareid)
                                    .collection("bloggings")
                                    .document(getSnapshots().getSnapshot(position).getId())
                                    .update("comments", model.getComments());
                        }
                    }
                });
                holder.likeimage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (model.getLikes().containsKey(firebaseUser.getUid()))
                            Toast.makeText(blogging.this, "already liked", Toast.LENGTH_SHORT).show();
                        else {
                            // updating likes and giving rewards
                            model.getLikes().put(firebaseUser.getUid(), true);
                            ff.collection("startup")
                                    .document(shareid)
                                    .collection("bloggings")
                                    .document(getSnapshots().getSnapshot(position).getId())
                                    .update("likes", model.getLikes());


                            Double bonus = userFunctions.userProfile.getBonus() + 5;

                            userFunctions.userProfile.setBonus(bonus);
                            ff.collection("users")
                                    .document(firebaseUser.getUid()).update("bonus",bonus);
                        }
                    }
                });

                holder.commentimage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        comment comments = new comment();
                        Bundle bd = new Bundle();
                        bd.putString("shareid", shareid);
                        bd.putString("blogid", getSnapshots().getSnapshot(position).getId());
                        comments.setArguments(bd);
                        comments.show(getSupportFragmentManager(), "Comment Dialogue");

                    }
                });


                if (model.getType().equals("video")) {
                    final float[] c_sec = {0};
                    final boolean[] me = {true};
                    holder.imageView.setVisibility(View.GONE);
                    holder.videoplayer.setVisibility(View.VISIBLE);
                    holder.videoplayer.addYouTubePlayerListener(new YouTubePlayerListener() {
                        @Override
                        public void onReady(@NotNull YouTubePlayer youTubePlayer) {
                            String id = model.getBlogurl();
                            youTubePlayer.cueVideo(id, 0);
                            pd.dismiss();
                        }

                        @Override
                        public void onStateChange(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlayerState playerState) {

                        }

                        @Override
                        public void onPlaybackQualityChange(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlaybackQuality playbackQuality) {

                        }

                        @Override
                        public void onPlaybackRateChange(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlaybackRate playbackRate) {

                        }

                        @Override
                        public void onError(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlayerError playerError) {

                        }

                        @Override
                        public void onCurrentSecond(@NotNull YouTubePlayer youTubePlayer, float v) {
                            c_sec[0] = v;
                        }

                        @Override
                        public void onVideoDuration(@NotNull YouTubePlayer youTubePlayer, float v) {

                        }

                        @Override
                        public void onVideoLoadedFraction(@NotNull YouTubePlayer youTubePlayer, float v) {

                        }

                        @Override
                        public void onVideoId(@NotNull YouTubePlayer youTubePlayer, @NotNull String s) {

                        }

                        @Override
                        public void onApiChange(@NotNull YouTubePlayer youTubePlayer) {

                        }
                    });
                } else {
                    holder.imageView.setVisibility(View.VISIBLE);
                    holder.videoplayer.setVisibility(View.GONE);
                    FirebaseStorage.getInstance().getReference().child(model.getBlogurl())
                            .getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            Glide.with(blogging.this).load(task.getResult()).into(holder.imageView);
                            pd.dismiss();
                        }
                    });

                }
            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(blogging.this));

        invest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:  to be made
            }
        });

    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        private YouTubePlayerView videoplayer;
        private ImageView imageView;
        private TextView title, description, num_likes, num_comments;
        private EditText addcomment;
        private ImageView commentbutton, likeimage, commentimage;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            videoplayer = itemView.findViewById(R.id.videoplayer);
            imageView = itemView.findViewById(R.id.imageview);
            title = itemView.findViewById(R.id.blogtitle);
            description = itemView.findViewById(R.id.description);
            num_likes = itemView.findViewById(R.id.number_of_likes);
            num_comments = itemView.findViewById(R.id.number_of_comments);
            addcomment = itemView.findViewById(R.id.addcomment);
            commentbutton = itemView.findViewById(R.id.commentButton);
            likeimage = itemView.findViewById(R.id.likeimage);
            commentimage = itemView.findViewById(R.id.commentimage);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemReselectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.buy:
                    buy buy = new buy();
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("shareid", shareid);
                    buy.setArguments(bundle1);
                    buy.show(getSupportFragmentManager(), "Dailog buy");
                    break;
                case R.id.sell:
                    FirebaseFirestore.getInstance().collection("users")
                            .document(firebaseUser.getUid()).collection("myshares").document(shareid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.getResult().exists()) {
                                sell sell = new sell();
                                Bundle bundle2 = new Bundle();
                                bundle2.putString("shareid", shareid);
                                sell.setArguments(bundle2);
                                sell.show(getSupportFragmentManager(), "Dailog sell");
                            } else
                                Toast.makeText(blogging.this, "you do not have any holdings of this share", Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(blogging.this, MainActivity.class));
    }
}