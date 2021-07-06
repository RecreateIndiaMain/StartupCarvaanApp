// Generated by view binder compiler. Do not edit!
package recreate.india.main.startupcarvaan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import recreate.india.main.startupcarvaan.R;

public final class ZSingleBidingBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final EditText bidprice;

  @NonNull
  public final TextView currentprice;

  @NonNull
  public final TextView currentwinner;

  @NonNull
  public final TextView description;

  @NonNull
  public final TextView join;

  @NonNull
  public final ImageView productimage;

  @NonNull
  public final TextView timer;

  @NonNull
  public final TextView title;

  @NonNull
  public final LinearLayout underprice;

  private ZSingleBidingBinding(@NonNull CardView rootView, @NonNull EditText bidprice,
      @NonNull TextView currentprice, @NonNull TextView currentwinner,
      @NonNull TextView description, @NonNull TextView join, @NonNull ImageView productimage,
      @NonNull TextView timer, @NonNull TextView title, @NonNull LinearLayout underprice) {
    this.rootView = rootView;
    this.bidprice = bidprice;
    this.currentprice = currentprice;
    this.currentwinner = currentwinner;
    this.description = description;
    this.join = join;
    this.productimage = productimage;
    this.timer = timer;
    this.title = title;
    this.underprice = underprice;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ZSingleBidingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ZSingleBidingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.z_single_biding, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ZSingleBidingBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bidprice;
      EditText bidprice = rootView.findViewById(id);
      if (bidprice == null) {
        break missingId;
      }

      id = R.id.currentprice;
      TextView currentprice = rootView.findViewById(id);
      if (currentprice == null) {
        break missingId;
      }

      id = R.id.currentwinner;
      TextView currentwinner = rootView.findViewById(id);
      if (currentwinner == null) {
        break missingId;
      }

      id = R.id.description;
      TextView description = rootView.findViewById(id);
      if (description == null) {
        break missingId;
      }

      id = R.id.join;
      TextView join = rootView.findViewById(id);
      if (join == null) {
        break missingId;
      }

      id = R.id.productimage;
      ImageView productimage = rootView.findViewById(id);
      if (productimage == null) {
        break missingId;
      }

      id = R.id.timer;
      TextView timer = rootView.findViewById(id);
      if (timer == null) {
        break missingId;
      }

      id = R.id.title;
      TextView title = rootView.findViewById(id);
      if (title == null) {
        break missingId;
      }

      id = R.id.underprice;
      LinearLayout underprice = rootView.findViewById(id);
      if (underprice == null) {
        break missingId;
      }

      return new ZSingleBidingBinding((CardView) rootView, bidprice, currentprice, currentwinner,
          description, join, productimage, timer, title, underprice);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
