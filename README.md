# Droideez

A collection of Android utility classes for things I use frequently.

## BitmapUtils

Downsample a bitmap to 100x100 from a Uri or resource id.

    Bitmap bm = BitmapUtils.with(context).decodeFromUri(someUri, 100, 100);
    Bitmap bm = BitmapUtils.with(context).decodeFromResource(R.drawable.someBm, 100, 100);

## TypefaceUtils

Create a cache of custom typefaces. *Android currently doesn't release or reuse custom
typefaces, so if you use them a lot without caching them you're leaking a lot of memory.*

    // Using 'fonts' as the default assets directory where your fonts live.
    MY_STATIC_CACHE = TypefaceUtils.with(context).build();
    Typeface myTypeface = MY_STATIC_CACHE.getTypeface("Droideez.ttf");

## ViewUtils

Shorthand for commonly used view methods. Use static imports for even shorter-hand.

    // No casting required
    TextView tv = ViewUtils.view(parentView, R.id.my_text_view);
    MyCustomView mcv = ViewUtils.view(parentActivity, R.id.my_custom_view);
    ViewUtils.hide(tv);
    ViewUtils.show(mcv, this > that);
    TextView tv2 = ViewUtils.setText(parentView, R.id.my_other_text_view, "Droideez");

## IntentUtils

Tell your home activity to logout. This is a useful way to logout if you have an activity
hierarchy that contains a top-level home activity that uses the launch mode "singleTop".

    // Set on app launch
    IntentUtils.setHomeActivity(HomeActivity.class);
    IntentUtils.setLogoutActivity(LoginActivity.class);

    // From a child activity
    startActivity(IntentUtils.createLogoutIntent(this));
    finish();

Check if you should log out in your home activity.

    @Override
    protected void onNewIntent(Intent i) {
        super.onNewIntent(i);

        if (IntentUtils.logoutIfTold(this, i))
            return;
    }

Listen for logouts, maybe clean things up.

    // Set on app launch
    IntentUtils.setOnLogoutListener(myLogoutListener);

    // Some class
    public class SomeClass implements OnLogoutListener {
        @Override
        public void onLogout() {
            getSharedPreferences("my.prefs").edit().clear().commit();
            ACTIVE_USER = null;
        }
    }

## StreamUtils

Close an InputStream quietly:

    StreamUtils.closeQuietly(stream);

instead of:

    try {
    } finally {
        try {
            stream.close()
        } catch (IOException e) {}
    }

## WindowUtils

Get the dimensions of the default display.

    Point dimensions = WindowUtils.with(context).getDimensions();
    Bitmap bm = BitmapUtils.with(context).decodeFromUri(someUri, dimensions.x, dimensions.y);
