package com.caseycrites.droideez;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

/**
 * Collection of methods related to fragments
 */
public class FragmentUtils {

  private Context mContext;
  private FragmentManager mFragManager;
  private FragmentTransaction mFragTransaction;

  private FragmentUtils(Context context, FragmentManager fragManager) {
    mContext = context;
    mFragManager = fragManager;
  }

  public static FragmentUtils with(Context context, FragmentManager fragManager) {
    return new FragmentUtils(context, fragManager);
  }

  private int getFragmentParentId(Fragment f) {
    return ((View)f.getView().getParent()).getId();
  }

  @TargetApi(Build.VERSION_CODES.HONEYCOMB)
  public FragmentTransaction beginTransaction() {
    if (mFragTransaction == null)
      synchronized (this) {
        if (mFragTransaction == null)
          mFragTransaction = mFragManager.beginTransaction();
      }
    return mFragTransaction;
  }

  @TargetApi(Build.VERSION_CODES.HONEYCOMB)
  public FragmentTransaction addFragment(int parentResId, Fragment f, String tag) {
    if (TextUtils.isEmpty(tag))
      return beginTransaction().add(parentResId, f);
    else
      return beginTransaction().add(parentResId, f, tag);
  }

  @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
  public FragmentTransaction attachFragment(Fragment f) {
    return beginTransaction().attach(f);
  }

  @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
  public FragmentTransaction detachFragment(Fragment f) {
    return beginTransaction().detach(f);
  }

  @TargetApi(Build.VERSION_CODES.HONEYCOMB)
  public Fragment getOrCreateFragment(String tag, String fragClassName) {
    return getOrCreateFragment(tag, fragClassName, null);
  }

  @TargetApi(Build.VERSION_CODES.HONEYCOMB)
  public Fragment getOrCreateFragment(String tag, String fragClassName, Bundle args) {
    Fragment f = mFragManager.findFragmentByTag(tag);
    if (f == null)
      if (args == null)
        f = Fragment.instantiate(mContext, fragClassName);
      else
        f = Fragment.instantiate(mContext, fragClassName, args);
    return f;
  }

  @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
  public FragmentTransaction addOrAttachFragment(int parentResId, Fragment f) {
    return addOrAttachFragment(parentResId, f, null);
  }

  @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
  public FragmentTransaction addOrAttachFragment(int parentResId, Fragment f, String tag) {
    boolean added = f.isAdded();
    if (added && f.isDetached())
      return attachFragment(f);
    else if (!added)
      return addFragment(parentResId, f, tag);
    throw new IllegalArgumentException("Tried to attach a Fragment that's already attached!");
  }

  public FragmentTransaction swapFragments(Fragment currentFragment, Fragment newFragment) {
    return swapFragments(currentFragment, newFragment, null);
  }

  public FragmentTransaction swapFragments(Fragment currentFragment, Fragment newFragment, String tag) {
    FragmentTransaction ft = beginTransaction();

    int parentId = getFragmentParentId(currentFragment);
    detachFragment(currentFragment);

    addOrAttachFragment(parentId, newFragment, tag);
    return ft;
  }

}
