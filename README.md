Flat UI Kit
===================

Flat UI Kit is a library that lets you use native android widgets with a better and customized look.

You can define the widgets in XML or create on run time in JAVA. Even though the widgets are customized, you can create your own widget styles with some attributes.

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
 
 
## Usage (Android Studio)

Download the library and extract it. Put the folder into libs directory of the project. 

- Open "Project Structure" (Right click on project -> "Open Module Settings").
- Select "Modules" from left navigation menu.
- Click "+" button on left top and select "Import Module".
- Select the root directory of the project that you put into your libs folder.
- Click next in all of the pages.
- Then click your project name below the "+" button at top.
- Then click "+" button at the right side and select "Module Dependency".
- Select FlatUI and DONE.

For using this library in Android Studio (IntelliJ IDEA) you also need to activate including assets from dependencies to be able to include font files.
- Open project structure (Right click on project -> 'Open Module Settings')
- Select 'Facets' from left menu
- Select 'Compiler' tab at right
- Activate 'Include assets from dependencies into APK' beneath 'Resources Packaging'

Also you need to add this part to the root element of the element in layout page

```xml
xmlns:app="http://schemas.android.com/apk/res-auto"
```

## Main Java functions

```java

// Setting default theme to avoid to add the attribute "theme" to XML 
// and to be able to change the whole theme at once
FlatUI.setDefaultTheme(FlatUI.DEEP);

// to change the color of the action bar at runtime
FlatUI.setActionBarTheme(this, FlatUI.DEEP, false);

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
- customBackgroundColor : (color)

## Samples

```xml

<!-- FlatEditText -->
<!-- available attrs: fieldStyle, cornerRadius, textPadding, fontFamily, fontWeight, textAppearance -->

<com.cengalabs.flatui.views.FlatEditText
	android:id="@+id/edittext_flat"
	android:layout_width="match_parent"
	android:layout_height="wrap_content"                        
	app:theme="Sand"
	app:fieldStyle="flat"
	app:cornerRadius="3dip"/>
	
<com.cengalabs.flatui.views.FlatEditText
	android:id="@+id/edittext_flat"
	android:layout_width="match_parent"
	android:layout_height="wrap_content"                        
	app:theme="Sand"
	app:fieldStyle="box"
	app:cornerRadius="3dip"/>

<com.cengalabs.flatui.views.FlatEditText
	android:id="@+id/edittext_flat"
	android:layout_width="match_parent"
	android:layout_height="wrap_content"                        
	app:theme="Sand"
	app:fieldStyle="transparent"
	app:cornerRadius="3dip"/>
	

<!-- FlatSeekBar -->
<!-- available attrs: none -->

<com.cengalabs.flatui.views.FlatSeekBar
	android:id="@+id/seekbar"
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	android:layout_margin="5dip"
	app:theme="Sand"
	android:max="100"/>
	

<!-- FlatButton -->
<!-- available attrs: textApearance, textPadding, cornerRadius, isFullFlat, fontFamily, fontWeight -->

<com.cengalabs.flatui.views.FlatButton
	android:id="@+id/button_block"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	android:text="Block"
	app:theme="Sand"
	app:textAppearance="light"/>

<com.cengalabs.flatui.views.FlatButton
	android:id="@+id/button_flat"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	android:text="Full Flat"
	app:theme="Sand"
	app:isFullFlat="true"
	app:textAppearance="light"/>
	

	
<!-- FlatCheckBox -->
<!-- available attrs: cornerRadius, size, fontFamily, fontWeight -->

<com.cengalabs.flatui.views.FlatCheckBox
	android:id="@+id/checkbox_unchecked_enabled"
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	app:theme="Sand"
	app:size="20dip"
	android:text="unchecked enabled"/>

	
	
<!-- FlatRadioButton -->
<!-- available attrs: size, fontFamily, fontWeight -->

<com.cengalabs.flatui.views.FlatRadioButton
	android:id="@+id/checkbox_unchecked_disabled"
	android:layout_width="match_parent"
	android:layout_height="wrap_content"	
	app:theme="Sand"
	app:size="20dip"
	android:text="unchecked enabled"
	android:enabled="false"/>
	
	

<!-- FlatToggleButton -->
<!-- available attrs: size -->

<com.cengalabs.flatui.views.FlatToggleButton
	android:id="@+id/toggle_unchecked_enabled"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	app:theme="Sand"
	app:size="20dip"/>

	
```

