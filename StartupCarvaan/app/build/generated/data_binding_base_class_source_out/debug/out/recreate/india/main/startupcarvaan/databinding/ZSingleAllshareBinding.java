// Generated by view binder compiler. Do not edit!
package recreate.india.main.startupcarvaan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.github.mikephil.charting.charts.LineChart;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import recreate.india.main.startupcarvaan.R;

public final class ZSingleAllshareBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView advice;

  @NonNull
  public final LinearLayout afterpress;

  @NonNull
  public final TextView buyingprice;

  @NonNull
  public final LinearLayout colorlayout;

  @NonNull
  public final LinearLayout colorlayout2;

  @NonNull
  public final ImageView companylogo;

  @NonNull
  public final TextView companyname;

  @NonNull
  public final TextView group;

  @NonNull
  public final LinearLayout growth;

  @NonNull
  public final ProgressBar growthbar;

  @NonNull
  public final TextView growthtext;

  @NonNull
  public final YouTubePlayerView introvideo;

  @NonNull
  public final Button invest;

  @NonNull
  public final TextView investors;

  @NonNull
  public final LinearLayout lcompany;

  @NonNull
  public final LineChart lineChartPrice;

  @NonNull
  public final RelativeLayout loglayout;

  @NonNull
  public final TextView nextslot;

  @NonNull
  public final ImageView performance;

  @NonNull
  public final ConstraintLayout relativeLayout;

  @NonNull
  public final ImageView secondLayout;

  @NonNull
  public final TextView sellingprice;

  @NonNull
  public final TextView tags;

  @NonNull
  public final LinearLayout video;

  private ZSingleAllshareBinding(@NonNull CardView rootView, @NonNull TextView advice,
      @NonNull LinearLayout afterpress, @NonNull TextView buyingprice,
      @NonNull LinearLayout colorlayout, @NonNull LinearLayout colorlayout2,
      @NonNull ImageView companylogo, @NonNull TextView companyname, @NonNull TextView group,
      @NonNull LinearLayout growth, @NonNull ProgressBar growthbar, @NonNull TextView growthtext,
      @NonNull YouTubePlayerView introvideo, @NonNull Button invest, @NonNull TextView investors,
      @NonNull LinearLayout lcompany, @NonNull LineChart lineChartPrice,
      @NonNull RelativeLayout loglayout, @NonNull TextView nextslot, @NonNull ImageView performance,
      @NonNull ConstraintLayout relativeLayout, @NonNull ImageView secondLayout,
      @NonNull TextView sellingprice, @NonNull TextView tags, @NonNull LinearLayout video) {
    this.rootView = rootView;
    this.advice = advice;
    this.afterpress = afterpress;
    this.buyingprice = buyingprice;
    this.colorlayout = colorlayout;
    this.colorlayout2 = colorlayout2;
    this.companylogo = companylogo;
    this.companyname = companyname;
    this.group = group;
    this.growth = growth;
    this.growthbar = growthbar;
    this.growthtext = growthtext;
    this.introvideo = introvideo;
    this.invest = invest;
    this.investors = investors;
    this.lcompany = lcompany;
    this.lineChartPrice = lineChartPrice;
    this.loglayout = loglayout;
    this.nextslot = nextslot;
    this.performance = performance;
    this.relativeLayout = relativeLayout;
    this.secondLayout = secondLayout;
    this.sellingprice = sellingprice;
    this.tags = tags;
    this.video = video;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ZSingleAllshareBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ZSingleAllshareBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.z_single_allshare, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ZSingleAllshareBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.advice;
      TextView advice = rootView.findViewById(id);
      if (advice == null) {
        break missingId;
      }

      id = R.id.afterpress;
      LinearLayout afterpress = rootView.findViewById(id);
      if (afterpress == null) {
        break missingId;
      }

      id = R.id.buyingprice;
      TextView buyingprice = rootView.findViewById(id);
      if (buyingprice == null) {
        break missingId;
      }

      id = R.id.colorlayout;
      LinearLayout colorlayout = rootView.findViewById(id);
      if (colorlayout == null) {
        break missingId;
      }

      id = R.id.colorlayout2;
      LinearLayout colorlayout2 = rootView.findViewById(id);
      if (colorlayout2 == null) {
        break missingId;
      }

      id = R.id.companylogo;
      ImageView companylogo = rootView.findViewById(id);
      if (companylogo == null) {
        break missingId;
      }

      id = R.id.companyname;
      TextView companyname = rootView.findViewById(id);
      if (companyname == null) {
        break missingId;
      }

      id = R.id.group;
      TextView group = rootView.findViewById(id);
      if (group == null) {
        break missingId;
      }

      id = R.id.growth;
      LinearLayout growth = rootView.findViewById(id);
      if (growth == null) {
        break missingId;
      }

      id = R.id.growthbar;
      ProgressBar growthbar = rootView.findViewById(id);
      if (growthbar == null) {
        break missingId;
      }

      id = R.id.growthtext;
      TextView growthtext = rootView.findViewById(id);
      if (growthtext == null) {
        break missingId;
      }

      id = R.id.introvideo;
      YouTubePlayerView introvideo = rootView.findViewById(id);
      if (introvideo == null) {
        break missingId;
      }

      id = R.id.invest;
      Button invest = rootView.findViewById(id);
      if (invest == null) {
        break missingId;
      }

      id = R.id.investors;
      TextView investors = rootView.findViewById(id);
      if (investors == null) {
        break missingId;
      }

      id = R.id.lcompany;
      LinearLayout lcompany = rootView.findViewById(id);
      if (lcompany == null) {
        break missingId;
      }

      id = R.id.lineChart_price;
      LineChart lineChartPrice = rootView.findViewById(id);
      if (lineChartPrice == null) {
        break missingId;
      }

      id = R.id.loglayout;
      RelativeLayout loglayout = rootView.findViewById(id);
      if (loglayout == null) {
        break missingId;
      }

      id = R.id.nextslot;
      TextView nextslot = rootView.findViewById(id);
      if (nextslot == null) {
        break missingId;
      }

      id = R.id.performance;
      ImageView performance = rootView.findViewById(id);
      if (performance == null) {
        break missingId;
      }

      id = R.id.relativeLayout;
      ConstraintLayout relativeLayout = rootView.findViewById(id);
      if (relativeLayout == null) {
        break missingId;
      }

      id = R.id.secondLayout;
      ImageView secondLayout = rootView.findViewById(id);
      if (secondLayout == null) {
        break missingId;
      }

      id = R.id.sellingprice;
      TextView sellingprice = rootView.findViewById(id);
      if (sellingprice == null) {
        break missingId;
      }

      id = R.id.tags;
      TextView tags = rootView.findViewById(id);
      if (tags == null) {
        break missingId;
      }

      id = R.id.video;
      LinearLayout video = rootView.findViewById(id);
      if (video == null) {
        break missingId;
      }

      return new ZSingleAllshareBinding((CardView) rootView, advice, afterpress, buyingprice,
          colorlayout, colorlayout2, companylogo, companyname, group, growth, growthbar, growthtext,
          introvideo, invest, investors, lcompany, lineChartPrice, loglayout, nextslot, performance,
          relativeLayout, secondLayout, sellingprice, tags, video);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
