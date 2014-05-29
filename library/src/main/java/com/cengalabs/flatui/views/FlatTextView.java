package com.cengalabs.flatui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.cengalabs.flatui.Attributes;
import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.R;

/**
 * User: eluleci
 * Date: 24.10.2013
 * Time: 21:09
 */
public class FlatTextView extends TextView implements Attributes.AttributeChangeListener {

    private Attributes attributes;

    private int textColor = 2;
    private int backgroundColor = Attributes.INVALID;
    private int customBackgroundColor = Attributes.INVALID;

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
            int customTheme = a.getResourceId(R.styleable.FlatTextView_theme, Attributes.DEFAULT_THEME);
            attributes.setThemeSilent(customTheme, getResources());

            attributes.setFontFamily(a.getString(R.styleable.FlatTextView_fontFamily));
            attributes.setFontWeight(a.getString(R.styleable.FlatTextView_fontWeight));
            attributes.setFontExtension(a.getString(R.styleable.FlatTextView_fontExtension));

            attributes.setRadius(a.getDimensionPixelSize(R.styleable.FlatTextView_cornerRadius, Attributes.DEFAULT_RADIUS));
            attributes.setBorderWidth(a.getDimensionPixelSize(R.styleable.FlatTextView_borderWidth, 0));

            // getting view specific attributes
            textColor = a.getInt(R.styleable.FlatTextView_textColor, textColor);
            backgroundColor = a.getInt(R.styleable.FlatTextView_backgroundColor, backgroundColor);
            customBackgroundColor = a.getInt(R.styleable.FlatTextView_customBackgroundColor, customBackgroundColor);

            a.recycle();
        }

        GradientDrawable gradientDrawable = new GradientDrawable();
        if (backgroundColor != Attributes.INVALID) {
            gradientDrawable.setColor(attributes.getColor(backgroundColor));
        } else if (customBackgroundColor != Attributes.INVALID) {
            gradientDrawable.setColor(customBackgroundColor);
        } else {
            gradientDrawable.setColor(Color.TRANSPARENT);
        }
        gradientDrawable.setCornerRadius(attributes.getRadius());
        gradientDrawable.setStroke(attributes.getBorderWidth(), attributes.getColor(textColor));
        setBackgroundDrawable(gradientDrawable);

        // setting the text color only if there is no android:textColor attribute used
        if (!hasOwnTextColor) setTextColor(attributes.getColor(textColor));

        // check for IDE preview render
        if(!this.isInEditMode()) {
            Typeface typeface = FlatUI.getFont(getContext(), attributes);
            if (typeface != null) setTypeface(typeface);
        }
    }

    public Attributes getAttributes() {
        return attributes;
    }

    @Override
    public void onThemeChange() {
        init(null);
    }
}
