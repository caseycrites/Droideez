package com.caseycrites.droideez;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TypefaceUtils {

  private TypefaceUtils() {}

  public static TypefaceCacheBuilder with(Context context) {
    return new TypefaceCacheBuilder(context);
  }

  public static class TypefaceCacheBuilder {

    private Context mContext;
    private String mAssetsDir = "fonts";

    private TypefaceCacheBuilder(Context context) {
      mContext = context;
    }

    public TypefaceCacheBuilder assetsDir(String dir) {
      mAssetsDir = dir;
      return this;
    }

    public TypefaceCache build() throws IOException {
      String[] fontNames = mContext.getAssets().list(mAssetsDir);
      if (fontNames.length == 0)
        throw new IllegalArgumentException("Must supply an assets directory containg fonts!");

      return new TypefaceCache(mContext, mAssetsDir, fontNames);
    }

  }

  public static class TypefaceCache {

    private final AssetManager mAM;
    private final Map<String, Typeface> mTypefaceMap;
    private final String mAssetsDir;

    private TypefaceCache(Context context, String assetsDir, String[] typefaceKeys) {
      mAM = context.getAssets();
      mAssetsDir = assetsDir;
      mTypefaceMap = new HashMap<String, Typeface>();
      for (String typefaceKey : typefaceKeys)
        mTypefaceMap.put(typefaceKey, null);
    }

    public Typeface getTypeface(String typefaceName) {
      if (!mTypefaceMap.containsKey(typefaceName))
        throw new IllegalArgumentException("No typeface found for name " + typefaceName);

      Typeface typeface = mTypefaceMap.get(typefaceName);

      if (typeface == null) {
        synchronized(this) {
          if (typeface == null) {
            typeface = Typeface.createFromAsset(mAM, mAssetsDir + "/" + typefaceName);
            mTypefaceMap.put(typefaceName, typeface);
          }
        }
      }

      return typeface;
    }

  }

}
