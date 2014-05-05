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
import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.R;
import com.cengalabs.flatui.constants.Colors;

/**
 * Created with IntelliJ IDEA.
 * User: eluleci
 * Date: 23.10.2013
 * Time: 22:18
 */
public class FlatToggleButton extends ToggleButton implements Colors, Attributes.AttributeChangeListener {

    private Attributes attributes;

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
            attributes = new Attributes(this);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.FlatToggleButton);

            // getting common attributes
            attributes.setThemeSilent(a.getInt(R.styleable.FlatToggleButton_theme, FlatUI.DEFAULT_THEME));

            int customTheme = a.getResourceId(R.styleable.FlatToggleButton_customTheme, FlatUI.INVALID_ATTRIBUTE);
            if (customTheme != FlatUI.INVALID_ATTRIBUTE) attributes.setCustomThemeSilent(customTheme, getResources());

            attributes.setSize(a.getDimensionPixelSize(R.styleable.FlatToggleButton_size, FlatUI.DEFAULT_SIZE));
            attributes.setRadius(a.getDimensionPixelSize(R.styleable.FlatToggleButton_cornerRadius, FlatUI.DEFAULT_RADIUS));
            padding = attributes.getSize() / 10;

            a.recycle();
        }

        // creating unchecked-enabled state drawable
        ShapeDrawable uncheckedEnabledFrontCore = new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
        uncheckedEnabledFrontCore.getPaint().setColor(attributes.getColor(2));
        InsetDrawable uncheckedEnabledFront = new InsetDrawable(uncheckedEnabledFrontCore, padding);

        ShapeDrawable uncheckedEnabledBack = new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
        uncheckedEnabledBack.getPaint().setColor(Color.parseColor("#f2f2f2"));
        uncheckedEnabledBack.setIntrinsicWidth(attributes.getSize() / 2 * 5);
        uncheckedEnabledBack.setIntrinsicHeight(attributes.getSize());
        uncheckedEnabledBack.setPadding(0, 0, attributes.getSize() / 2 * 5, 0);

        Drawable[] d1 = {uncheckedEnabledBack, uncheckedEnabledFront};
        LayerDrawable uncheckedEnabled = new LayerDrawable(d1);

        // creating checked-enabled state drawable
        ShapeDrawable checkedEnabledFrontCore = new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
        checkedEnabledFrontCore.getPaint().setColor(attributes.getColor(2));
        InsetDrawable checkedEnabledFront = new InsetDrawable(checkedEnabledFrontCore, padding);

        ShapeDrawable checkedEnabledBack = new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
        checkedEnabledBack.getPaint().setColor(attributes.getColor(3));
        checkedEnabledBack.setPadding(attributes.getSize() / 2 * 5, 0, 0, 0);

        Drawable[] d2 = {checkedEnabledBack, checkedEnabledFront};
        LayerDrawable checkedEnabled = new LayerDrawable(d2);

        // creating unchecked-disabled state drawable
        ShapeDrawable uncheckedDisabledFrontCore = new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
        uncheckedDisabledFrontCore.getPaint().setColor(Color.parseColor("#d2d2d2"));
        InsetDrawable uncheckedDisabledFront = new InsetDrawable(uncheckedDisabledFrontCore, padding);

        ShapeDrawable uncheckedDisabledBack = new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
        uncheckedDisabledBack.getPaint().setColor(Color.parseColor("#f2f2f2"));
        uncheckedDisabledBack.setPadding(0, 0, attributes.getSize() / 2 * 5, 0);

        Drawable[] d3 = {uncheckedDisabledBack, uncheckedDisabledFront};
        LayerDrawable uncheckedDisabled = new LayerDrawable(d3);

        // creating checked-disabled state drawable
        ShapeDrawable checkedDisabledFrontCore = new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
        checkedDisabledFrontCore.getPaint().setColor(attributes.getColor(3));
        InsetDrawable checkedDisabledFront = new InsetDrawable(checkedDisabledFrontCore, padding);

        ShapeDrawable checkedDisabledBack = new ShapeDrawable(new RoundRectShape(attributes.getOuterRadius(), null, null));
        checkedDisabledBack.getPaint().setColor(Color.parseColor("#f2f2f2"));
        checkedDisabledBack.setPadding(attributes.getSize() / 2 * 5, 0, 0, 0);

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