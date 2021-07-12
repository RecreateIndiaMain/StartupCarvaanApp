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

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.aboutshare.models.sharedetails;
import recreate.india.main.startupcarvaan.allmodels.reward.Level;
import recreate.india.main.startupcarvaan.allmodels.share.ShareFunctions;
import recreate.india.main.startupcarvaan.allmodels.user.ShareHoldings;
import recreate.india.main.startupcarvaan.allmodels.user.UserFunctions;

import recreate.india.main.startupcarvaan.user.user;



public class sell extends DialogFragment {
    private TextView shares,price,available,price_of_share;
    private ImageView closesell;
    private EditText share;
    private Button btnsell;
    Spinner spin;
    String item;
    String price_ ="";
    String share_;
    private sharedetails sharedetails=new sharedetails();
    private String shareid;
    private FirebaseFirestore ff= FirebaseFirestore.getInstance();
    private FirebaseUser mUser;
    ShareHoldings holdings;
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dailogsell,null,false);
        Bundle bundle=getArguments();
        shareid=bundle.getString("shareid");
        share=view.findViewById(R.id.shares);
        price=view.findViewById(R.id.price);
        spin=view.findViewById(R.id.spinner3);
        available=view.findViewById(R.id.totalsharestosell);
        price_of_share=view.findViewById(R.id.price_of_shares);
        btnsell=view.findViewById(R.id.btn_sell);

        mUser=FirebaseAuth.getInstance().getCurrentUser();
        List<String> list = new ArrayList<String>();
        list.add(0, "Price of a share : No. of shares");

        // TODO:  Share details need to be fetched and worked out

        //retrieve holdings
        FirebaseFirestore.getInstance()
                .collection("users")
                .document(new user().user().getUid())
                .collection("myshares")
                .document(shareid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                holdings = value.toObject(ShareHoldings.class);
                Map<String,ArrayList<Double>>holding=holdings.getHoldings();
                for (Map.Entry<String, ArrayList<Double>> entry : holding.entrySet())
                    list.add(String.valueOf(entry.getKey()+" : "+entry.getValue()));
            }
        });
        //retrieving share details
        FirebaseFirestore.getInstance()
                .collection("allshares")
                .document(shareid)
                .collection("sharedetails")
                .document("sharedetails").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                sharedetails=value.toObject(sharedetails.class);
                available.setText("Available :"+String.valueOf(sharedetails.getAvailableforselling()/sharedetails.getSellingprice()));
                price_of_share.setText(sharedetails.getSellingprice()+" rci");
            }
        });
        // retrieving coin details
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(arrayAdapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Price of a share : No. of shares")) {
                } else {
                    item = parent.getItemAtPosition(position).toString();
                    int i;
                    for(i=0;i<item.length();i++){
                        if(item.charAt(i)==' '){
                            break;
                        }
                        price_+=item.charAt(i);
                    }
                    i+=3    ;
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
                if(price_.equals(""))
                    Toast.makeText(getContext(), "please select one holding", Toast.LENGTH_SHORT).show();
                else {
                    String quan=share.getText().toString();
                    if(quan.equals(""))
                        Toast.makeText(getContext(), "please select a amount", Toast.LENGTH_SHORT).show();
                    else{
                        Double price=new ShareFunctions(shareid).trading.getSellingprice();
                        Double quantity=Double.valueOf(String.valueOf(share.getText().toString()));
                        String day="1.0";
//                        if(quantity>Double.valueOf(holdings.getHoldings().get(price_)))
//                            Toast.makeText(getContext(), "do not have "+ quantity + " shares for price "+price_, Toast.LENGTH_SHORT).show();
//                        else{
//                            UserFunctions userFunctions=new UserFunctions();
//                            userFunctions.addPendingTransaction(shareid,quantity,price,"sell");
//                            userFunctions.removeShares(shareid,day,quantity,price);
//                            dismiss();
//                        }
                    }
                }
            }
        });
        builder.setView(view);
        return builder.create();
    }

    // Model changed please look at the transaction details model
//
//    public void completed_transaction(String startupName,String quantity,String price){
//        Timestamp timestamp=Timestamp.now();
//        transaction_details transaction_details=new transaction_details(startupName,quantity,price,false,timestamp);
//        ff.collection("users").document(mUser.getUid()).collection("transactions").document("details").update("completed",transaction_details).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful())
//                {
//                    ff.collection("allshares")
//                            .document(shareid).collection("transactions")
//                            .document("details")
//                            .update("completed",transaction_details)
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    Toast.makeText(getContext(), "Successfully bought", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                }
//            }
//        });
//    }
//    public void sell_request(String startupName,String quantity,String price){
//        Timestamp timestamp=Timestamp.now();
//        transaction_details transaction_details=new transaction_details(startupName,quantity,price,false,timestamp);
//        ff.collection("users").document(mUser.getUid()).collection("transactions").document("details").update("pending",transaction_details).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful())
//                {
//                    ff.collection("allshares")
//                            .document("shareid")
//                            .collection("transactions")
//                            .document("details").update("pending",transaction_details).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            Toast.makeText(getContext(), "Request sent", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                }
//            }
//        });
//    }

}