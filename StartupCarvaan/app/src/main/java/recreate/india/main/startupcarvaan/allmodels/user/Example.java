package recreate.india.main.startupcarvaan.allmodels.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.allmodels.user.UserFunctions;
import recreate.india.main.startupcarvaan.allmodels.user.UserProfile;

public class Example extends AppCompatActivity {
    UserFunctions userFunctions=new UserFunctions();
    UserProfile userProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        userFunctions.getUserProfile();
        userProfile=userFunctions.userProfile;
    }
}