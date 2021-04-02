package recreate.india.main.startupcarvaan.mainActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import io.paperdb.Paper;
import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.fragments.allshares.allshares;
import recreate.india.main.startupcarvaan.fragments.biding.biding;
import recreate.india.main.startupcarvaan.fragments.mycoins.mycoins;
import recreate.india.main.startupcarvaan.fragments.myshares.myshares;
import recreate.india.main.startupcarvaan.fragments.practice.practice;
import recreate.india.main.startupcarvaan.loginsignup.loginActivity;

public class MainActivity extends AppCompatActivity{
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


    private void switchFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Paper.init(MainActivity.this);
        drawerLayout=findViewById(R.id.drawerlayout);
        ActionBar toolbar=this.getSupportActionBar();
        toggle= new ActionBarDrawerToggle(MainActivity.this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView=findViewById(R.id.n11);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.logout:
                        Toast.makeText(MainActivity.this, "logout working", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

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
        switch(item.getItemId()){
            case R.id.howtoplay:
                Toast.makeText(MainActivity.this, "howtoplay working", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Paper.book().write("first","true");
    }

}