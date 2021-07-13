package recreate.india.main.startupcarvaan.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;
import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.allmodels.fragmentmodel.PractiseModel;
import recreate.india.main.startupcarvaan.allmodels.user.UserProfile;
import recreate.india.main.startupcarvaan.user.user;


public class practice extends Fragment {
    private RecyclerView myshare;
    private FirestoreRecyclerAdapter adapter;
    private CustomProgressDialogue cpd;
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore ff = FirebaseFirestore.getInstance();

    private UserProfile userProfile = new UserProfile();

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
        View view = inflater.inflate(R.layout.fragment_practice, container, false);
        cpd = new CustomProgressDialogue(getActivity());
        myshare = view.findViewById(R.id.practicerecyclerview);

        Query query = FirebaseFirestore.getInstance().collection("validators");
        FirestoreRecyclerOptions<PractiseModel> option = new FirestoreRecyclerOptions.Builder<PractiseModel>().setQuery(query, PractiseModel.class).build();
        adapter = new FirestoreRecyclerAdapter<PractiseModel, practice.viewholder>(option) {
            @NonNull
            @Override
            public practice.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_single_practice, parent, false);
                return new viewholder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull practice.viewholder holder, int position, @NonNull PractiseModel model) {
                String bettingId = getSnapshots().getSnapshot(position).getId();

                holder.name.setText(model.getName());
                holder.currentbet.setText(String.valueOf(model.getBetting().get(firebaseUser.getUid())));
                holder.rate.setText(String.valueOf(model.getRate()));
                holder.description.setText(model.getDescription());
                holder.current.setText(String.valueOf(model.getVote().size()));
                if (model.getVote().containsKey(firebaseUser.getUid())) {
                    holder.vote.setVisibility(View.GONE);
                } else {
                    holder.vote.setVisibility(View.VISIBLE);
                }
                holder.vote.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        model.getVote().put(firebaseUser.getUid(), true);
                        FirebaseFirestore.getInstance().collection("validators")
                                .document(bettingId)
                                .update("Vote", model.getVote());
                    }
                });

                holder.bet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.betting.getText().toString().equalsIgnoreCase("")) {
                            Toast.makeText(getContext(), "please enter some amount", Toast.LENGTH_LONG).show();
                        } else if (holder.betting.getText().toString().contains("."))
                            holder.betting.setError("Invalid bonus coins");
                        else {
                            Integer betp = Integer.valueOf(holder.betting.getText().toString());
                            ff.collection("users").document(firebaseUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                                    if (task.getResult().exists())
                                        userProfile = task.getResult().toObject(UserProfile.class);
                                    Double bonus = userProfile.getBonus();
                                    if (betp < bonus) {
                                        userProfile.setBonus(bonus - betp);
                                        ff.collection("users").document(firebaseUser.getUid()).update("bonus", bonus - betp);
                                        if (model.getBetting().containsKey(firebaseUser.getUid())) {
                                            Integer previousBet = model.getBetting().get(firebaseUser.getUid());
                                            model.getBetting().put(firebaseUser.getUid(), previousBet + betp);
                                        } else {
                                            model.getBetting().put(firebaseUser.getUid(), betp);
                                        }
                                        ff.collection("validators").document(bettingId).update("betting", model.getBetting());

                                    } else {
                                        Toast.makeText(getContext(), "you do not have enough coins", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            // Old code
                            /*
                            final coin[] coin = {new coin()};
                            FirebaseFirestore.getInstance().collection("users")
                                    .document(new user().user().getUid())
                                    .collection("others")
                                    .document("coins")
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {

                                    coin[0] = task.getResult().toObject(coin.class);
                                    if (betp <= coin[0].getBonus()) {
                                        new userfunctions().removeBonus(coin[0].getBonus(), betp);
                                        if (model.getHolders().containsKey(new user().user().getUid())) {
                                            model.getHolders().put(new user().user().getUid(), betp + model.getHolders().get(new user().user().getUid()));
                                        } else {
                                            model.getHolders().put(new user().user().getUid(), betp);

                                        }
                                        FirebaseFirestore.getInstance().collection("validators")
                                                .document(getSnapshots().getSnapshot(position).getId())
                                                .update("holders", model.getHolders());

                                    }
                                }
                            });

                             */

                        }
                    }
                });
                FirebaseStorage.getInstance().getReference().child(model.getLogo()).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if(task.getResult()!=null)
                        Glide.with(view.getContext()).load(task.getResult()).placeholder(R.drawable.userimage).into(holder.logo);
                        cpd.dismiss();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        holder.logo.setImageResource(R.drawable.logonew);
                    }
                });
                holder.video.addYouTubePlayerListener(new YouTubePlayerListener() {
                    @Override
                    public void onReady(@NotNull YouTubePlayer youTubePlayer) {
                        youTubePlayer.cueVideo(model.getUrl(), 0);
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
            }
        };
        myshare.setAdapter(adapter);
        myshare.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    private class viewholder extends RecyclerView.ViewHolder {
        private TextView name, description, current, currentbet, rate;
        private CircleImageView logo;
        private YouTubePlayerView video;
        private EditText betting;
        private Button bet, vote;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            rate = itemView.findViewById(R.id.rate);
            logo = itemView.findViewById(R.id.companylogo);
            currentbet = itemView.findViewById(R.id.currentbet);
            name = itemView.findViewById(R.id.companyname);
            description = itemView.findViewById(R.id.description);
            current = itemView.findViewById(R.id.currentvotes);
            bet = itemView.findViewById(R.id.bet);
            vote = itemView.findViewById(R.id.vote);
            video = itemView.findViewById(R.id.videoplayer);
            betting = itemView.findViewById(R.id.betting);
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