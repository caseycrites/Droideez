package com.caseycrites.droideez.test;

import com.caseycrites.droideez.TypefaceUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
public class TypefaceUtilsTest {

  private static final String LIBRE_BOLD = "LibreBaskerville-Bold.ttf";
  private static final String LIBRE_ITALIC = "LibreBaskerville-Italic.ttf";
  private static final String LIBRE_REGULAR = "LibreBaskerville-Regular.ttf";

  @Test
  public void testSetupCache() throws Exception {
    TypefaceUtils.TypefaceCache cache = TypefaceUtils
      .with(Robolectric.application.getApplicationContext()).build();

    assertThat(cache).isNotNull();
    assertThat(cache.getTypeface(LIBRE_BOLD)).isNotNull();
    assertThat(cache.getTypeface(LIBRE_ITALIC)).isNotNull();
    assertThat(cache.getTypeface(LIBRE_REGULAR)).isNotNull();
  }

  @Test(expected=IllegalArgumentException.class)
  public void testSetupCacheBadFolder() throws Exception {
    TypefaceUtils.with(Robolectric.application.getApplicationContext())
      .assetsDir("garbage").build();
  }

  @Test(expected=IllegalArgumentException.class)
  public void testInvalidFont() throws Exception {
    TypefaceUtils.TypefaceCache cache = TypefaceUtils
      .with(Robolectric.application.getApplicationContext()).build();

    cache.getTypeface("garbage");
  }
}
