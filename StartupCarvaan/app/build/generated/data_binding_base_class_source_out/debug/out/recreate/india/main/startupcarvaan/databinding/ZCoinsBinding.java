// Generated by view binder compiler. Do not edit!
package recreate.india.main.startupcarvaan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import recreate.india.main.startupcarvaan.R;

public final class ZCoinsBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final Button RedeemNow;

  @NonNull
  public final TextView Redeemtext;

  @NonNull
  public final CircleImageView redeemImage;

  private ZCoinsBinding(@NonNull CardView rootView, @NonNull Button RedeemNow,
      @NonNull TextView Redeemtext, @NonNull CircleImageView redeemImage) {
    this.rootView = rootView;
    this.RedeemNow = RedeemNow;
    this.Redeemtext = Redeemtext;
    this.redeemImage = redeemImage;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ZCoinsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ZCoinsBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.z_coins, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ZCoinsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.RedeemNow;
      Button RedeemNow = rootView.findViewById(id);
      if (RedeemNow == null) {
        break missingId;
      }

      id = R.id.Redeemtext;
      TextView Redeemtext = rootView.findViewById(id);
      if (Redeemtext == null) {
        break missingId;
      }

      id = R.id.redeemImage;
      CircleImageView redeemImage = rootView.findViewById(id);
      if (redeemImage == null) {
        break missingId;
      }

      return new ZCoinsBinding((CardView) rootView, RedeemNow, Redeemtext, redeemImage);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
