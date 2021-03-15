package recreate.india.main.startupcarvaan.mainActivities;

// WRITTEN BY VISHNU

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import recreate.india.main.startupcarvaan.R;


public class IntroSliderActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout mDotLayout;
    private SliderAdaptar sliderAdaptar;
    private final int LENGTH_OF_ITEMS = new SliderAdaptar(IntroSliderActivity.this).getCount();
    private Button nextBtn;
    private Button skipBtn;
    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro_slider);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        nextBtn = findViewById(R.id.nextBtn);
        skipBtn = findViewById(R.id.skipBtn);

        viewPager = findViewById(R.id.viewPager);
        mDotLayout = findViewById(R.id.dotLayout);

        sliderAdaptar = new SliderAdaptar(IntroSliderActivity.this);

        viewPager.setAdapter(sliderAdaptar);
        mDotLayout.setupWithViewPager(viewPager, true);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentPage != LENGTH_OF_ITEMS - 1) {
                    viewPager.setCurrentItem(mCurrentPage + 1);
                } else {
                    startActivity(new Intent(IntroSliderActivity.this, recreate.india.main.startupcarvaan.loginsignup.loginActivity.class));
                    finish();
                    Log.i("TAG", "ENDDDD");
                }

            }
        });


        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroSliderActivity.this, recreate.india.main.startupcarvaan.loginsignup.loginActivity.class));
                finish();
            }
        });

        mDotLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                mCurrentPage = tab.getPosition();


                if (mCurrentPage == 0) {
                    nextBtn.setEnabled(true);
                    nextBtn.setVisibility(View.VISIBLE);
                    nextBtn.setText("Next");
                } else if (mCurrentPage == LENGTH_OF_ITEMS - 1) {
                    nextBtn.setEnabled(true);
                    nextBtn.setVisibility(View.VISIBLE);
                    nextBtn.setText("Finish");
                } else {
                    nextBtn.setEnabled(true);
                    nextBtn.setVisibility(View.VISIBLE);
                    nextBtn.setText("Next");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}