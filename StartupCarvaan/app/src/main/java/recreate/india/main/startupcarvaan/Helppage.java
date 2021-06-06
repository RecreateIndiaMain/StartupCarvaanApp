package recreate.india.main.startupcarvaan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import recreate.india.main.startupcarvaan.fragments.allshares.allshare;
import recreate.india.main.startupcarvaan.mainActivities.IntroSliderActivity;
import recreate.india.main.startupcarvaan.mainActivities.MainActivity;

public class Helppage extends AppCompatActivity {

    private LinearLayout l1,l2,l3,l4,l5;
    private LinearLayout l11,l12,l13,l14,l15;
    private LinearLayout text1,text2,text3,text4,text5;
    private ImageView im1,im2,im3,im4,im5;
    private LinearLayout before,after;
    private int count=0,count2=0,count3=0,count4=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helppage);

        before=findViewById(R.id.beforefaq);
        after=findViewById(R.id.afterfaq);
        l1=findViewById(R.id.linear1);
        l2=findViewById(R.id.linear2);
        l3=findViewById(R.id.linear3);
        l4=findViewById(R.id.linear4);
        l5=findViewById(R.id.linear5);
        l11=findViewById(R.id.linear11);
        l12=findViewById(R.id.linear12);
        l13=findViewById(R.id.linear13);
        l14=findViewById(R.id.linear14);
        l15=findViewById(R.id.linear15);
        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        text3=findViewById(R.id.text3);
        text4=findViewById(R.id.text4);
        text5=findViewById(R.id.text5);
        im1=findViewById(R.id.im1);
        im2=findViewById(R.id.im2);
        im3=findViewById(R.id.im3);
        im4=findViewById(R.id.im4);
        im5=findViewById(R.id.im5);
        l11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if(count%2==1)
                {
                    im1.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
                    text1.setVisibility(View.VISIBLE);

                    l1.setBackgroundColor(Color.parseColor("#E9EAEE"));
                    before.setVisibility(View.GONE);
                    after.setVisibility(View.VISIBLE);

                }
                else
                {
                    im1.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
                    text1.setVisibility(View.GONE);
                    l1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    before.setVisibility(View.VISIBLE);
                    after.setVisibility(View.GONE);
                }
            }
        });
        l12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count2++;
                if(count2%2==1)
                {
                    im2.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
                    text2.setVisibility(View.VISIBLE);
                    l2.setBackgroundColor(Color.parseColor("#E9EAEE"));
                    before.setVisibility(View.GONE);
                    after.setVisibility(View.VISIBLE);

                }
                else
                {
                    im2.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
                    text2.setVisibility(View.GONE);
                    l2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    before.setVisibility(View.VISIBLE);
                    after.setVisibility(View.GONE);
                }
            }
        });
        l13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count3++;
                if(count3%2==1)
                {
                    im3.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
                    text3.setVisibility(View.VISIBLE);
                    l3.setBackgroundColor(Color.parseColor("#E9EAEE"));
                    before.setVisibility(View.GONE);
                    after.setVisibility(View.VISIBLE);

                }
                else
                {
                    im3.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
                    text3.setVisibility(View.GONE);
                    l3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    before.setVisibility(View.VISIBLE);
                    after.setVisibility(View.GONE);
                }
            }
        });
        l14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if(count%2==1)
                {
                    im4.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
                    text4.setVisibility(View.VISIBLE);
                    l4.setBackgroundColor(Color.parseColor("#E9EAEE"));
                    before.setVisibility(View.GONE);
                    after.setVisibility(View.VISIBLE);

                }
                else
                {
                    im4.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
                    text4.setVisibility(View.GONE);
                    l4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    before.setVisibility(View.VISIBLE);
                    after.setVisibility(View.GONE);
                }
            }
        });







//        lineChart=findViewById(R.id.linechart);
//        FirebaseFirestore.getInstance().collection("allshares")
//                .document("shareid")
//                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                        allshare allshare=value.toObject(allshare.class);
//                        List<Entry> entries = new ArrayList<Entry>();
//                        List<Integer> dataObjects;
//                        dataObjects=allshare.getGraph();
//                        int count=0;
//                        for (int i=0;i<dataObjects.size();i++) {
//                            // turn your data into Entry objects
//                            entries.add(new Entry(count++, dataObjects.get(i)));
//                            lineChart.setData(new LineData(new LineDataSet(entries,"Price by day")));
//                            lineChart.setX(0);
//                            lineChart.setY(0);
//                            lineChart.invalidate();
//                        }
//                    }
//                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(Helppage.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}