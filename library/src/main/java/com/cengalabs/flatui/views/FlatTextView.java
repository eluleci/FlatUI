package com.cengalabs.flatui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.R;
import com.cengalabs.flatui.constants.Colors;

/**
 * Created with IntelliJ IDEA.
 * User: eluleci
 * Date: 24.10.2013
 * Time: 21:09
 */
public class FlatTextView extends android.widget.TextView implements Colors {

    private int fontId = FlatUI.DEFAULT_FONT_FAMILY;
    private int weight = FlatUI.DEFAULT_FONT_WEIGHT;
    private int[] color;
    private int textColor = 2;
    private int backgroundColor = -1;
    private int customBackgroundColor = -1;
    private int cornerRadius = 5;

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

    public void setTheme(int theme) {
        color = FlatUI.getColor(theme);
        init(null);
    }

    private void init(AttributeSet attrs) {

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CengaLabs);

            int theme = a.getInt(R.styleable.CengaLabs_theme, FlatUI.DEFAULT_THEME);
            color = FlatUI.getColor(theme);

            fontId = a.getInt(R.styleable.CengaLabs_fontFamily, fontId);
            weight = a.getInt(R.styleable.CengaLabs_fontWeight, weight);
            textColor = a.getInt(R.styleable.CengaLabs_textColor, textColor);
            backgroundColor = a.getInt(R.styleable.CengaLabs_backgroundColor, backgroundColor);
            customBackgroundColor = a.getInt(R.styleable.CengaLabs_customBackgroundColor, customBackgroundColor);
            cornerRadius = a.getInt(R.styleable.CengaLabs_cornerRadius, cornerRadius);

            a.recycle();
        } else if (color == null) {
            color = FlatUI.getColor(FlatUI.DEFAULT_THEME);
        }

        Typeface typeface = FlatUI.getFont(getContext(), fontId, weight);
        if (typeface != null) setTypeface(typeface);

        if(backgroundColor != -1){
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(color[backgroundColor]);
            gradientDrawable.setCornerRadius(cornerRadius);
            setBackgroundDrawable(gradientDrawable);
        } else if(customBackgroundColor != -1) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(customBackgroundColor);
            gradientDrawable.setCornerRadius(cornerRadius);
            setBackgroundDrawable(gradientDrawable);
        }

        setTextColor(color[textColor]);
    }
}
