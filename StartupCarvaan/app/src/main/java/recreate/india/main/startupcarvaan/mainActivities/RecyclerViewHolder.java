package recreate.india.main.startupcarvaan.mainActivities;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import recreate.india.main.startupcarvaan.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    View view;
    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        BottomNavigationView bottomNavigationView = itemView.findViewById(R.id.buy_sell_bottom_nav);
        view = itemView;
    }
}
