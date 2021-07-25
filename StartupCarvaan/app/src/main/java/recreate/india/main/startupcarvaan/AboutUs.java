package recreate.india.main.startupcarvaan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {

    private ImageView drop2,drop1;
    private TextView textView1,textView2;
    private boolean check1=false,check2=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        drop2=findViewById(R.id.contact_img2);
        textView2=findViewById(R.id.contact_text2);
        drop1=findViewById(R.id.contact_img1);
        textView1=findViewById(R.id.contact_text1);
        textView2.setVisibility(View.GONE);
        textView2.setText(getString(R.string.ContactUs));
        drop2.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
        drop1.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);

        drop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageDropdown2();
            }
        });
        drop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageDropdown1();
            }
        });

    }

    private void manageDropdown2() {
        if(check2){
            check2=false;
            drop2.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
            textView2.setVisibility(View.VISIBLE);
        }
        else{
            check2=true;
            drop2.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
            textView2.setVisibility(View.GONE);
        }
    }

    private void manageDropdown1() {
        if(check1){
            check1=false;
            drop1.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
            textView1.setVisibility(View.VISIBLE);
        }
        else{
            check1=true;
            drop1.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
            textView1.setVisibility(View.GONE);
        }
    }
}