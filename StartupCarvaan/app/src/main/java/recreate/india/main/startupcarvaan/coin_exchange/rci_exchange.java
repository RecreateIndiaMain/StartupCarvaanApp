package recreate.india.main.startupcarvaan.coin_exchange;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
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

public class rci_exchange extends AppCompatActivity {
    private LineChart lineChart;
    private TextView desc_rci,price_rci;
    private RciValue rciValue=new RciValue();
    FirebaseFirestore ff=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rci_exchange);
        lineChart=findViewById(R.id.lineChart_rci);
        lineChart.setTouchEnabled(true);
        lineChart.setPinchZoom(true);
        desc_rci=findViewById(R.id.desc_aboutRci);
        price_rci=findViewById(R.id.price_of_RCI);
        ArrayList<String> xAxes=new ArrayList<>();
        ArrayList<Entry> day=new ArrayList<>();
        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(1, 5));
        values.add(new Entry(2, 10));
        values.add(new Entry(3, 10));
        values.add(new Entry(3, 20));
        values.add(new Entry(4, 50));
        showGraph(values);
        ff.collection("AboutRci").document("details").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    rciValue = value.toObject(RciValue.class);
                    price_rci.setText(String.valueOf(rciValue.getCurrentvalue()));
                    desc_rci.setText(String.valueOf(rciValue.getDescription()));
//                    HashMap<String,Double> price_time =rciValue.getGraph();
//                    for (Map.Entry<String, Double> set : price_time.entrySet()) {
//                        xAxes.add(String.valueOf(set.getKey()));
//                        day.add(new Entry((float) Double.parseDouble(String.valueOf(set.getValue())), (float) Double.parseDouble(set.getKey())));
//                    }
//                    String[] xaxes = new String[xAxes.size()];
//                    for (int i = 0; i < xAxes.size(); i++) {
//                        xaxes[i] = xAxes.get(i).toString();
//                    }
//                    ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();
//                    LineDataSet lineDataSet = new LineDataSet(day, "Date");
//                    lineDataSet.setDrawCircles(false);
//                    lineDataSet.setColor(Color.BLUE);
//                    lineDataSets.add(lineDataSet);
//                    lineChart.setData(new LineData(lineDataSets));
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
            set1 = new LineDataSet(values, "Sample Data");
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
        }
    }
}