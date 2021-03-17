package recreate.india.main.startupcarvaan.mainActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.fragments.allshares.allshares;
import recreate.india.main.startupcarvaan.fragments.biding.biding;
import recreate.india.main.startupcarvaan.fragments.mycoins.mycoins;
import recreate.india.main.startupcarvaan.fragments.myshares.myshares;
import recreate.india.main.startupcarvaan.fragments.practice.practice;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bnv;
    private NavigationView navigationView;
    private Fragment fragment = null;
    private FragmentTransaction fragmentTransaction;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.allshares:
                    fragment = new allshares();
                    switchFragment(fragment);
                    return true;
                case R.id.myshares:
                    fragment = new myshares();
                    switchFragment(fragment);
                    return true;
                case R.id.practice:
                    fragment = new practice();
                    switchFragment(fragment);
                    return true;
                case R.id.mycoins:
                    fragment = new mycoins();
                    switchFragment(fragment);
                    return true;
                case R.id.biding:
                    fragment = new biding();
                    switchFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    private NavigationView.OnNavigationItemSelectedListener drawer=new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch(item.getItemId()){

            }
            return true;
        }
    };


    private void switchFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=findViewById(R.id.drawerlayout);
        ActionBar toolbar=this.getSupportActionBar();
        toggle= new ActionBarDrawerToggle(MainActivity.this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView=findViewById(R.id.n1);
        navigationView.setNavigationItemSelectedListener(drawer);
        bnv=findViewById(R.id.main_bottom_nav);
        bnv.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        switchFragment(new allshares());
        bnv.setSelectedItemId(R.id.allshares);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}