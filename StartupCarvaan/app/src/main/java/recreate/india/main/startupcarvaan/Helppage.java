package recreate.india.main.startupcarvaan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
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

public class Helppage extends AppCompatActivity {
    private LineChart lineChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helppage);
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
}