package com.caseycrites.droideez.test;

import com.caseycrites.droideez.FragmentUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
public class FragmentUtilsTest {

  FragmentUtils fUtils;

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() {
    fUtils = null;
  }

  @Test
  public void addOrAttachFragment() {
  }

}