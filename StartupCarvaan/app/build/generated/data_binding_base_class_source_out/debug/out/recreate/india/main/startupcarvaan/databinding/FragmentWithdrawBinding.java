// Generated by view binder compiler. Do not edit!
package recreate.india.main.startupcarvaan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import recreate.india.main.startupcarvaan.R;

public final class FragmentWithdrawBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final RecyclerView withdrawMoneyTransactionRecyclerView;

  private FragmentWithdrawBinding(@NonNull FrameLayout rootView,
      @NonNull RecyclerView withdrawMoneyTransactionRecyclerView) {
    this.rootView = rootView;
    this.withdrawMoneyTransactionRecyclerView = withdrawMoneyTransactionRecyclerView;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentWithdrawBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentWithdrawBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_withdraw, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentWithdrawBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.withdraw_moneyTransaction_recycler_view;
      RecyclerView withdrawMoneyTransactionRecyclerView = rootView.findViewById(id);
      if (withdrawMoneyTransactionRecyclerView == null) {
        break missingId;
      }

      return new FragmentWithdrawBinding((FrameLayout) rootView,
          withdrawMoneyTransactionRecyclerView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
