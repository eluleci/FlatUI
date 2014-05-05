package com.cengalabs.flatui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: eluleci
 * Date: 25.10.2013
 * Time: 15:00
 */
public class FlatUI {

    public static final String androidStyleNameSpace = "http://schemas.android.com/apk/res/android";

    public static final int SAND = R.array.sand;
    public static final int ORANGE = R.array.orange;
    public static final int CANDY = R.array.candy;
    public static final int BLOSSOM = R.array.blossom;
    public static final int GRAPE = R.array.grape;
    public static final int DEEP = R.array.deep;
    public static final int SKY = R.array.sky;
    public static final int GRASS = R.array.grass;
    public static final int DARK = R.array.dark;
    public static final int SNOW = R.array.snow;
    public static final int SEA = R.array.sea;
    public static final int BLOOD = R.array.blood;

    public static Typeface getFont(Context context, Attributes attributes) {

        String fontPath = "fonts/" + attributes.getFontFamily()
                + "_" + attributes.getFontWeight()
                + "." + attributes.getFontExtension();

        try {
            return Typeface.createFromAsset(context.getAssets(), fontPath);
        } catch (Exception e) {
            Log.e("FlatUI", "Font file at " + fontPath + " cannot be found or the file is " +
                    "not a valid font file. Please be sure that library assets are included " +
                    "to project. If not, copy assets/fonts folder of the library to your " +
                    "projects assets folder.");
            return null;
        }
    }

    /**
     * Sets action bar drawable with given attributes. Can be used for standard Activity ActionBar.
     * If you are using Action Bar Compatibility, you can use getActionBarDrawable() method with
     * same attributes and apply drawable manually.
     *
     * @param activity context
     * @param theme    selected theme
     * @param dark     boolean for choosing dark colors or primary colors
     */
    public static void setActionBarTheme(Activity activity, int theme, boolean dark) {

        Drawable drawable = getActionBarDrawable(activity, theme, dark);

        ActionBar actionBar = activity.getActionBar();
        actionBar.setBackgroundDrawable(drawable);
    }

    /**
     * Returns a suitable drawable for ActionBar with theme colors. Should be used in case of usage
     * of Action Bar Compatibility library.
     *
     * @param theme selected theme
     * @param dark  boolean for choosing dark colors or primary colors
     * @return drawable to be used in ActionBar
     */
    public static Drawable getActionBarDrawable(Activity activity, int theme, boolean dark) {
        int[] colors = activity.getResources().getIntArray(theme);

        int color1 = colors[2];
        int color2 = colors[1];

        if (dark) {
            color1 = colors[1];
            color2 = colors[0];
        }

        PaintDrawable front = new PaintDrawable(color1);
        PaintDrawable bottom = new PaintDrawable(color2);
        Drawable[] d = {bottom, front};
        LayerDrawable drawable = new LayerDrawable(d);
        drawable.setLayerInset(1, 0, 0, 0, 3);
        return drawable;
    }

    public static void setDefaultTheme(int theme) {
        Attributes.DEFAULT_THEME = theme;
    }
}