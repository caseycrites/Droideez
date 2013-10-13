package com.caseycrites.droideez.test;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.caseycrites.droideez.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import static com.caseycrites.droideez.ViewUtils.androidEmptyView;
import static com.caseycrites.droideez.ViewUtils.androidListView;
import static com.caseycrites.droideez.ViewUtils.hide;
import static com.caseycrites.droideez.ViewUtils.setText;
import static com.caseycrites.droideez.ViewUtils.show;
import static com.caseycrites.droideez.ViewUtils.view;
import static org.fest.assertions.api.ANDROID.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
public class ViewUtilsTest {

  public static class ViewUtilsActivity extends Activity {
  }

  private ViewUtilsActivity viewUtilsActivity;
  private View v;

  @Before
  public void setUp() throws Exception {
    viewUtilsActivity = Robolectric.buildActivity(ViewUtilsActivity.class).create().get();
    v = LayoutInflater.from(Robolectric.application.getApplicationContext())
      .inflate(R.layout.view_utils_test, null);
    viewUtilsActivity.setContentView(v);
  }

  @After
  public void tearDown() {
    viewUtilsActivity = null;
    v = null;
  }

  @Test
  public void testFindViewsInView() {
    ProgressBar pb = view(v, R.id.progress_bar);
    assertThat(pb).isNotNull();

    LinearLayout ll = view(v, R.id.linear_layout_holder);
    assertThat(ll).isNotNull();

    ImageButton ib = view(ll, R.id.image_button);
    assertThat(ib).isNotNull();
  }

  @Test
  public void testFindViewsInActivity() {
    ProgressBar pb = view(viewUtilsActivity, R.id.progress_bar);
    assertThat(pb).isNotNull();

    LinearLayout ll = view(viewUtilsActivity, R.id.linear_layout_holder);
    assertThat(ll).isNotNull();

    ImageButton ib = view(viewUtilsActivity, R.id.image_button);
    assertThat(ib).isNotNull();
  }

  @Test
  public void testFindAndroidViewsInView() {
    ListView lv = androidListView(v);
    assertThat(lv).isNotNull();

    TextView tv = androidEmptyView(v);
    assertThat(tv).isNotNull();
  }

  @Test
  public void testFindAndroidViewsInActivity() {
    ListView lv = androidListView(viewUtilsActivity);
    assertThat(lv).isNotNull();

    TextView tv = androidEmptyView(viewUtilsActivity);
    assertThat(tv).isNotNull();
  }

  @Test
  public void testHideView() {
    ListView lv = androidListView(v);
    assertThat(lv).isVisible();

    hide(lv);
    assertThat(lv).isNotVisible();
  }

  @Test
  public void testShowView() {
    TextView tv = androidEmptyView(v);
    assertThat(tv).isNotVisible();

    show(tv);
    assertThat(tv).isVisible();
  }

  @Test
  public void testShowHideView() {
    ListView lv = androidListView(v);
    assertThat(lv).isVisible();

    show(lv, false);
    assertThat(lv).isNotVisible();

    // shouldn't change
    show(lv, false);
    assertThat(lv).isNotVisible();

    TextView tv = androidEmptyView(v);
    assertThat(tv).isNotVisible();

    // shouldn't change
    show(tv, false);
    assertThat(tv).isNotVisible();

    show(tv, true);
    assertThat(tv).isVisible();
  }

  @Test
  public void testSetText() {
    TextView tv = androidEmptyView(v);
    assertThat(tv).containsText("");

    setText(v, android.R.id.empty, "you are");
    assertThat(tv).containsText("you are");
  }

}
