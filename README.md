Android FlatUI
===================

FlatUI is a library that lets you use native android widgets with a better and customized look.

You can define the widgets in XML or create on run time in JAVA. Even though the widgets are customized, you can create your own widget styles via attributes.

There are many predefined themes inside this library and there will be a functionality to add your own colors to it soon.


Features included
-----------------
* Creating widgets inside XML.
* Creating widgets inside JAVA.
* Using defined themes.
* Changing theme at runtime.
* Changing ActionBar theme.


Widgets
-----------
![Themes][1]

Themes
-----------
![Themes][2]

 [1]: https://raw.github.com/eluleci/FlatUI/master/sample-images/showcase.png
 [2]: https://raw.github.com/eluleci/FlatUI/master/sample-images/themes.png

Including into your project
-------------------------

This library is pushed to Maven Central, so you just need to add the following dependency to your `build.gradle`.

    dependencies {
        compile 'com.github.eluleci:flatui:0.1.2'
    }


IMPORTANT - If you want to use the widgets inside xml, you need to add this line to the root element of layout and use necessary parameters with 'flatui' tag
```xml
xmlns:flatui="http://schemas.android.com/apk/res-auto"
```

## Main Java functions

```java

// Setting default theme to avoid to add the attribute "theme" to XML 
// and to be able to change the whole theme at once
FlatUI.setDefaultTheme(FlatUI.DEEP);

// to change the color of the action bar at runtime
FlatUI.setActionBarTheme(this, FlatUI.DEEP, false, false);

// if you are using ActionBar of compatibility library, get drawable and set it manually to support action bar.
getSupportActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(FlatUI.DEEP, false));

```

## Attribute list

- theme          :  theme of the element
- textAppearance :  text color on the element. dark or light colors of the theme.(none, dark, light)
- fontFamily     :  font family of the element. (DroidSans, OpenSans, Roboto)
- fontWeight     :  font weight of the text (extralight, light, regular, bold, extrabold)
- cornerRadius   :  corner radius of the element. (dimension)
- textPadding    :  text padding of the element. (dimension)
- size           :  size of the element. (dimension)
- isFullFlat     :  shape of button (boolean)
- fieldStyle     :  shape of the edit text (flat, box, transparent)
- textColor      :  text color of the text view (darker, dark, main, light)
- backgroundColor:  selecting one of the colors of theme (darker, dark, main, light)
- customBackgroundColor : (color)

## Samples

```xml

<!-- TextView -->
<!-- available attrs: cornerRadius, fontFamily, fontWeight, textColor, backgroundColor, customBackgroundColor -->

<com.cengalabs.flatui.views.FlatTextView
                android:id="@+id/title_edittexts"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="10dip"
                android:layout_marginLeft="5dip"
                android:maxLines="1"
                android:gravity="center_vertical"
                flatui:textColor="main"
                android:text="Edit Texts"
                android:textSize="20dip"/>

<!-- FlatEditText -->
<!-- available attrs: fieldStyle, cornerRadius, textPadding, fontFamily, fontWeight, textAppearance -->

<com.cengalabs.flatui.views.FlatEditText
	android:id="@+id/edittext_flat"
	android:layout_width="match_parent"
	android:layout_height="wrap_content"                        
	flatui:theme="Sand"
	flatui:fieldStyle="flat"
	flatui:cornerRadius="3dip"/>
	
<com.cengalabs.flatui.views.FlatEditText
	android:id="@+id/edittext_flat"
	android:layout_width="match_parent"
	android:layout_height="wrap_content"                        
	flatui:theme="Sand"
	flatui:fieldStyle="box"
	flatui:cornerRadius="3dip"/>

<com.cengalabs.flatui.views.FlatEditText
	android:id="@+id/edittext_flat"
	android:layout_width="match_parent"
	android:layout_height="wrap_content"                        
	flatui:theme="Sand"
	flatui:fieldStyle="transparent"
	flatui:cornerRadius="3dip"/>
	

<!-- FlatSeekBar -->
<!-- available attrs: none -->

<com.cengalabs.flatui.views.FlatSeekBar
	android:id="@+id/seekbar"
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	android:layout_margin="5dip"
	flatui:theme="Sand"
	android:max="100"/>
	

<!-- FlatButton -->
<!-- available attrs: textApearance, textPadding, cornerRadius, isFullFlat, fontFamily, fontWeight -->

<com.cengalabs.flatui.views.FlatButton
	android:id="@+id/button_block"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	android:text="Block"
	flatui:theme="Sand"
	flatui:textAppearance="light"/>

<com.cengalabs.flatui.views.FlatButton
	android:id="@+id/button_flat"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	android:text="Full Flat"
	flatui:theme="Sand"
	flatui:isFullFlat="true"
	flatui:textAppearance="light"/>
	

	
<!-- FlatCheckBox -->
<!-- available attrs: cornerRadius, size, fontFamily, fontWeight -->

<com.cengalabs.flatui.views.FlatCheckBox
	android:id="@+id/checkbox_unchecked_enabled"
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	flatui:theme="Sand"
	flatui:size="20dip"
	android:text="unchecked enabled"/>

	
	
<!-- FlatRadioButton -->
<!-- available attrs: size, fontFamily, fontWeight -->

<com.cengalabs.flatui.views.FlatRadioButton
	android:id="@+id/checkbox_unchecked_disabled"
	android:layout_width="match_parent"
	android:layout_height="wrap_content"	
	flatui:theme="Sand"
	flatui:size="20dip"
	android:text="unchecked enabled"
	android:enabled="false"/>
	
	

<!-- FlatToggleButton -->
<!-- available attrs: size -->

<com.cengalabs.flatui.views.FlatToggleButton
	android:id="@+id/toggle_unchecked_enabled"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	flatui:theme="Sand"
	flatui:size="20dip"/>

	
```

License
--------

    Copyright 2014 Cengalabs

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.