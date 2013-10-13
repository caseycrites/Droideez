package com.caseycrites.droideez.test;

import android.graphics.Point;

import com.caseycrites.droideez.WindowUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static org.fest.assertions.api.ANDROID.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
public class WindowUtilsTest {

  private WindowUtils wUtils;

  @Before
  public void setUp() throws Exception {
    wUtils = WindowUtils.with(Robolectric.application.getApplicationContext());
  }

  @After
  public void tearDown() {
    wUtils = null;
  }

  @Test
  public void testGetDimensions() {
    Point dimensions = wUtils.getDimensions();

    // no real screen here
    assertThat(dimensions).isNotNull();
    assertThat(dimensions).hasX(0);
    assertThat(dimensions).hasY(0);
  }

}
