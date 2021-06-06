package recreate.india.main.startupcarvaan.coin_exchange;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.fragments.models.RciValue;
import recreate.india.main.startupcarvaan.fragments.progressdialogue.CustomProgressDialogue;
import recreate.india.main.startupcarvaan.mainActivities.MainActivity;

public class rci_exchange extends AppCompatActivity {
    private LineChart lineChart;
    private TextView desc_rci, price_rci;
    Toolbar toolbar;
    private RciValue rciValue = new RciValue();
    FirebaseFirestore ff = FirebaseFirestore.getInstance();
    CustomProgressDialogue cpd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rci_exchange);
        cpd=new CustomProgressDialogue(this);
        cpd.show();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                cpd.dismiss();
            }
        };
        Handler handler=new Handler();

        lineChart=findViewById(R.id.lineChart_rci);
        lineChart.setTouchEnabled(true);
        lineChart.setPinchZoom(true);
        desc_rci=findViewById(R.id.desc_aboutRci);
        price_rci=findViewById(R.id.price_of_RCI);
        ArrayList<String> xAxes=new ArrayList<>();
        ArrayList<Entry> day=new ArrayList<>();
        ArrayList<Entry> values = new ArrayList<>();
        final float[] s1 = new float[1];
        final float[] s2 = new float[1];
        ff.collection("AboutRci").document("details").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    handler.postDelayed(runnable,2000);
                    Toast.makeText(rci_exchange.this, "Please tap on the graph is not visible", Toast.LENGTH_SHORT).show();
                    rciValue = value.toObject(RciValue.class);
                    price_rci.setText(String.valueOf(rciValue.getCurrentvalue()));
                    desc_rci.setText(String.valueOf(rciValue.getDescription()));
                    HashMap<String,Double> price_time =rciValue.getGraph();
                    for (Map.Entry<String, Double> set : price_time.entrySet()) {
                        s1[0] = Float.parseFloat(set.getKey());
                        s2[0] = Float.parseFloat(String.valueOf(set.getValue()));
                        values.add(new Entry(s1[0], s2[0]));
                    }
                    LineDataSet set1;
                    if (lineChart.getData() != null &&
                            lineChart.getData().getDataSetCount() > 0) {
                        set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
                        set1.setValues(values);
                        lineChart.getData().notifyDataChanged();
                        lineChart.notifyDataSetChanged();
                    } else {
                        set1 = new LineDataSet(values, "Price");
                        set1.setDrawIcons(false);
                        set1.enableDashedLine(10f, 5f, 0f);
                        set1.enableDashedHighlightLine(10f, 5f, 0f);
                        set1.setColor(Color.DKGRAY);
                        set1.setCircleColor(Color.DKGRAY);
                        set1.setLineWidth(1f);
                        set1.setCircleRadius(3f);
                        set1.setDrawCircleHole(false);
                        set1.setValueTextSize(9f);
                        set1.setDrawFilled(true);
                        set1.setFormLineWidth(1f);
                        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                        dataSets.add(set1);
                        LineData data = new LineData(dataSets);
                        lineChart.setData(data);
                        lineChart.invalidate();
                    }

                    if(lineChart.getData()==null && lineChart.getData().getDataSetCount()==0)
                        showGraph(values);
                }
            }
        });
    }
    void showGraph(ArrayList<Entry> values){
        LineDataSet set1;
        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            for (Entry i:values)
                Log.i("my_values", String.valueOf(i));
            set1 = new LineDataSet(values, "Price");
//            set1.setDrawIcons(false);
//            set1.enableDashedLine(10f, 5f, 0f);
//            set1.enableDashedHighlightLine(10f, 5f, 0f);
//            set1.setColor(Color.DKGRAY);
//            set1.setCircleColor(Color.DKGRAY);
//            set1.setLineWidth(1f);
//            set1.setCircleRadius(3f);
//            set1.setDrawCircleHole(false);
//            set1.setValueTextSize(9f);
//            set1.setDrawFilled(true);
//            set1.setFormLineWidth(1f);
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            LineData data = new LineData(dataSets);
            lineChart.setData(data);
            lineChart.invalidate();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(rci_exchange.this,MainActivity.class));
        finish();
    }

}