package com.cengalabs.flatui.views;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.constants.Colors;

/**
 * Created with IntelliJ IDEA.
 * User: eluleci
 * Date: 24.10.2013
 * Time: 23:03
 */
public class FlatSeekBar extends android.widget.SeekBar implements Colors {

    private int size = FlatUI.DEFAULT_SIZE;
    private int theme;
    private int[] color;

    public FlatSeekBar(Context context) {
        super(context);
        init(null, true);
    }

    public FlatSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, true);
    }

    public FlatSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, true);
    }

    public void setTheme(int theme) {
        color = FlatUI.getColor(theme);
        init(null, false);
    }

    private void init(AttributeSet attrs, boolean applyAttributeTheme) {

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, com.cengalabs.flatui.R.styleable.CengaLabs);

            theme = a.getInt(com.cengalabs.flatui.R.styleable.CengaLabs_theme, FlatUI.DEFAULT_THEME);
            color = FlatUI.getColor(theme);

            size = a.getDimensionPixelSize(com.cengalabs.flatui.R.styleable.CengaLabs_size, size);

            a.recycle();
        } else if (color == null) {
            color = FlatUI.getColor(FlatUI.DEFAULT_THEME);
        }

        // setting thumb
        PaintDrawable thumb = new PaintDrawable(color[0]);
        thumb.setCornerRadius(size * 9 / 8);
        thumb.setIntrinsicWidth(size * 9 / 4);
        thumb.setIntrinsicHeight(size * 9 / 4);
        setThumb(thumb);

        // progress
        PaintDrawable progress = new PaintDrawable(color[1]);
        progress.setCornerRadius(size);
        progress.setIntrinsicHeight(size);
        progress.setIntrinsicWidth(size);
        progress.setDither(true);
        ClipDrawable progressClip = new ClipDrawable(progress, Gravity.LEFT, ClipDrawable.HORIZONTAL);

        // secondary progress
        PaintDrawable secondary = new PaintDrawable(color[2]);
        secondary.setCornerRadius(size);
        secondary.setIntrinsicHeight(size);
        ClipDrawable secondaryProgressClip = new ClipDrawable(secondary, Gravity.LEFT, ClipDrawable.HORIZONTAL);

        // background
        PaintDrawable background = new PaintDrawable(color[3]);
        background.setCornerRadius(size);
        background.setIntrinsicHeight(size);

        // applying drawable
        LayerDrawable ld = (LayerDrawable) getProgressDrawable();
        ld.setDrawableByLayerId(R.id.background, background);
        ld.setDrawableByLayerId(R.id.progress, progressClip);
        ld.setDrawableByLayerId(R.id.secondaryProgress, secondaryProgressClip);
    }
}
