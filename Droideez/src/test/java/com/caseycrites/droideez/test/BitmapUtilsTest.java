package com.caseycrites.droideez.test;

import android.graphics.Bitmap;
import android.net.Uri;

import com.caseycrites.droideez.BitmapUtils;
import com.caseycrites.droideez.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
public class BitmapUtilsTest {

  BitmapUtils bmUtils;
  final String rawBitmapPath = "android.resource://com.caseycrites.droideez/raw/android";

  @Before
  public void setUp() throws Exception {
    bmUtils = BitmapUtils.with(Robolectric.application.getApplicationContext());
  }

  @After
  public void tearDown() {
    bmUtils = null;
  }

  @Test
  public void decodeFromUri() throws Exception {
    Bitmap bm = bmUtils.decodeFromUri(Uri.parse(rawBitmapPath), 50, 50);

    assertThat(bm.getWidth()).isEqualTo(50);
    assertThat(bm.getHeight()).isEqualTo(50);

    bm.recycle();
  }

  @Test
  public void decodeFromResource() throws Exception {
    Bitmap bm = bmUtils.decodeFromResource(R.drawable.android, 100, 100);

    assertThat(bm.getWidth()).isEqualTo(100);
    assertThat(bm.getHeight()).isEqualTo(100);

    bm.recycle();
  }
}
