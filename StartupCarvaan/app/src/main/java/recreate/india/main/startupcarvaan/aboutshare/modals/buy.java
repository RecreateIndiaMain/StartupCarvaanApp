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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.allmodels.share.ShareFunctions;
import recreate.india.main.startupcarvaan.allmodels.share.sharedetails.Trading;
import recreate.india.main.startupcarvaan.allmodels.user.UserFunctions;

public class buy extends DialogFragment {
    //local variables declaration
    private TextView priceofshare,availableforbuying;
    private EditText no_of_shares;
    private Button buy_now;
    private ImageView closebuy;
    private String shareid;
    private FirebaseUser mUser;
    private FirebaseFirestore ff=FirebaseFirestore.getInstance();
    public ShareFunctions shareFunctions;
    public UserFunctions userFunctions=new UserFunctions();
    Double buying_price;
    // constructor declaration
    public buy() {
    }

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dailogbuy,null,false);
        // from intent
        Bundle bundle=getArguments();
        shareid=bundle.getString("shareid");
        shareFunctions=new ShareFunctions(shareid);
//        Toast.makeText(getContext(), String.valueOf(shareFunctions.trading.getBuyvolume()), Toast.LENGTH_SHORT).show();
        //assigning id to variables
        priceofshare=view.findViewById(R.id.price_of_shares);
        no_of_shares=view.findViewById(R.id.noofshares);
        buy_now=view.findViewById(R.id.btn_buy);
        closebuy=view.findViewById(R.id.close68);
        mUser=FirebaseAuth.getInstance().getCurrentUser();

        final Trading[] trading = {new Trading()};

        ff.collection("startup").document(shareid).collection("sharedetails").document("trading").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

            trading[0] =documentSnapshot.toObject(Trading.class);
            buying_price=trading[0].getBuyingprice();
                priceofshare.setText(buying_price+"rci");
            }
        });

        closebuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(no_of_shares.getText().toString().equals(""))
                    Toast.makeText(getContext(), "please enter a valid quantity"+String.valueOf(shareFunctions.trading.getBuyingprice()), Toast.LENGTH_SHORT).show();
                else{
                    Double quantity=Double.valueOf(no_of_shares.getText().toString());
                    Double amount=buying_price*quantity;
                    if(userFunctions.check_rci(amount)){
                        userFunctions.deduct_rci(amount);
                        userFunctions.addPendingTransaction(shareid,quantity,buying_price,"buy");
                        Toast.makeText(view.getContext(), "Thank you for investing with us..Your request will be processed shortly..", Toast.LENGTH_LONG).show();
                        dismiss();
                    }
                    else Toast.makeText(getContext(), "you do not have enough amount", Toast.LENGTH_SHORT).show();
                }
            }
        });



        builder.setView(view);
        return builder.create();
    }

    // Model is changed please look at the transactions_detals

    /*

    public void completed_transaction(String startupName,String quantity,String price){
        Timestamp timestamp=Timestamp.now();
        TransactionDetails TransactionDetails =new TransactionDetails(startupName,quantity,price,true,timestamp);
       ff.collection("users").document(mUser.getUid()).collection("transactions").document("details").update("completed", TransactionDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    ff.collection("allshares")
                            .document(shareid).collection("transactions")
                            .document("details")
                            .update("completed", TransactionDetails)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(getContext(), "Successfully bought", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    public void buy_request(String startupName,String quantity,String price){
        Timestamp timestamp=Timestamp.now();
        TransactionDetails TransactionDetails =new TransactionDetails(startupName,quantity,price,true,timestamp);
        ff.collection("users").document(mUser.getUid()).collection("transactions").document("details").update("pending", TransactionDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                ff.collection("allshares")
                        .document("shareid")
                        .collection("transactions")
                        .document("details").update("pending", TransactionDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(), "Request sent", Toast.LENGTH_SHORT).show();
                    }
                });

                }
            }
        });
    }
    */
}