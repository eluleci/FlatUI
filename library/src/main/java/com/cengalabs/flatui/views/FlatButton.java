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
import android.widget.Button;

import com.cengalabs.flatui.Attributes;
import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.R;
import com.cengalabs.flatui.constants.Colors;

/**
 * Created with IntelliJ IDEA.
 * User: eluleci
 * Date: 23.10.2013
 * Time: 22:18
 */
public class FlatButton extends Button implements Colors, Attributes.AttributeChangeListener {


    private Attributes attributes;

    private int bottom = 5;
    //private int padding = 10;
    private boolean isFullFlat = false;
    private int textAppearance = 0;
    private int radius = FlatUI.DEFAULT_RADIUS;

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

    private void init(AttributeSet attrs) {

        if (attributes == null)
            attributes = new Attributes(this);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.FlatButton);

            attributes.setThemeSilent(a.getInt(R.styleable.FlatButton_theme, FlatUI.DEFAULT_THEME));

            int customTheme = a.getResourceId(R.styleable.FlatButton_customTheme, FlatUI.INVALID_ATTRIBUTE);
            if (customTheme != FlatUI.INVALID_ATTRIBUTE)
                attributes.setCustomThemeSilent(customTheme, getResources());

            attributes.setFontId(a.getInt(R.styleable.FlatButton_fontFamily, FlatUI.DEFAULT_FONT_FAMILY));
            attributes.setFontWeight(a.getInt(R.styleable.FlatButton_fontWeight, FlatUI.DEFAULT_FONT_WEIGHT));

            textAppearance = a.getInt(R.styleable.FlatButton_textAppearance, textAppearance);
            //padding = a.getDimensionPixelSize(R.styleable.CengaLabs_textPadding, padding);
            radius = a.getDimensionPixelSize(R.styleable.FlatButton_cornerRadius, radius);
            isFullFlat = a.getBoolean(R.styleable.FlatButton_isFullFlat, isFullFlat);

            a.recycle();
        }

        float[] outerR = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};

        // creating normal state drawable
        ShapeDrawable normalFront = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        normalFront.getPaint().setColor(attributes.getColor(2));
        //normalFront.setPadding(padding, padding, padding, padding);

        ShapeDrawable normalBack = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        normalBack.getPaint().setColor(attributes.getColor(1));

        if (isFullFlat) bottom = 0;
        normalBack.setPadding(0, 0, 0, bottom);

        Drawable[] d = {normalBack, normalFront};
        LayerDrawable normal = new LayerDrawable(d);

        // creating pressed state drawable
        ShapeDrawable pressedFront = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        pressedFront.getPaint().setColor(attributes.getColor(2));

        ShapeDrawable pressedBack = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        pressedBack.getPaint().setColor(attributes.getColor(0));
        if (!isFullFlat) pressedBack.setPadding(0, 0, 0, 3);

        Drawable[] d2 = {pressedBack, pressedFront};
        LayerDrawable pressed = new LayerDrawable(d2);

        // creating disabled state drawable
        ShapeDrawable disabledFront = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        disabledFront.getPaint().setColor(attributes.getColor(3));

        ShapeDrawable disabledBack = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        disabledBack.getPaint().setColor(attributes.getColor(2));
        //if (!isFullFlat) disabledBack.setPadding(0, 0, 0, padding);

        Drawable[] d3 = {disabledBack, disabledFront};
        LayerDrawable disabled = new LayerDrawable(d3);

        StateListDrawable states = new StateListDrawable();

        states.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, pressed);
        states.addState(new int[]{android.R.attr.state_focused, android.R.attr.state_enabled}, pressed);
        states.addState(new int[]{android.R.attr.state_enabled}, normal);
        states.addState(new int[]{-android.R.attr.state_enabled}, disabled);

        setBackgroundDrawable(states);

        if (textAppearance == 1) setTextColor(attributes.getColor(0));
        else if (textAppearance == 2) setTextColor(attributes.getColor(3));
        else setTextColor(Color.WHITE);

        Typeface typeface = FlatUI.getFont(getContext(), attributes.getFontId(), attributes.getFontWeight());
        if (typeface != null) setTypeface(typeface);
    }

    public Attributes getAttributes() {
        return attributes;
    }

    @Override
    public void onThemeChange() {
        init(null);
    }
}
