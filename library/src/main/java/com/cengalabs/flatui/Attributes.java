package com.cengalabs.flatui;

import android.content.res.Resources;

/**
 * Created by eluleci on 05/05/14.
 */
public class Attributes {

    /**
     * Color related fields
     */
    private int[] colors;
    private int theme = -1;
    private int customTheme = -1;

    /**
     * Font related fields
     */
    private int fontId = FlatUI.DEFAULT_FONT_FAMILY;
    private int fontWeight = 2;

    /**
     * Size related fields
     */

    private AttributeChangeListener attributeChangeListener;

    public Attributes(AttributeChangeListener attributeChangeListener) {
        this.attributeChangeListener = attributeChangeListener;
    }

    private void initColors(int theme) {
        colors = FlatUI.getColor(theme);
        if (colors == null) colors = FlatUI.getColor(FlatUI.DEFAULT_THEME);
    }

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
        this.customTheme = FlatUI.INVALID_ATTRIBUTE;
        initColors(theme);
        attributeChangeListener.onThemeChange();
    }

    public void setThemeSilent(int theme) {
        this.theme = theme;
        this.customTheme = FlatUI.INVALID_ATTRIBUTE;
        initColors(theme);
    }

    public int getCustomTheme() {
        return customTheme;
    }

    public void setCustomTheme(int customTheme, Resources resources) {
        this.customTheme = customTheme;
        this.theme = FlatUI.CUSTOM_THEME;
        colors = resources.getIntArray(customTheme);
        attributeChangeListener.onThemeChange();
    }

    public void setCustomThemeSilent(int customTheme, Resources resources) {
        this.customTheme = customTheme;
        this.theme = FlatUI.CUSTOM_THEME;
        colors = resources.getIntArray(customTheme);
    }

    public void setColors(int[] colors) {
        this.colors = colors;
        attributeChangeListener.onThemeChange();
    }

    public int getColor(int colorPos) {
        return colors[colorPos];
    }

    public int getFontId() {
        return fontId;
    }

    public void setFontId(int fontId) {
        this.fontId = fontId;
    }

    public void setFontWeight(int fontWeight) {
        this.fontWeight = fontWeight;
    }

    public int getFontWeight() {
        return fontWeight;
    }

    public interface AttributeChangeListener {
        public void onThemeChange();
    }

}
