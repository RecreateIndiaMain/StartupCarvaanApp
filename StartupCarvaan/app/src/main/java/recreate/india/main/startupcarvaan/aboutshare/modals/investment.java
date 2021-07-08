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
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.aboutshare.models.sharedetails;
import recreate.india.main.startupcarvaan.allmodels.share.sharedetails.TransactionDetails;
import recreate.india.main.startupcarvaan.fragments.models.sharefunctions;
import recreate.india.main.startupcarvaan.fragments.mycoins.coin;
import recreate.india.main.startupcarvaan.user.user_share_functions;
import recreate.india.main.startupcarvaan.user.userfunctions;

public class investment extends DialogFragment {
    //local variables declaration
    private TextView priceofshare,availableforbuying;
    private EditText no_of_shares;
    private Button buy_now;
    private ImageView closebuy;
    private String shareid;
    private FirebaseUser mUser;
    private FirebaseFirestore ff=FirebaseFirestore.getInstance();

    // constructor declaration
    private user_share_functions usersharefunctions= new user_share_functions();
    private sharedetails sharedetails=new sharedetails();
    private coin coin=new coin();
    private sharefunctions sharefunctions=new sharefunctions();
    private userfunctions userfunctions=new userfunctions();

    public investment() {
    }

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dailogbuy,null,false);
        // from intent
        Bundle bundle=getArguments();
        shareid=bundle.getString("shareid");

        //assigning id to variables
        priceofshare=view.findViewById(R.id.price_of_shares);
        availableforbuying=view.findViewById(R.id.availableforbuying);
        no_of_shares=view.findViewById(R.id.noofshares);
        buy_now=view.findViewById(R.id.btn_buy);
        closebuy=view.findViewById(R.id.close68);
        mUser=FirebaseAuth.getInstance().getCurrentUser();
        closebuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        // retrieving share details
        FirebaseFirestore.getInstance()
                .collection("allshares")
                .document(shareid)
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
                // double investment=Double.valueof(quantity * price)
                /*
                    if(check_investment(investment)){
                        deduct_rci(investment);
                        addpoints(investment*0.1)
                        if(if_already_user_has_share(shareid)){
                            update_user_share_rel;
                        }
                        else{
                            add_new_user_share_relation;
                        }
                        add_pending_transaction_to_share(shareid,quantity,price);
                        add_pending_transaction_to_user(shareid,quantity,price);
                    }
                    else{
                        Toast(you do not have enough amount)
                    }
                 */


            }
        });
        builder.setView(view);
        return builder.create();
    }
}