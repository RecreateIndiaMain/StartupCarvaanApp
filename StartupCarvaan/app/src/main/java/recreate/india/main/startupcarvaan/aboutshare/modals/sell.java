package recreate.india.main.startupcarvaan.aboutshare.modals;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.fragments.models.holdings;

public class sell extends DialogFragment {
    private TextView shares;
    private ImageView closebuy;
    private EditText share;
    private TextView price;
    Spinner spin;
    String item;
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dailogsell,null,false);
        share=view.findViewById(R.id.shares);
        price=view.findViewById(R.id.price);
        spin=view.findViewById(R.id.spinner3);
        List<String> list = new ArrayList<String>();
        list.add(0, "price : no of shares");
        list.add("10 : 80");
        list.add("20 : 90");
        list.add("30 : 100");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(arrayAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("price : no of shares")) {

                } else {
                    item = parent.getItemAtPosition(position).toString();
                    String price_ ="";
                    String share_;
                    int i;
                    for(i=0;i<item.length();i++){
                        if(item.charAt(i)==' '){
                            break;
                        }
                        price_+=item.charAt(i);
                    }
                    i+=2;
                    share_=item.substring(i,item.length());
                    price.setText(price_);
                    share.setText(share_);
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