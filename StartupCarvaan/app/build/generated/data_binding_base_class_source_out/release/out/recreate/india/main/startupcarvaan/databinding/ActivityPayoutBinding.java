// Generated by view binder compiler. Do not edit!
package recreate.india.main.startupcarvaan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import recreate.india.main.startupcarvaan.R;

public final class ActivityPayoutBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final EditText coins;

  @NonNull
  public final TextView convertedMoney;

  @NonNull
  public final EditText upiId;

  @NonNull
  public final EditText upiname;

  @NonNull
  public final EditText upiphonenumber;

  @NonNull
  public final Button withdrawButton;

  private ActivityPayoutBinding(@NonNull ScrollView rootView, @NonNull EditText coins,
      @NonNull TextView convertedMoney, @NonNull EditText upiId, @NonNull EditText upiname,
      @NonNull EditText upiphonenumber, @NonNull Button withdrawButton) {
    this.rootView = rootView;
    this.coins = coins;
    this.convertedMoney = convertedMoney;
    this.upiId = upiId;
    this.upiname = upiname;
    this.upiphonenumber = upiphonenumber;
    this.withdrawButton = withdrawButton;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_payout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.coins;
      EditText coins = rootView.findViewById(id);
      if (coins == null) {
        break missingId;
      }

      id = R.id.convertedMoney;
      TextView convertedMoney = rootView.findViewById(id);
      if (convertedMoney == null) {
        break missingId;
      }

      id = R.id.upi_id;
      EditText upiId = rootView.findViewById(id);
      if (upiId == null) {
        break missingId;
      }

      id = R.id.upiname;
      EditText upiname = rootView.findViewById(id);
      if (upiname == null) {
        break missingId;
      }

      id = R.id.upiphonenumber;
      EditText upiphonenumber = rootView.findViewById(id);
      if (upiphonenumber == null) {
        break missingId;
      }

      id = R.id.withdrawButton;
      Button withdrawButton = rootView.findViewById(id);
      if (withdrawButton == null) {
        break missingId;
      }

      return new ActivityPayoutBinding((ScrollView) rootView, coins, convertedMoney, upiId, upiname,
          upiphonenumber, withdrawButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
