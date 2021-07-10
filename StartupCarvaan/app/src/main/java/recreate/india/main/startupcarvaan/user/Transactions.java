package recreate.india.main.startupcarvaan.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import recreate.india.main.startupcarvaan.R;

public class Transactions extends AppCompatActivity {


    private Fragment fragment=null;
    private FragmentTransaction fragmentTransaction;
    private BottomNavigationView bottomnavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelected=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.pending:
                    fragment=new pending_transaction();
                    switchFragment(fragment);
                     return true;
                case R.id.confirmed:
                    fragment=new confirmed_transactions();
                    switchFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        bottomnavigationView=findViewById(R.id.btv_transactions);
        bottomnavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelected);
        switchFragment(new pending_transaction());

    }


    private void switchFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.transactions_frame, fragment);
        fragmentTransaction.commit();
    }
}