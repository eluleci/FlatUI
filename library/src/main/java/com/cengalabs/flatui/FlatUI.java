package com.cengalabs.flatui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.util.Log;

import com.cengalabs.flatui.constants.Colors;

/**
 * Created with IntelliJ IDEA.
 * User: eluleci
 * Date: 25.10.2013
 * Time: 15:00
 */
public class FlatUI implements Colors {

    public static final int DEFAULT_FONT_FAMILY = 2;
    public static final int DEFAULT_FONT_WEIGHT = 1;
    public static int DEFAULT_THEME = 5;

    public static final int SAND = 0;
    public static final int ORANGE = 1;
    public static final int CANDY = 2;
    public static final int BLOSSOM = 3;
    public static final int GRAPE = 4;
    public static final int DEEP = 5;
    public static final int SKY = 6;
    public static final int GRASS = 7;
    public static final int DARK = 8;
    public static final int SNOW = 9;
    public static final int SEA = 10;
    public static final int BLOOD = 11;

    public static int[] getColor(int theme) {

        if (theme == SAND)
            return COLOR_SAND;
        else if (theme == ORANGE)
            return COLOR_ORANGE;
        else if (theme == CANDY)
            return COLOR_CANDY;
        else if (theme == BLOSSOM)
            return COLOR_BLOSSOM;
        else if (theme == GRAPE)
            return COLOR_GRAPE;
        else if (theme == DEEP)
            return COLOR_DEEP;
        else if (theme == SKY)
            return COLOR_SKY;
        else if (theme == GRASS)
            return COLOR_GRASS;
        else if (theme == DARK)
            return COLOR_DARK;
        else if (theme == SNOW)
            return COLOR_SNOW;
        else if (theme == SEA)
            return COLOR_SEA;
        else if (theme == BLOOD)
            return COLOR_BLOOD;

        return COLOR_CANDY;
    }

    public static Typeface getFont(Context context, int fontId, int weight) {
        String fontName = "";
        String fontWeight = "";

        if (fontId != 0) {

            if (fontId == 1) fontName = "opensans";
            else if (fontId == 2) fontName = "roboto";
            else if (fontId == 3) fontName = "comfortaa";

            switch (weight) {
                case 0:
                    fontWeight = "extralight.ttf";
                    break;
                case 1:
                    fontWeight = "light.ttf";
                    break;
                case 2:
                    fontWeight = "regular.ttf";
                    break;
                case 3:
                    fontWeight = "bold.ttf";
                    break;
                case 4:
                    fontWeight = "extrabold.ttf";
                    break;
            }

            try {
                return Typeface.createFromAsset(context.getAssets(),
                        "fonts/" + fontName + "_" + fontWeight);
            } catch (Exception e) {
                Log.e("FlatUI", "Font files cannot be found. Please be sure that library assets" +
                        " are included to project. If not, copy assets/fonts folder of the" +
                        " library to your projects assets folder.");
            }
        }
        return null;
    }

    /**
     * Sets action bar drawable with given attributes. Can be used for standard Activity ActionBar.
     * If you are using Action Bar Compatibility, you can use getActionBarDrawable() method with
     * same attributes and apply drawable manually.
     *
     * @param activity context
     * @param theme selected theme
     * @param dark boolean for choosing dark colors or primary colors
     * @param titleEnabled used for hiding/showing action bar title after changing drawable
     */
    public static void setActionBarTheme(Activity activity, int theme, boolean dark, boolean titleEnabled) {

        Drawable drawable = getActionBarDrawable(theme, dark);

        ActionBar actionBar = activity.getActionBar();
        actionBar.setBackgroundDrawable(drawable);
        actionBar.setDisplayShowTitleEnabled(!titleEnabled);
        actionBar.setDisplayShowTitleEnabled(titleEnabled);
    }

    /**
     * Returns a suitable drawable for ActionBar with theme colors. Should be used in case of usage
     * of Action Bar Compatibility library.
     *
     * @param theme selected theme
     * @param dark boolean for choosing dark colors or primary colors
     * @return drawable to be used in ActionBar
     */
    public static Drawable getActionBarDrawable(int theme, boolean dark) {
        int[] color = getColor(theme);

        int color1 = color[2];
        int color2 = color[1];

        if (dark) {
            color1 = color[1];
            color2 = color[0];
        }

        PaintDrawable front = new PaintDrawable(color1);
        PaintDrawable bottom = new PaintDrawable(color2);
        Drawable[] d = {bottom, front};
        LayerDrawable drawable = new LayerDrawable(d);
        drawable.setLayerInset(1, 0, 0, 0, 3);
        return drawable;
    }

    public static void setDefaultTheme(int theme) {
        DEFAULT_THEME = theme;
    }
}