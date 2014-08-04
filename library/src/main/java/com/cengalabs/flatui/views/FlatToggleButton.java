package com.cengalabs.flatui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.widget.ToggleButton;

import com.cengalabs.flatui.Attributes;
import com.cengalabs.flatui.R;

/**
 * User: eluleci
 * Date: 23.10.2013
 * Time: 22:18
 */
public class FlatToggleButton extends ToggleButton implements Attributes.AttributeChangeListener {

    private Attributes attributes;

    private int space = 14;
    private int padding;

    public FlatToggleButton(Context context) {
        super(context);
        init(null);
    }

    public FlatToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FlatToggleButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        if (attributes == null)
            attributes = new Attributes(this, getResources());

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.fl_FlatToggleButton);

            // getting common attributes
            int customTheme = a.getResourceId(R.styleable.fl_FlatToggleButton_fl_theme, Attributes.DEFAULT_THEME);
            attributes.setThemeSilent(customTheme, getResources());

            attributes.setRadius(a.getDimensionPixelSize(R.styleable.fl_FlatToggleButton_fl_cornerRadius, Attributes.DEFAULT_RADIUS_PX));

            space = a.getDimensionPixelSize(R.styleable.fl_FlatToggleButton_fl_space, space);
            padding = space / 10;

            a.recycle();
        }

        // creating unchecked-enabled state drawable
        ShapeDrawable uncheckedEnabledFrontCore = new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
        uncheckedEnabledFrontCore.getPaint().setColor(attributes.getColor(2));
        InsetDrawable uncheckedEnabledFront = new InsetDrawable(uncheckedEnabledFrontCore, padding);

        ShapeDrawable uncheckedEnabledBack = new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
        uncheckedEnabledBack.getPaint().setColor(Color.parseColor("#f2f2f2"));
        uncheckedEnabledBack.setIntrinsicWidth(space / 2 * 5);
        uncheckedEnabledBack.setIntrinsicHeight(space);
        uncheckedEnabledBack.setPadding(0, 0, space / 2 * 5, 0);

        Drawable[] d1 = {uncheckedEnabledBack, uncheckedEnabledFront};
        LayerDrawable uncheckedEnabled = new LayerDrawable(d1);

        // creating checked-enabled state drawable
        ShapeDrawable checkedEnabledFrontCore = new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
        checkedEnabledFrontCore.getPaint().setColor(attributes.getColor(2));
        InsetDrawable checkedEnabledFront = new InsetDrawable(checkedEnabledFrontCore, padding);

        ShapeDrawable checkedEnabledBack = new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
        checkedEnabledBack.getPaint().setColor(attributes.getColor(3));
        checkedEnabledBack.setPadding(space / 2 * 5, 0, 0, 0);

        Drawable[] d2 = {checkedEnabledBack, checkedEnabledFront};
        LayerDrawable checkedEnabled = new LayerDrawable(d2);

        // creating unchecked-disabled state drawable
        ShapeDrawable uncheckedDisabledFrontCore = new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
        uncheckedDisabledFrontCore.getPaint().setColor(Color.parseColor("#d2d2d2"));
        InsetDrawable uncheckedDisabledFront = new InsetDrawable(uncheckedDisabledFrontCore, padding);

        ShapeDrawable uncheckedDisabledBack = new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
        uncheckedDisabledBack.getPaint().setColor(Color.parseColor("#f2f2f2"));
        uncheckedDisabledBack.setPadding(0, 0, space / 2 * 5, 0);

        Drawable[] d3 = {uncheckedDisabledBack, uncheckedDisabledFront};
        LayerDrawable uncheckedDisabled = new LayerDrawable(d3);

        // creating checked-disabled state drawable
        ShapeDrawable checkedDisabledFrontCore = new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
        checkedDisabledFrontCore.getPaint().setColor(attributes.getColor(3));
        InsetDrawable checkedDisabledFront = new InsetDrawable(checkedDisabledFrontCore, padding);

        ShapeDrawable checkedDisabledBack = new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
        checkedDisabledBack.getPaint().setColor(Color.parseColor("#f2f2f2"));
        checkedDisabledBack.setPadding(space / 2 * 5, 0, 0, 0);

        Drawable[] d4 = {checkedDisabledBack, checkedDisabledFront};
        LayerDrawable checkedDisabled = new LayerDrawable(d4);

        StateListDrawable states = new StateListDrawable();

        states.addState(new int[]{-android.R.attr.state_checked, android.R.attr.state_enabled},
                new InsetDrawable(uncheckedEnabled, padding * 2));
        states.addState(new int[]{android.R.attr.state_checked, android.R.attr.state_enabled},
                new InsetDrawable(checkedEnabled, padding * 2));
        states.addState(new int[]{-android.R.attr.state_checked, -android.R.attr.state_enabled},
                new InsetDrawable(uncheckedDisabled, padding * 2));
        states.addState(new int[]{android.R.attr.state_checked, -android.R.attr.state_enabled},
                new InsetDrawable(checkedDisabled, padding * 2));

        setBackgroundDrawable(states);

        setText("");
        setTextOff("");
        setTextOn("");

        setTextSize(0);
    }

    public Attributes getAttributes() {
        return attributes;
    }

    @Override
    public void onThemeChange() {
        init(null);
    }
}