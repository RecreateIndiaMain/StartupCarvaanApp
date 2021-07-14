// Generated by view binder compiler. Do not edit!
package recreate.india.main.startupcarvaan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import recreate.india.main.startupcarvaan.R;

public final class ActivityBloggingBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final RecyclerView aboutshare;

  @NonNull
  public final LinearLayout applayout;

  @NonNull
  public final ImageView backblog;

  @NonNull
  public final Button bla;

  @NonNull
  public final Button blaaa;

  @NonNull
  public final BottomNavigationView buySellBottomNav;

  private ActivityBloggingBinding(@NonNull RelativeLayout rootView,
      @NonNull RecyclerView aboutshare, @NonNull LinearLayout applayout,
      @NonNull ImageView backblog, @NonNull Button bla, @NonNull Button blaaa,
      @NonNull BottomNavigationView buySellBottomNav) {
    this.rootView = rootView;
    this.aboutshare = aboutshare;
    this.applayout = applayout;
    this.backblog = backblog;
    this.bla = bla;
    this.blaaa = blaaa;
    this.buySellBottomNav = buySellBottomNav;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityBloggingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityBloggingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_blogging, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityBloggingBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.aboutshare;
      RecyclerView aboutshare = rootView.findViewById(id);
      if (aboutshare == null) {
        break missingId;
      }

      id = R.id.applayout;
      LinearLayout applayout = rootView.findViewById(id);
      if (applayout == null) {
        break missingId;
      }

      id = R.id.backblog;
      ImageView backblog = rootView.findViewById(id);
      if (backblog == null) {
        break missingId;
      }

      id = R.id.bla;
      Button bla = rootView.findViewById(id);
      if (bla == null) {
        break missingId;
      }

      id = R.id.blaaa;
      Button blaaa = rootView.findViewById(id);
      if (blaaa == null) {
        break missingId;
      }

      id = R.id.buy_sell_bottom_nav;
      BottomNavigationView buySellBottomNav = rootView.findViewById(id);
      if (buySellBottomNav == null) {
        break missingId;
      }

      return new ActivityBloggingBinding((RelativeLayout) rootView, aboutshare, applayout, backblog,
          bla, blaaa, buySellBottomNav);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
