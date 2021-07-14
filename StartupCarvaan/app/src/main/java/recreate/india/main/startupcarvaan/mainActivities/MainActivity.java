package recreate.india.main.startupcarvaan.mainActivities;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import io.paperdb.Paper;
import recreate.india.main.startupcarvaan.Helppage;
import recreate.india.main.startupcarvaan.Levels;
import recreate.india.main.startupcarvaan.R;

import recreate.india.main.startupcarvaan.allmodels.user.UserProfile;
import recreate.india.main.startupcarvaan.coin_exchange.AboutRCI;
import recreate.india.main.startupcarvaan.fragments.allshares;

import recreate.india.main.startupcarvaan.fragments.biding;
import recreate.india.main.startupcarvaan.fragments.mycoins;
import recreate.india.main.startupcarvaan.fragments.myshares;

import recreate.india.main.startupcarvaan.fragments.practice;
import recreate.india.main.startupcarvaan.loginsignup.loginActivity;
import recreate.india.main.startupcarvaan.user.CreateProfile;
import recreate.india.main.startupcarvaan.user.MoneyTransactions;
import recreate.india.main.startupcarvaan.user.ProfileActivity;
import recreate.india.main.startupcarvaan.user.Transactions;

public class MainActivity extends AppCompatActivity{
    private BottomNavigationView bnv;
    private NavigationView navigationView;
    private Fragment fragment = null;
    private FragmentTransaction fragmentTransaction;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private UserProfile userProfile=new UserProfile();
    private long pressedTime=0;
    private FirebaseFirestore ff=FirebaseFirestore.getInstance();

    // drawer bottom navigation menu
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
    //drawer navigation
    private NavigationView.OnNavigationItemSelectedListener mOnDNavigationItemSelectedListener
            = new NavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.logout:
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(MainActivity.this,loginActivity.class));
                    finish();
                    break;
                case R.id.howtoplay:
                    startActivity(new Intent(MainActivity.this, Helppage.class));
                    finish();
                    break;
                case R.id.profile:
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                    finish();
                    break;

                case R.id.growth:
                    startActivity(new Intent(MainActivity.this, Levels.class));
                    finish();
                    break;
                case R.id.shares_transactions:
                    startActivity(new Intent(MainActivity.this,Transactions.class));
                    break;

                case R.id.aboutRci:
                    startActivity(new Intent(MainActivity.this, AboutRCI.class));
                    finish();
                    break;
                case R.id.privacypolicy:
                    startActivity(new Intent(MainActivity.this,PrivacyPolicy.class));
                    finish();
                    break;
                case R.id.money_transactions:
                    startActivity(new Intent(MainActivity.this, MoneyTransactions.class));
                    break;
                case R.id.coin_drawer:
                case R.id.buyc:
                    fragment = new mycoins();
                    switchFragment(fragment);
                    break;

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

        navigationView=findViewById(R.id.n11);
        navigationView.setNavigationItemSelectedListener(mOnDNavigationItemSelectedListener);
        bnv=findViewById(R.id.main_bottom_nav);
        bnv.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        switchFragment(new allshares());
        bnv.setSelectedItemId(R.id.allshares);
        drawerLayout=findViewById(R.id.drawerlayout);
        toggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

//        String current=Paper.book().read("version");
//        FirebaseFirestore.getInstance().collection("version")
//                .document("version")
//                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable @org.jetbrains.annotations.Nullable DocumentSnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
//                        String latest=value.getString("current");
//                        if(!current.equals(latest)){
//                            startActivity(new Intent(Intent.ACTION_VIEW,
//                                    Uri.parse("https://play.google.com/store/apps/details?id=recreate.india.main.startupcarvaan")));
//                        }
//                    }
//                });
    }

    @Override
    public void onBackPressed() {
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(MainActivity.this, MainActivity.class));
//            finish();
        }
        pressedTime = System.currentTimeMillis();



    }
}