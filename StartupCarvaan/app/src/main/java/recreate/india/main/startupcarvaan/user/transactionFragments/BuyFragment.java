package recreate.india.main.startupcarvaan.user.transactionFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import recreate.india.main.startupcarvaan.R;
import recreate.india.main.startupcarvaan.allmodels.user.UserMoneyTransactions;


public class BuyFragment extends Fragment {





    public BuyFragment() {
        // Required empty public constructor
    }
    private UserMoneyTransactions userMoneyTransactions=new UserMoneyTransactions();
    private FirebaseFirestore ff=FirebaseFirestore.getInstance();
    private FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_buy, container, false);
        recyclerView=view.findViewById(R.id.buy_moneyTransaction_recycler_view);


        Query query=  ff.collection("users").document(firebaseUser.getUid()).collection("boughtRci");



        return view;
    }
}