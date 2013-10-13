package com.caseycrites.droideez;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ViewUtils {

  private ViewUtils() {}

  // view finders

  public static <V extends View> V view(View v, int childId) {
    return (V) v.findViewById(childId);
  }

  public static <V extends View> V view(Activity a, int childId) {
    return (V) a.findViewById(childId);
  }

  // android default view finders

  public static ListView androidListView(View v) {
    ListView lv = view(v, android.R.id.list);
    return lv;
  }

  public static ListView androidListView(Activity a) {
    ListView lv = view(a, android.R.id.list);
    return lv;
  }

  public static <V> V androidEmptyView(View v) {
    return (V) view(v, android.R.id.empty);
  }

  public static <V> V androidEmptyView(Activity a) {
    return (V) view(a, android.R.id.empty);
  }

  // view visibility

  public static void show(View v) {
    if (v.getVisibility() == View.GONE)
      v.setVisibility(View.VISIBLE);
  }

  public static void show(View v, boolean shouldShow) {
    if (shouldShow)
      show(v);
    else
      hide(v);
  }

  public static void hide(View v) {
    if (v.getVisibility() == View.VISIBLE)
      v.setVisibility(View.GONE);
  }

  // text

  public static TextView setText(View v, int childId, CharSequence text) {
    TextView tv = view(v, childId);
    tv.setText(text);
    return tv;
  }

  public static TextView setText(Activity a, int childId, CharSequence text) {
    TextView tv = view(a, childId);
    tv.setText(text);
    return tv;
  }

}
