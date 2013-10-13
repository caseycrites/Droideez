package com.caseycrites.droideez;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Collection of Intent related methods.
 */
public class IntentUtils {

  /**
   *  Intent extra key signifying a logout request.
   */
  public static final String LOGOUT_KEY = "droideez_logout";

  private static Class mHomeActivity;
  private static Class mLogoutActivity;

  private IntentUtils() {}

  // defaults

  /**
   * Default top level (home) activity class.
   *
   * @param homeActivityClass
   */
  public static void setHomeActivity(Class homeActivityClass) {
    mHomeActivity = homeActivityClass;
  }

  /**
   * Default activity class to be directed to after being logged out.
   *
   * Like a splash screen or login/register screen.
   *
   * @param logoutActivityClass
   */
  public static void setLogoutActivity(Class logoutActivityClass) {
    mLogoutActivity = logoutActivityClass;
  }

  // logout intent creation

  /**
   * Create an intent with the logout key as an extra for the specified class.
   *
   * @param context
   * @param homeActivityClass
   * @return Intent
   */
  public static Intent createLogoutIntent(Context context, Class homeActivityClass) {
    Intent i = baseIntent(context, homeActivityClass);
    i.putExtra(LOGOUT_KEY, true);
    return i;
  }

  /**
   * Create an intent with the logout key as an extra for the default class.
   *
   * @param context
   * @return Intent
   */
  public static Intent createLogoutIntent(Context context) {
    if (mHomeActivity == null)
      throw new IllegalArgumentException("Must either set a default home activity or set at runtime.");
    Intent i = baseIntent(context, mHomeActivity);
    i.putExtra(LOGOUT_KEY, true);
    return i;
  }

  // logout

  /**
   * Logout of the app if LOGOUT_KEY is present in the extras.
   *
   * @param a Activity checking for logout.
   * @param i Intent to check for logout.
   * @param loggedOutActivityClass
   */
  public static void logoutIfTold(Activity a, Intent i, Class loggedOutActivityClass) {
    if (i.getBooleanExtra(LOGOUT_KEY, false)) {
      a.startActivity(baseIntent(a.getBaseContext(), loggedOutActivityClass));
      a.finish();
    }
  }

  /**
   * Logout of the app if LOGOUT_KEY is present in the extras.
   *
   * @param a Activity checking for logout.
   * @param i Intent to check for logout.
   */
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
