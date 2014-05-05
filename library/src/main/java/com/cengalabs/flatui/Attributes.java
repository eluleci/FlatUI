package com.cengalabs.flatui;

import android.content.res.Resources;

/**
 * Created by eluleci on 05/05/14.
 */
public class Attributes {

    public static int INVALID = -1;

    public static int DEFAULT_THEME = R.array.blood;

    public static final String DEFAULT_FONT_FAMILY = "roboto";
    public static final String DEFAULT_FONT_WEIGHT = "light";
    public static final String DEFAULT_FONT_EXTENSION = "ttf";
    public static final int DEFAULT_TEXT_APPEARANCE = 0;

    public static final int DEFAULT_RADIUS = 10;
    public static final int DEFAULT_BORDER_WIDTH = 5;
    public static final int DEFAULT_SIZE = 20;

    /**
     * Color related fields
     */
    private int[] colors;
    private int theme = -1;

    /**
     * Font related fields
     */
    private String fontFamily = DEFAULT_FONT_FAMILY;
    private String fontWeight = DEFAULT_FONT_WEIGHT;
    private String fontExtension = DEFAULT_FONT_EXTENSION;
    private int textAppearance = DEFAULT_TEXT_APPEARANCE;

    /**
     * Size related fields
     */
    private int radius = DEFAULT_RADIUS;
    private int size = DEFAULT_SIZE;
    private int borderWidth = DEFAULT_BORDER_WIDTH;

    /**
     * Attribute change listener. Used to redraw the view when attributes are changed.
     */
    private AttributeChangeListener attributeChangeListener;

    public Attributes(AttributeChangeListener attributeChangeListener) {
        this.attributeChangeListener = attributeChangeListener;
    }

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme, Resources resources) {
        this.theme = theme;
        colors = resources.getIntArray(theme);
        attributeChangeListener.onThemeChange();
    }

    public void setThemeSilent(int customTheme, Resources resources) {
        this.theme = customTheme;
        colors = resources.getIntArray(customTheme);
    }

    public void setColors(int[] colors) {
        this.colors = colors;
        attributeChangeListener.onThemeChange();
    }

    public int getColor(int colorPos) {
        return colors[colorPos];
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        if (fontFamily != null && !fontFamily.equals("") && !fontFamily.equals("null"))
            this.fontFamily = fontFamily;
    }

    public String getFontWeight() {
        return fontWeight;
    }

    public void setFontWeight(String fontWeight) {
        if (fontWeight != null && !fontWeight.equals("") && !fontWeight.equals("null"))
            this.fontWeight = fontWeight;
    }

    public String getFontExtension() {
        return fontExtension;
    }

    public void setFontExtension(String fontExtension) {
        if (fontExtension != null && !fontExtension.equals("") && !fontExtension.equals("null"))
            this.fontExtension = fontExtension;
    }

    public int getRadius() {
        return radius;
    }

    public float[] getOuterRadius() {
        return new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public int getTextAppearance() {
        return textAppearance;
    }

    public void setTextAppearance(int textAppearance) {
        this.textAppearance = textAppearance;
    }

    public interface AttributeChangeListener {
        public void onThemeChange();
    }

}
