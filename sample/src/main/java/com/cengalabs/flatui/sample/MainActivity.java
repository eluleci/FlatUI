package com.cengalabs.flatui.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.views.FlatButton;
import com.cengalabs.flatui.views.FlatCheckBox;
import com.cengalabs.flatui.views.FlatEditText;
import com.cengalabs.flatui.views.FlatRadioButton;
import com.cengalabs.flatui.views.FlatSeekBar;
import com.cengalabs.flatui.views.FlatTextView;
import com.cengalabs.flatui.views.FlatToggleButton;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    private final int APP_THEME = R.array.blood;

    private ArrayList<FlatTextView> flatTextViews = new ArrayList<FlatTextView>();
    private ArrayList<FlatEditText> flatEditTexts = new ArrayList<FlatEditText>();
    private ArrayList<FlatButton> flatButtons = new ArrayList<FlatButton>();
    private ArrayList<FlatCheckBox> flatCheckBoxes = new ArrayList<FlatCheckBox>();
    private ArrayList<FlatRadioButton> flatRadioButtons = new ArrayList<FlatRadioButton>();
    private ArrayList<FlatToggleButton> flatToggleButtons = new ArrayList<FlatToggleButton>();
    private FlatRadioButton radioCheckedEnabled;
    private FlatSeekBar flatSeekBar;

    private boolean isSpinnerSelectedBefore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // converts the default values to dp to be compatible with different screen sizes
        FlatUI.initDefaultValues(this);

        // Default theme should be set before content view is added
        FlatUI.setDefaultTheme(APP_THEME);

        setContentView(R.layout.activity_main);

        // Getting action bar background and applying it
        getSupportActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(this, APP_THEME, false, 2));

        // titles
        flatTextViews.add((FlatTextView) findViewById(R.id.title_edittexts));
        flatTextViews.add((FlatTextView) findViewById(R.id.title_seekbar));
        flatTextViews.add((FlatTextView) findViewById(R.id.title_buttons));
        flatTextViews.add((FlatTextView) findViewById(R.id.title_buttons_shape));
        flatTextViews.add((FlatTextView) findViewById(R.id.title_buttons_text_appearance));
        flatTextViews.add((FlatTextView) findViewById(R.id.title_buttons_touch_effect));
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
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_transparentbox));
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_transparent));

        // buttons
        flatButtons.add((FlatButton) findViewById(R.id.button_block));
        flatButtons.add((FlatButton) findViewById(R.id.button_flat));
        flatButtons.add((FlatButton) findViewById(R.id.button_light));
        flatButtons.add((FlatButton) findViewById(R.id.button_white));
        flatButtons.add((FlatButton) findViewById(R.id.button_dark_text));
        flatButtons.add((FlatButton) findViewById(R.id.button_ease));
        flatButtons.add((FlatButton) findViewById(R.id.button_ripple));

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
        flatSeekBar.setProgress(30);
        flatSeekBar.setSecondaryProgress(40);

        /**
         * This part is an example of spinner usage. You can change the theme of this spinner by
         * editing the layout files spinner_button and simple_flat_list_item.
         */
        Spinner spinner = (Spinner) findViewById(R.id.themes_spinner);

        // Create an ArrayAdapter using the string array and a custom spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.themes_array, R.layout.spinner_button);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.simple_flat_list_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // preventing the spinner to change the theme on start
                if (!isSpinnerSelectedBefore) {
                    isSpinnerSelectedBefore = true;
                    return;
                }

                int themeReference = APP_THEME;
                switch (position) {
                    case 0:
                        themeReference = FlatUI.SAND;
                        break;
                    case 1:
                        themeReference = FlatUI.ORANGE;
                        break;
                    case 2:
                        themeReference = FlatUI.CANDY;
                        break;
                    case 3:
                        themeReference = FlatUI.BLOSSOM;
                        break;
                    case 4:
                        themeReference = FlatUI.GRAPE;
                        break;
                    case 5:
                        themeReference = FlatUI.DEEP;
                        break;
                    case 6:
                        themeReference = FlatUI.SKY;
                        break;
                    case 7:
                        themeReference = FlatUI.GRASS;
                        break;
                    case 8:
                        themeReference = FlatUI.DARK;
                        break;
                    case 9:
                        themeReference = FlatUI.SNOW;
                        break;
                    case 10:
                        themeReference = FlatUI.SEA;
                        break;
                    case 11:
                        themeReference = FlatUI.BLOOD;
                        break;
                }
                changeTheme(themeReference);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /**
         * Autocomplete textview. You can change the EditText color via theme but
         * you need to set a layout for the rows as shown below. This is the same
         * row that is used in the spinner example.
         */
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);

        String[] themes = getResources().getStringArray(R.array.themes_array);
        ArrayAdapter acAdapter = new ArrayAdapter(this, R.layout.simple_flat_list_item, themes);
        actv.setAdapter(acAdapter);
    }

    public void onChangeThemeButtonClicked(View v) {
        FlatButton button = (FlatButton) v;
        changeTheme(button.getAttributes().getTheme());
    }

    private void changeTheme(int colorReference) {

        for (FlatTextView view : flatTextViews) {
            view.getAttributes().setTheme(colorReference, getResources());
        }

        for (FlatEditText view : flatEditTexts) {
            view.getAttributes().setTheme(colorReference, getResources());
        }

        for (FlatButton view : flatButtons) {
            view.getAttributes().setTheme(colorReference, getResources());
        }

        for (FlatCheckBox view : flatCheckBoxes) {
            view.getAttributes().setTheme(colorReference, getResources());
        }

        for (FlatRadioButton view : flatRadioButtons) {
            view.getAttributes().setTheme(colorReference, getResources());
        }

        for (FlatToggleButton view : flatToggleButtons) {
            view.getAttributes().setTheme(colorReference, getResources());
        }

        flatSeekBar.getAttributes().setTheme(colorReference, getResources());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                flatSeekBar.setProgress(30);
                flatSeekBar.setSecondaryProgress(40);
            }
        });

        // if you are using standard action bar (not compatibility library) use this
        // getActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(this, colorReference, false));

        // if you are using ActionBar of Compatibility library, get drawable and set it manually
        getSupportActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(this, colorReference, false));

        setTitle("FlatUI Sample App");
    }

}
