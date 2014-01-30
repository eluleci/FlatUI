package com.cengalabs.flatui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.cengalabs.flatui.views.FlatButton;
import com.cengalabs.flatui.views.FlatCheckBox;
import com.cengalabs.flatui.views.FlatEditText;
import com.cengalabs.flatui.views.FlatRadioButton;
import com.cengalabs.flatui.views.FlatSeekBar;
import com.cengalabs.flatui.views.FlatTextView;
import com.cengalabs.flatui.views.FlatToggleButton;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    ArrayList<FlatTextView> flatTextViews = new ArrayList<FlatTextView>();
    ArrayList<FlatEditText> flatEditTexts = new ArrayList<FlatEditText>();
    ArrayList<FlatButton> flatButtons = new ArrayList<FlatButton>();
    ArrayList<FlatCheckBox> flatCheckBoxes = new ArrayList<FlatCheckBox>();
    ArrayList<FlatRadioButton> flatRadioButtons = new ArrayList<FlatRadioButton>();
    ArrayList<FlatToggleButton> flatToggleButtons = new ArrayList<FlatToggleButton>();
    FlatRadioButton radioCheckedEnabled;
    FlatSeekBar flatSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FlatUI.setDefaultTheme(FlatUI.DEEP);

        // if you are using standard action bar (not compatibility library) use this
        // FlatUI.setActionBarTheme(this, theme, false, true);

        // if you are using ActionBar of Compatibility library (like this activity), get drawable
        // and set it manually to support action bar.
        getSupportActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(FlatUI.DEEP, false));

        // titles
        flatTextViews.add((FlatTextView) findViewById(R.id.title_edittexts));
        flatTextViews.add((FlatTextView) findViewById(R.id.title_seekbar));
        flatTextViews.add((FlatTextView) findViewById(R.id.title_buttons));
        flatTextViews.add((FlatTextView) findViewById(R.id.title_buttons_shape));
        flatTextViews.add((FlatTextView) findViewById(R.id.title_buttons_text_appearance));
        flatTextViews.add((FlatTextView) findViewById(R.id.title_checkbox));
        flatTextViews.add((FlatTextView) findViewById(R.id.title_checkbox_enabled));
        flatTextViews.add((FlatTextView) findViewById(R.id.title_checkbox_disabled));
        flatTextViews.add((FlatTextView) findViewById(R.id.title_radiobutton));
        flatTextViews.add((FlatTextView) findViewById(R.id.title_radiobutton_enabled));
        flatTextViews.add((FlatTextView) findViewById(R.id.title_radiobutton_disabled));
        flatTextViews.add((FlatTextView) findViewById(R.id.title_toggle_button));
        flatTextViews.add((FlatTextView) findViewById(R.id.title_toggle_enabled));
        flatTextViews.add((FlatTextView) findViewById(R.id.title_toggle_disabled));
        flatTextViews.add((FlatTextView) findViewById(R.id.title_themes));
        flatTextViews.add((FlatTextView) findViewById(R.id.title_themes_note));

        // edit texts
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_flat));
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_box));
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_transparent));

        // buttons
        flatButtons.add((FlatButton) findViewById(R.id.button_block));
        flatButtons.add((FlatButton) findViewById(R.id.button_flat));
        flatButtons.add((FlatButton) findViewById(R.id.button_light));
        flatButtons.add((FlatButton) findViewById(R.id.button_white));
        flatButtons.add((FlatButton) findViewById(R.id.button_dark_text));

        // check boxes
        flatCheckBoxes.add((FlatCheckBox) findViewById(R.id.checkbox_unchecked_enabled));
        flatCheckBoxes.add((FlatCheckBox) findViewById(R.id.checkbox_checked_enabled));
        flatCheckBoxes.add((FlatCheckBox) findViewById(R.id.checkbox_unchecked_disabled));
        flatCheckBoxes.add((FlatCheckBox) findViewById(R.id.checkbox_checked_disabled));

        // radio buttons
        flatRadioButtons.add((FlatRadioButton) findViewById(R.id.radio_unchecked_enabled));
        flatRadioButtons.add((FlatRadioButton) findViewById(R.id.radio_unchecked_disabled));
        flatRadioButtons.add((FlatRadioButton) findViewById(R.id.radio_checked_disabled));
        radioCheckedEnabled = (FlatRadioButton) findViewById(R.id.radio_checked_enabled);
        flatRadioButtons.add(radioCheckedEnabled);
        radioCheckedEnabled.setChecked(true);

        // toggle buttons
        flatToggleButtons.add((FlatToggleButton) findViewById(R.id.toggle_unchecked_enabled));
        flatToggleButtons.add((FlatToggleButton) findViewById(R.id.toggle_checked_enabled));
        flatToggleButtons.add((FlatToggleButton) findViewById(R.id.toggle_unchecked_disabled));
        flatToggleButtons.add((FlatToggleButton) findViewById(R.id.toggle_checked_disabled));

        flatSeekBar = (FlatSeekBar) findViewById(R.id.seekbar);
    }

    public void onChangeThemeButtonClicked(View v) {
        FlatButton button = (FlatButton) v;
        changeTheme(button.getTheme());
    }

    private void changeTheme(int theme) {


        for (FlatTextView view : flatTextViews) {
            view.setTheme(theme);
        }

        for (FlatEditText view : flatEditTexts) {
            view.setTheme(theme);
        }

        for (FlatButton view : flatButtons) {
            view.setTheme(theme);
        }

        for (FlatCheckBox view : flatCheckBoxes) {
            view.setTheme(theme);
        }

        for (FlatRadioButton view : flatRadioButtons) {
            view.setTheme(theme);
        }

        for (FlatToggleButton view : flatToggleButtons) {
            view.setTheme(theme);
        }

        flatSeekBar.setTheme(theme);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                flatSeekBar.setProgress(30);
                flatSeekBar.setSecondaryProgress(40);
            }
        });

        // if you are using standard action bar (not compatibility library) use this
        // FlatUI.setActionBarTheme(this, theme, false, true);

        // if you are using ActionBar of Compatibility library, get drawable and set it manually
        getSupportActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(theme, false));

        setTitle("FlatUI Sample App");
    }

}
