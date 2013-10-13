package com.caseycrites.droideez;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Collection of methods related to Views.
 */
public class ViewUtils {

  private ViewUtils() {}

  // view finders

  /**
   * Find a child view of any type.
   *
   * @param v Parent view
   * @param childId Child view id.
   * @return View
   */
  public static <V extends View> V view(View v, int childId) {
    return (V) v.findViewById(childId);
  }

  /**
   * Find a child view of any type.
   *
   * @param a Parent activity.
   * @param childId Child view id.
   * @return View
   */
  public static <V extends View> V view(Activity a, int childId) {
    return (V) a.findViewById(childId);
  }

  // android default view finders

  /**
   * Get the default android list view.
   *
   * android.R.id.list, defined in xml by @android:id/list
   *
   * @param v Parent view
   * @return ListView
   */
  public static ListView androidListView(View v) {
    ListView lv = view(v, android.R.id.list);
    return lv;
  }

  /**
   * Get the default android list view.
   *
   * android.R.id.list, defined in xml by @android:id/list
   *
   * @param a Parent activity
   * @return ListView
   */
  public static ListView androidListView(Activity a) {
    ListView lv = view(a, android.R.id.list);
    return lv;
  }

  /**
   * Get the default android empty list view.
   *
   * android.R.id.empty, defined in xml by @android:id/empty
   *
   * @param v Parent view
   * @return View
   */
  public static <V> V androidEmptyView(View v) {
    return (V) view(v, android.R.id.empty);
  }

  /**
   * Get the default android empty list view.
   *
   * android.R.id.empty, defined in xml by @android:id/empty
   *
   * @param a Parent activity
   * @return View
   */
  public static <V> V androidEmptyView(Activity a) {
    return (V) view(a, android.R.id.empty);
  }

  // view visibility

  /**
   * Show a hidden view.
   *
   * @param v
   */
  public static void show(View v) {
    if (v.getVisibility() == View.GONE)
      v.setVisibility(View.VISIBLE);
  }

  /**
   * Show or hide a view.
   *
   * @param v
   * @param shouldShow
   */
  public static void show(View v, boolean shouldShow) {
    if (shouldShow)
      show(v);
    else
      hide(v);
  }

  /**
   * Hide a view.
   *
   * @param v
   */
  public static void hide(View v) {
    if (v.getVisibility() == View.VISIBLE)
      v.setVisibility(View.GONE);
  }

  // text

  /**
   * Set text of a child view.
   *
   * @param v Parent view.
   * @param childId Child view id whose text should be set.
   * @param text
   * @return TextView the child whose text was set.
   */
  public static TextView setText(View v, int childId, CharSequence text) {
    TextView tv = view(v, childId);
    tv.setText(text);
    return tv;
  }

  /**
   * Set text of a child view.
   *
   * @param a Parent activity.
   * @param childId Child view id whose text should be set.
   * @param text
   * @return TextView the child whose text was set.
   */
  public static TextView setText(Activity a, int childId, CharSequence text) {
    TextView tv = view(a, childId);
    tv.setText(text);
    return tv;
  }

}
