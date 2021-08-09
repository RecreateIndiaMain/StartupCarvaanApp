// Generated by view binder compiler. Do not edit!
package recreate.india.main.startupcarvaan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import recreate.india.main.startupcarvaan.R;

public final class ZSinglePracticeBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final Button bet;

  @NonNull
  public final EditText betting;

  @NonNull
  public final CircleImageView companylogo;

  @NonNull
  public final TextView companyname;

  @NonNull
  public final TextView currentbet;

  @NonNull
  public final TextView currentvotes;

  @NonNull
  public final TextView description;

  @NonNull
  public final TextView rate;

  @NonNull
  public final YouTubePlayerView videoplayer;

  @NonNull
  public final Button vote;

  private ZSinglePracticeBinding(@NonNull CardView rootView, @NonNull Button bet,
      @NonNull EditText betting, @NonNull CircleImageView companylogo,
      @NonNull TextView companyname, @NonNull TextView currentbet, @NonNull TextView currentvotes,
      @NonNull TextView description, @NonNull TextView rate, @NonNull YouTubePlayerView videoplayer,
      @NonNull Button vote) {
    this.rootView = rootView;
    this.bet = bet;
    this.betting = betting;
    this.companylogo = companylogo;
    this.companyname = companyname;
    this.currentbet = currentbet;
    this.currentvotes = currentvotes;
    this.description = description;
    this.rate = rate;
    this.videoplayer = videoplayer;
    this.vote = vote;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ZSinglePracticeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ZSinglePracticeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.z_single_practice, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ZSinglePracticeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bet;
      Button bet = rootView.findViewById(id);
      if (bet == null) {
        break missingId;
      }

      id = R.id.betting;
      EditText betting = rootView.findViewById(id);
      if (betting == null) {
        break missingId;
      }

      id = R.id.companylogo;
      CircleImageView companylogo = rootView.findViewById(id);
      if (companylogo == null) {
        break missingId;
      }

      id = R.id.companyname;
      TextView companyname = rootView.findViewById(id);
      if (companyname == null) {
        break missingId;
      }

      id = R.id.currentbet;
      TextView currentbet = rootView.findViewById(id);
      if (currentbet == null) {
        break missingId;
      }

      id = R.id.currentvotes;
      TextView currentvotes = rootView.findViewById(id);
      if (currentvotes == null) {
        break missingId;
      }

      id = R.id.description;
      TextView description = rootView.findViewById(id);
      if (description == null) {
        break missingId;
      }

      id = R.id.rate;
      TextView rate = rootView.findViewById(id);
      if (rate == null) {
        break missingId;
      }

      id = R.id.videoplayer;
      YouTubePlayerView videoplayer = rootView.findViewById(id);
      if (videoplayer == null) {
        break missingId;
      }

      id = R.id.vote;
      Button vote = rootView.findViewById(id);
      if (vote == null) {
        break missingId;
      }

      return new ZSinglePracticeBinding((CardView) rootView, bet, betting, companylogo, companyname,
          currentbet, currentvotes, description, rate, videoplayer, vote);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}