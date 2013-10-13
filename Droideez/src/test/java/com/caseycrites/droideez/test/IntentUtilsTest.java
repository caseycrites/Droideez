package com.caseycrites.droideez.test;

import android.app.Activity;
import android.content.Intent;

import com.caseycrites.droideez.IntentUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static org.fest.assertions.api.ANDROID.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
public class IntentUtilsTest {

  public static class HomeActivity extends Activity {
  }

  public static class LogoutActivity extends Activity {
  }

  public static class ChildActivity extends Activity {
  }

  ChildActivity childActivity;
  HomeActivity homeActivity;
  LogoutActivity logoutActivity;

  @Before
  public void setUp() throws Exception {
    childActivity = Robolectric.buildActivity(ChildActivity.class).create().get();
    homeActivity = Robolectric.buildActivity(HomeActivity.class).create().get();
    logoutActivity = Robolectric.buildActivity(LogoutActivity.class).create().get();
  }

  @After
  public void tearDown() {
    homeActivity = null;
    logoutActivity = null;
  }

  @Test
  public void testCreateLogoutIntent() {
    Intent i = IntentUtils.createLogoutIntent(childActivity, HomeActivity.class);

    assertThat(i.getExtras()).hasKey(IntentUtils.LOGOUT_KEY);
    assertTrue(i.getExtras().getBoolean(IntentUtils.LOGOUT_KEY));
    assertThat(i).hasComponent(childActivity, HomeActivity.class);
    assertThat(i).hasFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testCreateLogoutIntentNoHome() {
    IntentUtils.createLogoutIntent(childActivity);
  }

  @Test
  public void testCreateLogoutIntentDefault() {
    IntentUtils.setHomeActivity(HomeActivity.class);

    Intent i = IntentUtils.createLogoutIntent(childActivity);

    assertThat(i.getExtras()).hasKey(IntentUtils.LOGOUT_KEY);
    assertTrue(i.getExtras().getBoolean(IntentUtils.LOGOUT_KEY));
    assertThat(i).hasComponent(childActivity, HomeActivity.class);
    assertThat(i).hasFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
  }

  @Test
  public void testLogoutIfTold() {
    Intent i = IntentUtils.createLogoutIntent(childActivity, HomeActivity.class);

    IntentUtils.logoutIfTold(homeActivity, i, LogoutActivity.class);

    assertThat(homeActivity).isFinishing();
  }

  @Test(expected=IllegalArgumentException.class)
  public void testLogoutIfToldNoLogout() {
    Intent i = IntentUtils.createLogoutIntent(childActivity, HomeActivity.class);

    IntentUtils.logoutIfTold(homeActivity, i);
  }

  @Test
  public void testLogoutIfToldDefault() {
    IntentUtils.setLogoutActivity(LogoutActivity.class);

    Intent i = IntentUtils.createLogoutIntent(childActivity, HomeActivity.class);

    IntentUtils.logoutIfTold(homeActivity, i);

    assertThat(homeActivity).isFinishing();
  }

  @Test
  public void testLogoutIfToldNoLogoutKey() {
    IntentUtils.logoutIfTold(homeActivity, new Intent(), LogoutActivity.class);

    assertThat(homeActivity).isNotFinishing();
  }

}
