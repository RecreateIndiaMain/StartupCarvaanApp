// Generated by view binder compiler. Do not edit!
package recreate.india.main.startupcarvaan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import recreate.india.main.startupcarvaan.R;

public final class SingleTransactionBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView amountTrs;

  @NonNull
  public final TextView boughtSoldTrs;

  @NonNull
  public final TextView priceTrs;

  @NonNull
  public final TextView quantityTrs;

  @NonNull
  public final TextView startupNameTrs;

  private SingleTransactionBinding(@NonNull ConstraintLayout rootView, @NonNull TextView amountTrs,
      @NonNull TextView boughtSoldTrs, @NonNull TextView priceTrs, @NonNull TextView quantityTrs,
      @NonNull TextView startupNameTrs) {
    this.rootView = rootView;
    this.amountTrs = amountTrs;
    this.boughtSoldTrs = boughtSoldTrs;
    this.priceTrs = priceTrs;
    this.quantityTrs = quantityTrs;
    this.startupNameTrs = startupNameTrs;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static SingleTransactionBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static SingleTransactionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.single_transaction, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static SingleTransactionBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.amount_trs;
      TextView amountTrs = rootView.findViewById(id);
      if (amountTrs == null) {
        break missingId;
      }

      id = R.id.bought_sold_trs;
      TextView boughtSoldTrs = rootView.findViewById(id);
      if (boughtSoldTrs == null) {
        break missingId;
      }

      id = R.id.price_trs;
      TextView priceTrs = rootView.findViewById(id);
      if (priceTrs == null) {
        break missingId;
      }

      id = R.id.quantity_trs;
      TextView quantityTrs = rootView.findViewById(id);
      if (quantityTrs == null) {
        break missingId;
      }

      id = R.id.startup_name_trs;
      TextView startupNameTrs = rootView.findViewById(id);
      if (startupNameTrs == null) {
        break missingId;
      }

      return new SingleTransactionBinding((ConstraintLayout) rootView, amountTrs, boughtSoldTrs,
          priceTrs, quantityTrs, startupNameTrs);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}