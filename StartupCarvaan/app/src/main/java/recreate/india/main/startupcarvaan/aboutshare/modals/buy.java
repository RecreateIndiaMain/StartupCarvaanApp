package recreate.india.main.startupcarvaan.aboutshare.modals;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.aboutshare.models.sharedetails;
import recreate.india.main.startupcarvaan.fragments.models.sharefunctions;
import recreate.india.main.startupcarvaan.fragments.mycoins.coin;
import recreate.india.main.startupcarvaan.user.user_share_functions;
import recreate.india.main.startupcarvaan.user.userfunctions;

public class buy extends DialogFragment {
    //local variables declaration
    private TextView priceofshare,availableforbuying;
    private EditText no_of_shares;
    private Button buy_now;
    private ImageView closebuy;



    // constructor declaration
    private user_share_functions usersharefunctions= new user_share_functions();
    private sharedetails sharedetails=new sharedetails();
    private coin coin=new coin();
    private sharefunctions sharefunctions=new sharefunctions();
    private userfunctions userfunctions=new userfunctions();



    public buy() {
    }

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dailogbuy,null,false);
        // from intent
        Bundle bundle=getArguments();
        String shareid=bundle.getString("shareid");

        //assigning id to variables
        priceofshare=view.findViewById(R.id.price_of_shares);
        availableforbuying=view.findViewById(R.id.availableforbuying);
        no_of_shares=view.findViewById(R.id.noofshares);
        buy_now=view.findViewById(R.id.btn_buy);
        closebuy=view.findViewById(R.id.close68);
        closebuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        // retrieving share details
        FirebaseFirestore.getInstance()
                .collection("allshares")
                .document("shareid")
                .collection("sharedetails").document("sharedetails").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                sharedetails=value.toObject(sharedetails.class);
                priceofshare.setText(String.valueOf(sharedetails.getBuyingprice()+" Rci"));
                availableforbuying.setText(String.valueOf("Available :"+sharedetails.getAvailableforbuying()));
            }
        });

        // retrieving coin details
        FirebaseFirestore.getInstance()
                .collection("users")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("others").document("coins").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                coin=value.toObject(coin.class);
            }
        });

        buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(no_of_shares.getText().toString().equals(""))
                    Toast.makeText(getContext(), "please enter a valid quantity", Toast.LENGTH_SHORT).show();
                else{
                    Integer share_price=Integer.valueOf(sharedetails.getBuyingprice());
                    Integer quantity=Integer.valueOf(no_of_shares.getText().toString());
                    if(quantity<=sharedetails.getAvailableforbuying()) {
                        Integer resultant_price=share_price*quantity;
                        if(resultant_price>coin.getRci()){
                            Toast.makeText(getContext(), "you do not have sufficient funds, please add some", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            // checking is user already a investor
                            FirebaseFirestore.getInstance().collection("users")
                                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .collection("myshares")
                                    .document("shareid").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if(task.getResult().exists()){
                                        usersharefunctions.updateShare("shareid",share_price,quantity);
                                        sharefunctions.removeAvailableBuy("shareid",sharedetails.getAvailableforbuying(),quantity);
                                        sharefunctions.addSell("shareid",sharedetails.getAvailableforselling(),quantity);
                                        userfunctions.removeRci(coin.getRci(),resultant_price);
                                        userfunctions.addPoints(.1*resultant_price);
                                    }
                                    else{
                                        usersharefunctions.addNewShare("shareid",share_price,quantity);
                                        usersharefunctions.addUser("shareid");
                                        sharefunctions.removeAvailableBuy("shareid",sharedetails.getAvailableforbuying(),quantity);
                                        sharefunctions.addSell("shareid",sharedetails.getAvailableforselling(),quantity);
                                        userfunctions.removeRci(coin.getRci(),resultant_price);
                                        userfunctions.addPoints(.1*resultant_price);
                                    }
                                    dismiss();
                                }
                            });
                        }
                    }
                    else{
                        Toast.makeText(getContext(), "sorry these much share are not available for buying right now", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        builder.setView(view);
        return builder.create();
    }
}