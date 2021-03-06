package com.caseycrites.droideez;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

/**
 * Collection of methods related to the device screen.
 */
public class WindowUtils {

  private boolean mThirteenOrHigher;
  private Display mDisplay;

  private WindowUtils(Context context) {
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    mDisplay = wm.getDefaultDisplay();
    mThirteenOrHigher = Build.VERSION.SDK_INT >= 13;
  }

  /**
   * Get an WindowUtils instance.
   *
   * @param context
   * @return WindowUtils
   */
  public static WindowUtils with(Context context) {
    return new WindowUtils(context);
  }

  /**
   * Get the default displays dimensions.
   *
   * @return Point
   */
  @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
  public Point getDimensions() {
    Point size = new Point();
    if (mThirteenOrHigher) {
      mDisplay.getSize(size);
    } else {
      size.x = mDisplay.getWidth();
      size.y = mDisplay.getHeight();
    }
    return size;
  }
}
