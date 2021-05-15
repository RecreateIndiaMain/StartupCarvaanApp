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
        desc_rci=findViewById(R.id.desc_aboutRci);
        price_rci=findViewById(R.id.price_of_RCI);
//        ff.collection("AboutRci").document("details").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                rciValue=task.getResult().toObject(RciValue.class);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(rci_exchange.this, "Failed to get", Toast.LENGTH_SHORT).show();
//            }
//        });
        ArrayList<String> xAxes=new ArrayList<>();
        ArrayList<Entry> day=new ArrayList<>();
        ff.collection("AboutRci").document("details").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    rciValue = value.toObject(RciValue.class);
                    price_rci.setText(String.valueOf(rciValue.getCurrentvalue()));
                    desc_rci.setText(String.valueOf(rciValue.getDescription()));
                    HashMap<String,Double> price_time =rciValue.getGraph();
                    for (Map.Entry<String, Double> set : price_time.entrySet()) {
                        xAxes.add(String.valueOf(set.getKey()));
                        day.add(new Entry(Float.parseFloat(String.valueOf(set.getValue())), Float.parseFloat(set.getKey())));
                    }
                    String[] xaxes = new String[xAxes.size()];
                    for (int i = 0; i < xAxes.size(); i++) {
                        xaxes[i] = xAxes.get(i).toString();
                    }
                    ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();
                    LineDataSet lineDataSet = new LineDataSet(day, "Date");
                    lineDataSet.setDrawCircles(false);
                    lineDataSet.setColor(Color.BLUE);
                    lineDataSets.add(lineDataSet);
                    lineChart.setData(new LineData(lineDataSets));
                }
            }
        });
//        Toast.makeText(this, rciValue.getDescription(), Toast.LENGTH_SHORT).show();
//        else
//        {
//            Toast.makeText(this, "No Value", Toast.LENGTH_SHORT).show();
//        }
    }
}