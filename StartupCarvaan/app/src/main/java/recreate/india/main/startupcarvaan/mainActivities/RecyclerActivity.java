package recreate.india.main.startupcarvaan.mainActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import recreate.india.main.startupcarvaan.R;

public class RecyclerActivity extends AppCompatActivity {
    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);


//        List<RecyclerData> list = new ArrayList<>();
//        list = getData();
//
//        // setting up the recycler view
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new MyRecyclerViewAdapter(RecyclerActivity.this);
//        //adapter.setClickListener(this);
//        recyclerView.setAdapter(adapter);
    }
}