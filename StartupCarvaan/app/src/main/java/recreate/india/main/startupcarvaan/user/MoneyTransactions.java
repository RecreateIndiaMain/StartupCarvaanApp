package recreate.india.main.startupcarvaan.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.user.transactionFragments.BuyFragment;
import recreate.india.main.startupcarvaan.user.transactionFragments.WithdrawFragment;

public class MoneyTransactions extends AppCompatActivity {

    private Fragment fragment=null;
    private FragmentTransaction fragmentTransaction;
    private BottomNavigationView bottomnavigationView;


    BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.bought_menu:
                    fragment=new BuyFragment();
                    switchFragment(fragment);
                    return true;
                case R.id.withdraw_menu:
                    fragment=new WithdrawFragment();
                    switchFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_transactions);
        bottomnavigationView=findViewById(R.id.money_btv_transactions);
        bottomnavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        switchFragment(new BuyFragment());

    }
    private void switchFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.money_transactions_frame, fragment);
        fragmentTransaction.commit();
    }
}