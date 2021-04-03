package recreate.india.main.startupcarvaan.aboutshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.aboutshare.modals.buy;
import recreate.india.main.startupcarvaan.aboutshare.modals.sell;

public class blogging extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    String shareid;
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemReselectedListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.buy:buy buy=new buy();
                    Bundle bundle1=new Bundle();
                    bundle1.putString("shareid",shareid);
                    buy.setArguments(bundle1);
                    buy.show(getSupportFragmentManager(),"Dailog buy");
                    break;
                case R.id.sell:
                    sell sell=new sell();
                    Bundle bundle2=new Bundle();
                    bundle2.putString("shareid",shareid);
                    sell.setArguments(bundle2);
                    sell.show(getSupportFragmentManager(),"Dailog sell");

                    break;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogging);
        bottomNavigationView=findViewById(R.id.buy_sell_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemReselectedListener);
    }
}