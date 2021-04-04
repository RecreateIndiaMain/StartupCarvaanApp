package recreate.india.main.startupcarvaan.aboutshare.modals;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.aboutshare.models.sharedetails;
import recreate.india.main.startupcarvaan.fragments.models.holdings;
import recreate.india.main.startupcarvaan.user.coin;
import recreate.india.main.startupcarvaan.user.user_share_functions;
import recreate.india.main.startupcarvaan.user.userfunctions;

public class sell extends DialogFragment {
    private TextView shares,price,available,price_of_share;
    private ImageView closebuy;
    private EditText share;
    private Button btnsell;
    Spinner spin;
    String item;
    String price_ ="";
    String share_;
    private sharedetails sharedetails=new sharedetails();
    private userfunctions userfunctions=new userfunctions();
    private coin coin=new coin();
    private user_share_functions user_share_functions=new user_share_functions();


    private holdings holdings=new holdings();
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dailogsell,null,false);
        share=view.findViewById(R.id.shares);
        price=view.findViewById(R.id.price);
        spin=view.findViewById(R.id.spinner3);
        available=view.findViewById(R.id.totalsharestosell);
        price_of_share=view.findViewById(R.id.price_of_shares);
        btnsell=view.findViewById(R.id.btn_sell);


        List<String> list = new ArrayList<String>();
        list.add(0, "price : no of shares");
        //retrieve holdings
        FirebaseFirestore.getInstance()
                .collection("users")
                .document("tupjdAJB8JcfMdzqc4P5iRIg0XE2")
                .collection("myshares")
                .document("shareid").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                holdings=value.toObject(holdings.class);
                Map<String,Double>holding=holdings.getHoldings();
                for (Map.Entry<String,Double> entry : holding.entrySet())
                    list.add(String.valueOf(entry.getKey()+" : "+entry.getValue()));
            }
        });
        //retrieving share details
        FirebaseFirestore.getInstance()
                .collection("allshares")
                .document("shareid")
                .collection("sharedetails")
                .document("sharedetails").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                sharedetails=task.getResult().toObject(sharedetails.class);
                available.setText("Available :"+String.valueOf(sharedetails.getAvailableforselling()));
                price_of_share.setText(sharedetails.getSellingprice()+" rci");
            }
        });

        // retrieving coin details
        FirebaseFirestore.getInstance()
                .collection("users")
                .document("tupjdAJB8JcfMdzqc4P5iRIg0XE2")
                .collection("others").document("coins").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                coin=value.toObject(coin.class);
            }
        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(arrayAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("price : no of shares")) {

                } else {
                    item = parent.getItemAtPosition(position).toString();
                    int i;
                    for(i=0;i<item.length();i++){
                        if(item.charAt(i)==' '){
                            break;
                        }
                        price_+=item.charAt(i);
                    }
                    i+=2;
                    share_=item.substring(i,item.length());
                    price.setText("price: "+price_);
                    share.setText(share_);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });
        btnsell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double price=Double.valueOf(price_);
                Double quantity=Double.valueOf(share.getText().toString());
                if(quantity>Double.valueOf(holdings.getHoldings().get(price_)))
                    Toast.makeText(getContext(), "do not have "+ quantity + " shares for price "+price_, Toast.LENGTH_SHORT).show();
                else{
                    Double resultant_price=price*quantity;
                    userfunctions.addRci(coin.getRci(),resultant_price);
                    user_share_functions.removeSomeShares("shareid",quantity,price);

                }
            }
        });
        builder.setView(view);
        return builder.create();

    }
}