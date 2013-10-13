package com.caseycrites.droideez;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class BitmapUtils {

  private Context mContext;

  private BitmapUtils(Context context) {
    mContext = context;
  }

  public static BitmapUtils with(Context context) {
    return new BitmapUtils(context);
  }

  // decoders

  public Bitmap decodeFromUri(Uri bitmapUri, int reqWidth, int reqHeight)
    throws FileNotFoundException {
    final BitmapFactory.Options options = justDecodeOptions();

    InputStream is = mContext.getContentResolver().openInputStream(bitmapUri);
    BitmapFactory.decodeStream(is, null, options);
    StreamUtils.closeQuitely(is);

    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

    is = mContext.getContentResolver().openInputStream(bitmapUri);
    options.inJustDecodeBounds = false;
    Bitmap bm = BitmapFactory.decodeStream(is, null, options);
    StreamUtils.closeQuitely(is);

    return bm;
  }

  public Bitmap decodeFromResource(int resId, int reqWidth, int reqHeight) {
    final BitmapFactory.Options options = justDecodeOptions();
    Resources res = mContext.getResources();

    BitmapFactory.decodeResource(res, resId, options);

    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

    options.inJustDecodeBounds = false;
    return BitmapFactory.decodeResource(res, resId, options);
  }

  // helpers

  private BitmapFactory.Options justDecodeOptions() {
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    return options;
  }

  private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {
      final int heightRatio = Math.round((float) height / (float) reqHeight);
      final int widthRatio = Math.round((float) width / (float) reqWidth);

      inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
    }
    return inSampleSize;
  }
}
