package recreate.india.main.startupcarvaan.fragments.progressdialogue;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import recreate.india.main.startupcarvaan.R;

public class CustomProgressDialogue extends Dialog {
    public CustomProgressDialogue(@NonNull Context context) {
        super(context);
        WindowManager.LayoutParams params=getWindow().getAttributes();
        getWindow().setAttributes(params);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        View view= LayoutInflater.from(context).inflate(R.layout.loading_layout,null);
        setContentView(view);

    }
}
