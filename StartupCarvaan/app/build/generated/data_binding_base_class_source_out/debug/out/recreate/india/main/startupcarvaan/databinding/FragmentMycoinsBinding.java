// Generated by view binder compiler. Do not edit!
package recreate.india.main.startupcarvaan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import recreate.india.main.startupcarvaan.R;

public final class FragmentMycoinsBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final TextView bonus;

  @NonNull
  public final Button btnEarnBonus;

  @NonNull
  public final Button buy;

  @NonNull
  public final TextView rci;

  @NonNull
  public final Button sell;

  @NonNull
  public final TextView textView3;

  private FragmentMycoinsBinding(@NonNull ScrollView rootView, @NonNull TextView bonus,
      @NonNull Button btnEarnBonus, @NonNull Button buy, @NonNull TextView rci,
      @NonNull Button sell, @NonNull TextView textView3) {
    this.rootView = rootView;
    this.bonus = bonus;
    this.btnEarnBonus = btnEarnBonus;
    this.buy = buy;
    this.rci = rci;
    this.sell = sell;
    this.textView3 = textView3;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentMycoinsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentMycoinsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_mycoins, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentMycoinsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bonus;
      TextView bonus = rootView.findViewById(id);
      if (bonus == null) {
        break missingId;
      }

      id = R.id.btn_earnBonus;
      Button btnEarnBonus = rootView.findViewById(id);
      if (btnEarnBonus == null) {
        break missingId;
      }

      id = R.id.buy;
      Button buy = rootView.findViewById(id);
      if (buy == null) {
        break missingId;
      }

      id = R.id.rci;
      TextView rci = rootView.findViewById(id);
      if (rci == null) {
        break missingId;
      }

      id = R.id.sell;
      Button sell = rootView.findViewById(id);
      if (sell == null) {
        break missingId;
      }

      id = R.id.textView3;
      TextView textView3 = rootView.findViewById(id);
      if (textView3 == null) {
        break missingId;
      }

      return new FragmentMycoinsBinding((ScrollView) rootView, bonus, btnEarnBonus, buy, rci, sell,
          textView3);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
