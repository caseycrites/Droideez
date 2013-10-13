package com.caseycrites.droideez;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class IntentUtils {

  public static final String LOGOUT_KEY = "droideez_logout";

  private static Class mHomeActivity;
  private static Class mLogoutActivity;

  private IntentUtils() {}

  // defaults

  public static void setHomeActivity(Class homeActivityClass) {
    mHomeActivity = homeActivityClass;
  }

  public static void setLogoutActivity(Class logoutActivityClass) {
    mLogoutActivity = logoutActivityClass;
  }

  // logout intent creation

  public static Intent createLogoutIntent(Context context, Class homeActivityClass) {
    Intent i = baseIntent(context, homeActivityClass);
    i.putExtra(LOGOUT_KEY, true);
    return i;
  }

  public static Intent createLogoutIntent(Context context) {
    if (mHomeActivity == null)
      throw new IllegalArgumentException("Must either set a default home activity or set at runtime.");
    Intent i = baseIntent(context, mHomeActivity);
    i.putExtra(LOGOUT_KEY, true);
    return i;
  }

  // logout

  public static void logoutIfTold(Activity a, Intent i, Class loggedOutActivityClass) {
    if (i.getBooleanExtra(LOGOUT_KEY, false)) {
      a.startActivity(baseIntent(a.getBaseContext(), loggedOutActivityClass));
      a.finish();
    }
  }

  public static void logoutIfTold(Activity a, Intent i) {
    if (mLogoutActivity == null)
      throw new IllegalArgumentException("Must either set a default logout activity or supply at runtime.");
    if (i.getBooleanExtra(LOGOUT_KEY, false)) {
      a.startActivity(baseIntent(a.getBaseContext(), mLogoutActivity));
      a.finish();
    }
  }

  // helper

  private static Intent baseIntent(Context context, Class clazz) {
    Intent i = new Intent(context, clazz);
    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    return i;
  }
}
