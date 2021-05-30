
package recreate.india.main.startupcarvaan.fragments.allshares;

import android.app.ProgressDialog;
import android.content.Entity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.aboutshare.blogging;
import recreate.india.main.startupcarvaan.aboutshare.models.sharedetails;
import recreate.india.main.startupcarvaan.fragments.progressdialogue.CustomProgressDialogue;
import recreate.india.main.startupcarvaan.user.ProfileActivity;

public class allshares extends Fragment {
    private FirebaseFirestore ff= FirebaseFirestore.getInstance();
    private FirestoreRecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private CustomProgressDialogue pDialog;
    private boolean loaded=false;
    private LineChart lineChart;
    ArrayList<Integer> graph=new ArrayList<>();
    ArrayList<Entry> data=new ArrayList<>();

    public allshares() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_allshares, container, false);
        recyclerView=view.findViewById(R.id.allsharerecyclerview);
        if(loaded==false)
            dismissDialog();
        final int[] count = {1};
        Query query=ff.collection("allshares").orderBy("users");
        FirestoreRecyclerOptions<allshare> option= new FirestoreRecyclerOptions.
                Builder<allshare>().setQuery(query,allshare.class).
                build();
        adapter= new FirestoreRecyclerAdapter<allshare, PostViewHolder>(option) {
            @NonNull
            @Override
            public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                showProgress();
                loaded=true;
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.z_single_allshare,parent,false);
                return new PostViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull allshare model) {
                holder.invest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String shareid = getSnapshots().getSnapshot(position).getId();
                        startActivity(new Intent(getContext(), blogging.class).putExtra("shareid", shareid));
                        getActivity().finish();

                    }
                });
                graph= (ArrayList<Integer>) model.getGraph();
                lineChart.setTouchEnabled(true);
                lineChart.setPinchZoom(true);
                for (int i=0;i<graph.size();i++){
                    data.add(new Entry(i+1,graph.get(i)));
                }
                LineDataSet set1;
                if (lineChart.getData() != null &&
                        lineChart.getData().getDataSetCount() > 0) {
                    set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
                    set1.setValues(data);
                    lineChart.getData().notifyDataChanged();
                    lineChart.notifyDataSetChanged();
                } else {
                    set1 = new LineDataSet(data, "Price");
                    set1.setDrawIcons(false);
                    set1.enableDashedLine(10f, 5f, 0f);
                    set1.enableDashedHighlightLine(10f, 5f, 0f);
                    set1.setColor(Color.BLUE);
                    set1.setCircleColor(Color.BLUE);
                    set1.setLineWidth(1f);
                    set1.setCircleRadius(1f);
                    set1.setDrawCircleHole(false);
                    set1.setValueTextSize(7f);
                    set1.setDrawFilled(true);
                    set1.setFormLineWidth(1f);
                    ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                    dataSets.add(set1);
                    LineData data2 = new LineData(dataSets);
                    lineChart.setData(data2);

                }
                holder.switchLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.on) {
                            holder.on = false;
                            holder.first.setVisibility(View.GONE);
                            holder.video.setVisibility(View.GONE);
                            holder.colorlayout.setVisibility(View.GONE);
                            holder.colourlayout2.setVisibility(View.VISIBLE);
                            holder.second.setVisibility(View.VISIBLE);
                        } else {
                            holder.on = true;
                            holder.first.setVisibility(View.VISIBLE);
                            holder.video.setVisibility(View.VISIBLE);
                            holder.colorlayout.setVisibility(View.VISIBLE);
                            holder.colourlayout2.setVisibility(View.GONE);
                            holder.second.setVisibility(View.GONE);
                        }
                    }
                });

                // loading all values to holder
                FirebaseStorage.getInstance().getReference().child(model.getLogourl()).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.getResult() != null) {
                            Glide.with(getContext())
                                    .load(task.getResult())
                                    .into(holder.companylogo);
                            dismissDialog();

                        }


                    }
                });
                holder.companyname.setText(model.getName());
                holder.growthtext.setText(String.valueOf(model.getGrowth()));
                holder.growthbar.setProgress(Integer.valueOf(model.getGrowth()));
                holder.investors.setText(String.valueOf(model.getUsers()));
                holder.group.setText(model.getType());
                String tag = "";
                for (int i = 0; i < model.getTags().size(); i++) {
                    tag += model.getTags().get(i) + "  ";
                }
                holder.tags.setText(tag);
                holder.advice.setText("Advice \n " + model.getAdvice());
                holder.nextslot.setText("Next slot   " + String.valueOf(model.getNextslot().toDate()));
                holder.introvideo.addYouTubePlayerListener(new YouTubePlayerListener() {
                    @Override
                    public void onReady(@NotNull YouTubePlayer youTubePlayer) {
                        String url = model.getIntrovideourl();
                        youTubePlayer.cueVideo(url, 0);


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
                String shareid = getSnapshots().getSnapshot(position).getId();
                FirebaseFirestore.getInstance()
                        .collection("allshares")
                        .document(shareid)
                        .collection("sharedetails")
                        .document("sharedetails")
                        .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                                sharedetails sharedetails = value.toObject(recreate.india.main.startupcarvaan.aboutshare.models.sharedetails.class);
                                holder.sellingprice.setText(String.valueOf(sharedetails.getSellingprice()));
                                holder.buyingprice.setText(String.valueOf(sharedetails.getBuyingprice()));
                            }
                        });
