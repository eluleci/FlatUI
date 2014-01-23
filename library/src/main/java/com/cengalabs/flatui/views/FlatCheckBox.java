package com.cengalabs.flatui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.util.AttributeSet;
import android.widget.CheckBox;
import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.R;
import com.cengalabs.flatui.constants.Colors;

/**
 * Created with IntelliJ IDEA.
 * User: eluleci
 * Date: 23.10.2013
 * Time: 22:18
 */
public class FlatCheckBox extends CheckBox implements Colors {

    private int[] color;
    private int theme;
    private int size = 34;
    private int border = 5;
    private int radius = 3;
    private int fontId = FlatUI.DEFAULT_FONT_FAMILY;
    private int fontWeight = FlatUI.DEFAULT_FONT_WEIGHT;

    public FlatCheckBox(Context context) {
        super(context);
        init(null);
    }

    public FlatCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FlatCheckBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public void setTheme(int theme) {
        this.theme = theme;
        color = FlatUI.getColor(theme);
        init(null);
    }

    private void init(AttributeSet attrs) {

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CengaLabs);

            theme = a.getInt(R.styleable.CengaLabs_theme, FlatUI.DEFAULT_THEME);
            color = FlatUI.getColor(theme);
            radius = a.getDimensionPixelSize(R.styleable.CengaLabs_cornerRadius, radius);
            size = a.getDimensionPixelSize(R.styleable.CengaLabs_size, size);

            fontId = a.getInt(R.styleable.CengaLabs_fontFamily, fontId);
            fontWeight = a.getInt(R.styleable.CengaLabs_fontWeight, fontWeight);

            a.recycle();
        } else if (color == null) {
            color = FlatUI.getColor(FlatUI.DEFAULT_THEME);
        }

        // creating unchecked-enabled state drawable
        GradientDrawable uncheckedEnabled = new GradientDrawable();
        uncheckedEnabled.setCornerRadius(radius);
        uncheckedEnabled.setSize(size, size);
        uncheckedEnabled.setColor(Color.TRANSPARENT);
        uncheckedEnabled.setStroke(border, color[2]);

        // creating checked-enabled state drawable
        GradientDrawable checkedOutside = new GradientDrawable();
        checkedOutside.setCornerRadius(radius);
        checkedOutside.setSize(size, size);
        checkedOutside.setColor(Color.TRANSPARENT);
        checkedOutside.setStroke(border, color[2]);

        PaintDrawable checkedCore = new PaintDrawable(color[2]);
        checkedCore.setCornerRadius(radius);
        checkedCore.setIntrinsicHeight(size);
        checkedCore.setIntrinsicWidth(size);
        InsetDrawable checkedInside = new InsetDrawable(checkedCore, border + 2, border + 2, border + 2, border + 2);

        Drawable[] checkedEnabledDrawable = {checkedOutside, checkedInside};
        LayerDrawable checkedEnabled = new LayerDrawable(checkedEnabledDrawable);

        // creating unchecked-enabled state drawable
        GradientDrawable uncheckedDisabled = new GradientDrawable();
        uncheckedDisabled.setCornerRadius(radius);
        uncheckedDisabled.setSize(size, size);
        uncheckedDisabled.setColor(Color.TRANSPARENT);
        uncheckedDisabled.setStroke(border, color[3]);

        // creating checked-disabled state drawable
        GradientDrawable checkedOutsideDisabled = new GradientDrawable();
        checkedOutsideDisabled.setCornerRadius(radius);
        checkedOutsideDisabled.setSize(size, size);
        checkedOutsideDisabled.setColor(Color.TRANSPARENT);
        checkedOutsideDisabled.setStroke(border, color[3]);

        PaintDrawable checkedCoreDisabled = new PaintDrawable(color[3]);
        checkedCoreDisabled.setCornerRadius(radius);
        checkedCoreDisabled.setIntrinsicHeight(size);
        checkedCoreDisabled.setIntrinsicWidth(size);
        InsetDrawable checkedInsideDisabled = new InsetDrawable(checkedCoreDisabled, border + 2, border + 2, border + 2, border + 2);

        Drawable[] checkedDisabledDrawable = {checkedOutsideDisabled, checkedInsideDisabled};
        LayerDrawable checkedDisabled = new LayerDrawable(checkedDisabledDrawable);


        StateListDrawable states = new StateListDrawable();
        states.addState(new int[]{-android.R.attr.state_checked, android.R.attr.state_enabled}, uncheckedEnabled);
        states.addState(new int[]{android.R.attr.state_checked, android.R.attr.state_enabled}, checkedEnabled);
        states.addState(new int[]{-android.R.attr.state_checked, -android.R.attr.state_enabled}, uncheckedDisabled);
        states.addState(new int[]{android.R.attr.state_checked, -android.R.attr.state_enabled}, checkedDisabled);
        setButtonDrawable(states);

        // setting padding for avoiding text to be appear on icon
        setPadding(size / 4 * 5, 0, 0, 0);
        setTextColor(color[2]);

        Typeface typeface = FlatUI.getFont(getContext(), fontId, fontWeight);
        if (typeface != null) setTypeface(typeface);
    }
}
