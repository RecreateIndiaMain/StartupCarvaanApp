package recreate.india.main.startupcarvaan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private boolean check=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        imageView=findViewById(R.id.contact_img);
        textView=findViewById(R.id.contact_text);
        textView.setVisibility(View.GONE);
        textView.setText(getString(R.string.ContactUs));
        imageView.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageDropdown();
            }
        });



    }

    private void manageDropdown() {
        if(check){
            check=false;
            imageView.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24);
            textView.setVisibility(View.VISIBLE);
        }
        else{
            check=true;
            imageView.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24);
            textView.setVisibility(View.GONE);
        }
    }
}