//                Toast.makeText(getContext(), sharedetails[0].getBuyingprice(), Toast.LENGTH_SHORT).show();
                if (model.getType().equals("elite")) {
                    holder.colorlayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bluetr));
                    holder.invest.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bluetr));
                    holder.colourlayout2.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bluetr));

                } else if (model.getType().equals("mediocre")) {
                    holder.colorlayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.purp20));
                    holder.invest.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.purp20));
                    holder.colourlayout2.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.purp20));

                } else {
                    holder.colorlayout.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bluepupr));
                    holder.invest.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bluepupr));
                    holder.colourlayout2.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bluepupr));
                }
            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
    public class PostViewHolder extends RecyclerView.ViewHolder {
        private Button invest;
        private ImageView switchLayout;
        private LinearLayout first,second,video,colorlayout,colourlayout2;
        // fields
        private ImageView companylogo;
        private TextView companyname,group,investors;
        private YouTubePlayerView introvideo;
        private TextView growthtext,tags;
        private ProgressBar growthbar;
        private TextView advice,nextslot,buyingprice,sellingprice;

        private boolean on=true;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            invest=itemView.findViewById(R.id.invest);
            switchLayout=itemView.findViewById(R.id.secondLayout);
            first=itemView.findViewById(R.id.growth);
            second=itemView.findViewById(R.id.afterpress);
            video=itemView.findViewById(R.id.video);
            colorlayout=itemView.findViewById(R.id.colorlayout);
            colourlayout2=itemView.findViewById(R.id.colorlayout2);
            companylogo=itemView.findViewById(R.id.companylogo);
            companyname=itemView.findViewById(R.id.companyname);
            investors=itemView.findViewById(R.id.investors);
            introvideo=itemView.findViewById(R.id.introvideo);
            growthtext=itemView.findViewById(R.id.growthtext);
            growthbar=itemView.findViewById(R.id.growthbar);
            tags=itemView.findViewById(R.id.tags);
            group=itemView.findViewById(R.id.group);
            advice=itemView.findViewById(R.id.advice);
            nextslot=itemView.findViewById(R.id.nextslot);
            buyingprice=itemView.findViewById(R.id.buyingprice);
            sellingprice=itemView.findViewById(R.id.sellingprice);
            lineChart=itemView.findViewById(R.id.lineChart_price);
        }
    }
    public void showProgress() {
        pDialog = null;
        if (pDialog == null) {
            pDialog = new CustomProgressDialogue(getActivity());
            pDialog.setCancelable(true);
            pDialog.show();
        }
    }
    void checkDailog(int i){
        if(i==0) {
            dismissDialog();
        }
    }
    public void dismissDialog() {
        if (pDialog != null && pDialog.isShowing()){
            pDialog.dismiss();
            pDialog = null;
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
        dismissDialog();
    }

}