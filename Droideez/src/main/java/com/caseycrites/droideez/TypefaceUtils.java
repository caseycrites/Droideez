package com.caseycrites.droideez;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Collection of methods related to Typefaces.
 */
public class TypefaceUtils {

  private TypefaceUtils() {}

  /**
   * Get a TypefaceCacheBuilder instance.
   *
   * @param context
   * @return TypefaceCacheBuilder
   */
  public static TypefaceCacheBuilder with(Context context) {
    return new TypefaceCacheBuilder(context);
  }

  /**
   * Builder class for creating a TypefaceCache.
   */
  public static class TypefaceCacheBuilder {

    private Context mContext;
    private String mAssetsDir = "fonts";

    private TypefaceCacheBuilder(Context context) {
      mContext = context;
    }

    /**
     * Assets directory where your custom fonts live.
     *
     * @param dir
     * @return TypefaceCacheBuilder
     */
    public TypefaceCacheBuilder assetsDir(String dir) {
      mAssetsDir = dir;
      return this;
    }

    /**
     * Build your very own TypefaceCache.
     *
     * @return TypefaceCache
     * @throws IOException
     */
    public TypefaceCache build() throws IOException {
      String[] fontNames = mContext.getAssets().list(mAssetsDir);
      if (fontNames.length == 0)
        throw new IllegalArgumentException("Must supply an assets directory containing fonts!");

      return new TypefaceCache(mContext, mAssetsDir, fontNames);
    }

  }

  /**
   * A class that ensures you only load that custom Typeface once.
   */
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

    /**
     * Retrieve your typeface by name.
     *
     * If you have a typeface file named 'Droideez.ttf', use that name to retrieve it.
     *
     * @param typefaceName
     * @return Typeface
     */
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
