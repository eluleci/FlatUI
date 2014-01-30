package com.cengalabs.flatui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.widget.EditText;
import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.R;
import com.cengalabs.flatui.constants.Colors;

/**
 * Created with IntelliJ IDEA.
 * User: eluleci
 * Date: 24.10.2013
 * Time: 21:09
 */
public class FlatEditText extends EditText implements Colors {

    private int fontId = FlatUI.DEFAULT_FONT_FAMILY;
    private int fontWeight = FlatUI.DEFAULT_FONT_WEIGHT;
    private int[] color;
    private int radius = 5;
    private int padding = 10;
    private int border = 3;
    private int style = 0;
    private int textAppearance = 0;

    public FlatEditText(Context context) {
        super(context);
        init(null);
    }

    public FlatEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FlatEditText(Context context, AttributeSet attrs, int defStyle) {
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

            style = a.getInt(R.styleable.CengaLabs_fieldStyle, 0);
            radius = a.getDimensionPixelSize(R.styleable.CengaLabs_cornerRadius, radius);
            padding = a.getDimensionPixelSize(R.styleable.CengaLabs_textPadding, padding);

            fontId = a.getInt(R.styleable.CengaLabs_fontFamily, fontId);
            fontWeight = a.getInt(R.styleable.CengaLabs_fontWeight, fontWeight);

            textAppearance = a.getInt(R.styleable.CengaLabs_textAppearance, textAppearance);

            a.recycle();
        } else if (color == null) {
            color = FlatUI.getColor(FlatUI.DEFAULT_THEME);
        }

        float[] outerR = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};

        // creating normal state drawable
        ShapeDrawable normalFront = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        normalFront.setPadding(padding, padding, padding, padding);

        ShapeDrawable normalBack = new ShapeDrawable(new RoundRectShape(outerR, null, null));
        normalBack.setPadding(border, border, border, border);

        if (style == 0) {             // flat
            normalFront.getPaint().setColor(Color.TRANSPARENT);
            normalBack.getPaint().setColor(color[2]);
            setTextColor(color[3]);

        } else if (style == 1) {      // box
            normalFront.getPaint().setColor(Color.WHITE);
            normalBack.getPaint().setColor(color[2]);
            setTextColor(color[2]);

        } else if (style == 2) {      // transparent
            normalFront.getPaint().setColor(Color.TRANSPARENT);
            normalBack.getPaint().setColor(Color.TRANSPARENT);
            setTextColor(color[1]);
        }

        Drawable[] d = {normalBack, normalFront};
        LayerDrawable normal = new LayerDrawable(d);

        setBackgroundDrawable(normal);

        setHintTextColor(color[3]);

        if (textAppearance == 1) setTextColor(color[0]);
        else if (textAppearance == 2) setTextColor(color[3]);

        Typeface typeface = FlatUI.getFont(getContext(), fontId, fontWeight);
        if (typeface != null) setTypeface(typeface);
    }
}
