// Generated by view binder compiler. Do not edit!
package recreate.india.main.startupcarvaan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import recreate.india.main.startupcarvaan.R;

public final class ActivityUpdateProfileBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final EditText adharcardUp;

  @NonNull
  public final EditText descriptionUp;

  @NonNull
  public final TextView displayNameUp;

  @NonNull
  public final LinearLayout personalinfo;

  @NonNull
  public final Button submitUp;

  @NonNull
  public final EditText titleUp;

  @NonNull
  public final LinearLayout topbar;

  @NonNull
  public final EditText userAddressUp;

  @NonNull
  public final EditText userEmailUp;

  @NonNull
  public final ImageView userImageUp;

  @NonNull
  public final EditText userPhoneUp;

  private ActivityUpdateProfileBinding(@NonNull LinearLayout rootView,
      @NonNull EditText adharcardUp, @NonNull EditText descriptionUp,
      @NonNull TextView displayNameUp, @NonNull LinearLayout personalinfo, @NonNull Button submitUp,
      @NonNull EditText titleUp, @NonNull LinearLayout topbar, @NonNull EditText userAddressUp,
      @NonNull EditText userEmailUp, @NonNull ImageView userImageUp,
      @NonNull EditText userPhoneUp) {
    this.rootView = rootView;
    this.adharcardUp = adharcardUp;
    this.descriptionUp = descriptionUp;
    this.displayNameUp = displayNameUp;
    this.personalinfo = personalinfo;
    this.submitUp = submitUp;
    this.titleUp = titleUp;
    this.topbar = topbar;
    this.userAddressUp = userAddressUp;
    this.userEmailUp = userEmailUp;
    this.userImageUp = userImageUp;
    this.userPhoneUp = userPhoneUp;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityUpdateProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityUpdateProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_update_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityUpdateProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.adharcard_up;
      EditText adharcardUp = rootView.findViewById(id);
      if (adharcardUp == null) {
        break missingId;
      }

      id = R.id.description_up;
      EditText descriptionUp = rootView.findViewById(id);
      if (descriptionUp == null) {
        break missingId;
      }

      id = R.id.displayName_up;
      TextView displayNameUp = rootView.findViewById(id);
      if (displayNameUp == null) {
        break missingId;
      }

      id = R.id.personalinfo;
      LinearLayout personalinfo = rootView.findViewById(id);
      if (personalinfo == null) {
        break missingId;
      }

      id = R.id.submit_up;
      Button submitUp = rootView.findViewById(id);
      if (submitUp == null) {
        break missingId;
      }

      id = R.id.title_up;
      EditText titleUp = rootView.findViewById(id);
      if (titleUp == null) {
        break missingId;
      }

      id = R.id.topbar;
      LinearLayout topbar = rootView.findViewById(id);
      if (topbar == null) {
        break missingId;
      }

      id = R.id.userAddress_up;
      EditText userAddressUp = rootView.findViewById(id);
      if (userAddressUp == null) {
        break missingId;
      }

      id = R.id.userEmail_up;
      EditText userEmailUp = rootView.findViewById(id);
      if (userEmailUp == null) {
        break missingId;
      }

      id = R.id.userImage_up;
      ImageView userImageUp = rootView.findViewById(id);
      if (userImageUp == null) {
        break missingId;
      }

      id = R.id.userPhone_up;
      EditText userPhoneUp = rootView.findViewById(id);
      if (userPhoneUp == null) {
        break missingId;
      }

      return new ActivityUpdateProfileBinding((LinearLayout) rootView, adharcardUp, descriptionUp,
          displayNameUp, personalinfo, submitUp, titleUp, topbar, userAddressUp, userEmailUp,
          userImageUp, userPhoneUp);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
