package com.cengalabs.flatui.views;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.SeekBar;

import com.cengalabs.flatui.Attributes;

/**
 * User: eluleci
 * Date: 24.10.2013
 * Time: 23:03
 */
public class FlatSeekBar extends SeekBar implements Attributes.AttributeChangeListener {

    private Attributes attributes;

    public FlatSeekBar(Context context) {
        super(context);
        init(null);
    }

    public FlatSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public FlatSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        if (attributes == null)
            attributes = new Attributes(this, getResources());

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, com.cengalabs.flatui.R.styleable.fl_FlatSeekBar);

            // getting common attributes
            int customTheme = a.getResourceId(com.cengalabs.flatui.R.styleable.fl_FlatSeekBar_fl_theme, Attributes.DEFAULT_THEME);
            attributes.setThemeSilent(customTheme, getResources());

            attributes.setSize(a.getDimensionPixelSize(com.cengalabs.flatui.R.styleable.fl_FlatSeekBar_fl_size, Attributes.DEFAULT_SIZE_PX));

            a.recycle();
        }

        // setting thumb
        PaintDrawable thumb = new PaintDrawable(attributes.getColor(0));
        thumb.setCornerRadius(attributes.getSize() * 9 / 8);
        thumb.setIntrinsicWidth(attributes.getSize() * 9 / 4);
        thumb.setIntrinsicHeight(attributes.getSize() * 9 / 4);
        setThumb(thumb);

        // progress
        PaintDrawable progress = new PaintDrawable(attributes.getColor(1));
        progress.setCornerRadius(attributes.getSize());
        progress.setIntrinsicHeight(attributes.getSize());
        progress.setIntrinsicWidth(attributes.getSize());
        progress.setDither(true);
        ClipDrawable progressClip = new ClipDrawable(progress, Gravity.LEFT, ClipDrawable.HORIZONTAL);

        // secondary progress
        PaintDrawable secondary = new PaintDrawable(attributes.getColor(2));
        secondary.setCornerRadius(attributes.getSize());
        secondary.setIntrinsicHeight(attributes.getSize());
        ClipDrawable secondaryProgressClip = new ClipDrawable(secondary, Gravity.LEFT, ClipDrawable.HORIZONTAL);

        // background
        PaintDrawable background = new PaintDrawable(attributes.getColor(3));
        background.setCornerRadius(attributes.getSize());
        background.setIntrinsicHeight(attributes.getSize());

        // applying drawable
        LayerDrawable ld = (LayerDrawable) getProgressDrawable();
        ld.setDrawableByLayerId(R.id.background, background);
        ld.setDrawableByLayerId(R.id.progress, progressClip);
        ld.setDrawableByLayerId(R.id.secondaryProgress, secondaryProgressClip);
    }

    public Attributes getAttributes() {
        return attributes;
    }

    @Override
    public void onThemeChange() {
        init(null);
    }
}
