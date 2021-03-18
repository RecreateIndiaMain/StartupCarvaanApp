package recreate.india.main.startupcarvaan.aboutshare.modals;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

import recreate.india.main.startupcarvaan.R;

public class sell extends DialogFragment {
    private TextView shares;
    private ImageView closebuy;
    Spinner spin;
    String item;
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dailogsell,null,false);
        spin=view.findViewById(R.id.spinner3);
        List<String> list = new ArrayList<String>();
        list.add(0, "price : no of shares");
        list.add("20 :100");
        list.add("20 :100");
        list.add("20 :100");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(arrayAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select problem")) {

                } else {
                    item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected:" + item, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        builder.setView(view);
        return builder.create();

    }
}