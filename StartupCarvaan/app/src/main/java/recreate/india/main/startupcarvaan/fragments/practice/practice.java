package recreate.india.main.startupcarvaan.fragments.practice;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;
import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.fragments.mycoins.coin;
import recreate.india.main.startupcarvaan.fragments.progressdialogue.CustomProgressDialogue;
import recreate.india.main.startupcarvaan.user.user;
import recreate.india.main.startupcarvaan.user.userfunctions;

public class practice extends Fragment {
    private RecyclerView myshare;
    private FirestoreRecyclerAdapter adapter;
    private CustomProgressDialogue cpd;


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
        cpd=new CustomProgressDialogue(getActivity());
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
                holder.name.setText(model.getName());
                holder.currentbet.setText(String.valueOf(model.getHolders().get(new user().user().getUid())));
                holder.rate.setText(String.valueOf(model.getRate()));
                holder.description.setText(model.getDescription());
                holder.current.setText(String.valueOf(model.getCurrentvotes().size()));
                if(model.getCurrentvotes().containsKey(new user().user().getUid())){
                    holder.vote.setVisibility(View.GONE);
                }
                else{
                    holder.vote.setVisibility(View.VISIBLE);
                }
                holder.vote.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        model.getCurrentvotes().put(new user().user().getUid(),true);
                        FirebaseFirestore.getInstance().collection("validators")
                                .document(getSnapshots().getSnapshot(position).getId())
                                .update("currentvotes",model.getCurrentvotes());
                    }
                });

                holder.bet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(holder.betting.getText().toString().equalsIgnoreCase("")){
                            Toast.makeText(getContext(),"please enter some amount",Toast.LENGTH_LONG).show();
                        }
                        else if(holder.betting.getText().toString().contains("."))
                            holder.betting.setError("Invalid bonus coins");
                        else{
                            final coin[] coin = {new coin()};
                            FirebaseFirestore.getInstance().collection("users")
                                    .document(new user().user().getUid())
                                    .collection("others")
                                    .document("coins")
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {

                                    coin[0] =task.getResult().toObject(coin.class);
                                    Integer betp=Integer.valueOf(holder.betting.getText().toString());
                                    if(betp<=coin[0].getBonus()){
                                        new userfunctions().removeBonus(coin[0].getBonus(),betp);
                                        if(model.getHolders().containsKey(new user().user().getUid())){
                                            model.getHolders().put(new user().user().getUid(),betp+model.getHolders().get(new user().user().getUid()));
                                        }
                                        else{
                                            model.getHolders().put(new user().user().getUid(),betp);

                                        }
                                        FirebaseFirestore.getInstance().collection("validators")
                                                .document(getSnapshots().getSnapshot(position).getId())
                                                .update("holders",model.getHolders());

                                    }
                                    else{
                                        Toast.makeText(getContext(), "you do not have enough coins", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });
                FirebaseStorage.getInstance().getReference().child(model.getLogourl()).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        Glide.with(view.getContext()).load(task.getResult()).placeholder(R.drawable.userimage).into(holder.logo);
                        cpd.dismiss();

                    }
                });
                holder.video.addYouTubePlayerListener(new YouTubePlayerListener() {
                    @Override
                    public void onReady(@NotNull YouTubePlayer youTubePlayer) {
                        youTubePlayer.cueVideo(model.getIntrovideourl(),0);
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
        private TextView name,description,current,currentbet,rate;
        private CircleImageView logo;
        private YouTubePlayerView video;
        private EditText betting;
        private Button bet, vote;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            rate=itemView.findViewById(R.id.rate);
            logo=itemView.findViewById(R.id.companylogo);
            currentbet=itemView.findViewById(R.id.currentbet);
            name=itemView.findViewById(R.id.companyname);
            description=itemView.findViewById(R.id.description);
            current=itemView.findViewById(R.id.currentvotes);
            bet=itemView.findViewById(R.id.bet);
            vote=itemView.findViewById(R.id.vote);
            video=itemView.findViewById(R.id.videoplayer);
            betting=itemView.findViewById(R.id.betting);
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