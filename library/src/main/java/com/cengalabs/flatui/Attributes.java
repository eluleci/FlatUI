package com.cengalabs.flatui;

import android.content.res.Resources;
import android.content.res.TypedArray;

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
    private int fontWeight = FlatUI.DEFAULT_FONT_WEIGHT;
    private int textAppearance = FlatUI.DEFAULT_TEXT_APPEARANCE;

    /**
     * Size related fields
     */
    private int radius = FlatUI.DEFAULT_RADIUS;
    private int borderWidth = FlatUI.DEFAULT_BORDER_WIDTH;
    private int size = FlatUI.DEFAULT_SIZE;

    private AttributeChangeListener attributeChangeListener;

    public Attributes(AttributeChangeListener attributeChangeListener) {
        this.attributeChangeListener = attributeChangeListener;
    }

    public void parseAttributes(TypedArray a, Resources resources) {
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
