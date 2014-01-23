package com.cengalabs.flatui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.R;
import com.cengalabs.flatui.constants.Colors;

/**
 * Created with IntelliJ IDEA.
 * User: eluleci
 * Date: 23.10.2013
 * Time: 22:18
 */
public class FlatButton extends android.widget.Button implements Colors {

    private int fontId = FlatUI.DEFAULT_FONT_FAMILY;
    private int fontWeight = 2;
    private int[] color;
    private int theme;
    private int radius = 5;
    private int bottom = 5;
    private int padding = 10;
    private int textAppearance = 0;
    private boolean isFullFlat = false;

    public FlatButton(Context context) {
        super(context);
        init(null);
    }

    public FlatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FlatButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public int getTheme() {
        return theme;
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

            textAppearance = a.getInt(R.styleable.CengaLabs_textAppearance, textAppearance);
            padding = a.getDimensionPixelSize(R.styleable.CengaLabs_textPadding, padding);
            radius = a.getDimensionPixelSize(R.styleable.CengaLabs_cornerRadius, radius);
            isFullFlat = a.getBoolean(R.styleable.CengaLabs_isFullFlat, isFullFlat);

            fontId = a.getInt(R.styleable.CengaLabs_fontFamily, fontId);
            fontWeight = a.getInt(R.styleable.CengaLabs_fontWeight, fontWeight);

            a.recycle();
        } else if (color == null) {
            color = FlatUI.getColor(FlatUI.DEFAULT_THEME);
        }

        float[] outerR = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};

        // creating normal state drawable
        ShapeDrawable normalFront = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        normalFront.getPaint().setColor(color[2]);
        normalFront.setPadding(padding, padding, padding, padding);

        ShapeDrawable normalBack = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        normalBack.getPaint().setColor(color[1]);

        if (isFullFlat) bottom = 0;
        normalBack.setPadding(0, 0, 0, bottom);

        Drawable[] d = {normalBack, normalFront};
        LayerDrawable normal = new LayerDrawable(d);

        // creating pressed state drawable
        ShapeDrawable pressedFront = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        pressedFront.getPaint().setColor(color[1]);

        ShapeDrawable pressedBack = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        pressedBack.getPaint().setColor(color[0]);
        if (!isFullFlat) pressedBack.setPadding(0, 0, 0, 3);

        Drawable[] d2 = {pressedBack, pressedFront};
        LayerDrawable pressed = new LayerDrawable(d2);

        // creating disabled state drawable
        ShapeDrawable disabledFront = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        disabledFront.getPaint().setColor(color[3]);

        ShapeDrawable disabledBack = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        disabledBack.getPaint().setColor(color[2]);
        if (!isFullFlat) disabledBack.setPadding(0, 0, 0, padding);

        Drawable[] d3 = {disabledBack, disabledFront};
        LayerDrawable disabled = new LayerDrawable(d3);

        StateListDrawable states = new StateListDrawable();

        states.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, pressed);
        states.addState(new int[]{android.R.attr.state_focused, android.R.attr.state_enabled}, pressed);
        states.addState(new int[]{android.R.attr.state_enabled}, normal);
        states.addState(new int[]{-android.R.attr.state_enabled}, disabled);

        setBackgroundDrawable(states);

        if (textAppearance == 1) setTextColor(color[0]);
        else if (textAppearance == 2) setTextColor(color[3]);
        else setTextColor(Color.WHITE);

        Typeface typeface = FlatUI.getFont(getContext(), fontId, fontWeight);
        if (typeface != null) setTypeface(typeface);
    }
}
