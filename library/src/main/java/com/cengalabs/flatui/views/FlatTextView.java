package com.cengalabs.flatui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.cengalabs.flatui.Attributes;
import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.R;
import com.cengalabs.flatui.constants.Colors;

/**
 * Created with IntelliJ IDEA.
 * User: eluleci
 * Date: 24.10.2013
 * Time: 21:09
 */
public class FlatTextView extends TextView implements Colors, Attributes.AttributeChangeListener {

    private Attributes attributes;

    private int textColor = 2;
    private int backgroundColor = -1;
    private int customBackgroundColor = -1;

    private boolean hasOwnTextColor;

    public FlatTextView(Context context) {
        super(context);
        init(null);
    }

    public FlatTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FlatTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        if (attributes == null)
            attributes = new Attributes(this);

        if (attrs != null) {

            // getting android default tags for textColor and textColorHint
            hasOwnTextColor = attrs.getAttributeValue(FlatUI.androidStyleNameSpace, "textColor") != null;

            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.FlatTextView);

            // getting common attributes
            attributes.setThemeSilent(a.getInt(R.styleable.FlatTextView_theme, FlatUI.DEFAULT_THEME));

            int customTheme = a.getResourceId(R.styleable.FlatTextView_customTheme, FlatUI.INVALID_ATTRIBUTE);
            if (customTheme != FlatUI.INVALID_ATTRIBUTE) attributes.setCustomThemeSilent(customTheme, getResources());

            attributes.setFontId(a.getInt(R.styleable.FlatTextView_fontFamily, FlatUI.DEFAULT_FONT_FAMILY));
            attributes.setFontWeight(a.getInt(R.styleable.FlatTextView_fontWeight, FlatUI.DEFAULT_FONT_WEIGHT));

            attributes.setRadius(attributes.getSize() / 2);
            attributes.setBorderWidth(a.getDimensionPixelSize(R.styleable.FlatTextView_borderWidth, 0));

            // getting view specific attributes
            textColor = a.getInt(R.styleable.FlatTextView_textColor, textColor);
            backgroundColor = a.getInt(R.styleable.FlatTextView_backgroundColor, backgroundColor);
            customBackgroundColor = a.getInt(R.styleable.FlatTextView_customBackgroundColor, customBackgroundColor);

            a.recycle();
        }

        if (backgroundColor != -1) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(attributes.getColor(backgroundColor));
            gradientDrawable.setCornerRadius(attributes.getRadius());
            gradientDrawable.setStroke(attributes.getBorderWidth(), attributes.getColor(textColor));
            setBackgroundDrawable(gradientDrawable);
        } else if (customBackgroundColor != -1) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(customBackgroundColor);
            gradientDrawable.setCornerRadius(attributes.getRadius());
            gradientDrawable.setStroke(attributes.getBorderWidth(), attributes.getColor(textColor));
            setBackgroundDrawable(gradientDrawable);
        }

        // setting the text color only if there is no android:textColor attribute used
        if (!hasOwnTextColor) setTextColor(attributes.getColor(textColor));

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
