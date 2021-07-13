package recreate.india.main.startupcarvaan.coin_exchange;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.allmodels.RciValue;
import recreate.india.main.startupcarvaan.mainActivities.MainActivity;

public class AboutRCI extends AppCompatActivity {
    private LineChart lineChart;
    private TextView desc_rci, price_rci, btn_buy, btn_sell;
    Toolbar toolbar;
    FirebaseFirestore ff = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rci_exchange);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);

        lineChart = findViewById(R.id.lineChart_rci);
        final RciValue[] rciValue = {new RciValue()};
        lineChart.setTouchEnabled(true);
        lineChart.setPinchZoom(true);
        desc_rci = findViewById(R.id.desc_aboutRci);
        price_rci = findViewById(R.id.price_of_RCI);
        btn_buy = findViewById(R.id.buy_aboutRci);
        btn_sell = findViewById(R.id.sell_aboutRci);
        ArrayList<String> xAxes = new ArrayList<>();
        ArrayList<Entry> day = new ArrayList<>();
        ArrayList<Entry> values = new ArrayList<>();
        final float[] s1 = new float[1];
        final float[] s2 = new float[1];


        ff.collection("AboutRci").document("details").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {

                    rciValue[0] = value.toObject(RciValue.class);
                    price_rci.setText(String.valueOf(rciValue[0].getCurrentvalue()));
                    desc_rci.setText(String.valueOf(rciValue[0].getDescription()));
                    ArrayList<Double> priceList = rciValue[0].getGraph();
                    int i = 0;
                    for (Double price : priceList) {
                        i++;
                        values.add(new Entry(Float.parseFloat(String.valueOf(i)), Float.parseFloat(String.valueOf(price))));
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

                    if (lineChart.getData() == null && lineChart.getData().getDataSetCount() == 0)
                        showGraph(values);
                }
            }
        });
    // TODO: to complete this clicks
        btn_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    void showGraph(ArrayList<Entry> values) {
        LineDataSet set1;
        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            for (Entry i : values)
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
        startActivity(new Intent(AboutRCI.this, MainActivity.class));
        finish();
    }
}