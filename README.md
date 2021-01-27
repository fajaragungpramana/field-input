# Android Field Input
[![](https://jitpack.io/v/fajaragungpramana/field-input.svg)](https://jitpack.io/#fajaragungpramana/field-input)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
</br>
</br>
Library for android. FieldInput is layout component base on ViewGroup LinearLayout for get input from user.

## Installation
Add it in your root build.gradle at the end of repositories:

```gradle
allProjects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Add the dependency:
```gradle
dependencies {
	implementation 'com.github.fajaragungpramana:field-input:0.0.4'
}
```

## Usage
Define a view in your layout file:
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <com.github.fajaragungpramana.field.FieldInput
        android:id="@+id/field_input"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginHorizontal="16dp"
        android:layout_marginTop="16dp"
        app:drawableEnd="@drawable/ic_barcode"
        app:focusable="true"
        app:hint="@string/app_name"
        app:inputType="text"
        app:textAllCaps="false"
        app:textColor="@color/black"
        app:textSize="14sp" />

</LinearLayout>
```

Handle drawable click listener.
```kotlin
fieldInput.setOnClickDrawableListener(DrawablePosition.END) {
	Log.d(MainActivity::class.simpleName, "Drawable ${DrawablePosition.END} Clicked!")
}
```

Get or do something when user is still typing text.
```kotlin
fieldInput.setOnTextChanged { text ->
	// Do something hire when user still typing
}
```

Set error message.
```kotlin
fieldInput.errorMessage = "Type something error message here!"
```

Set or get text input.
```kotlin
fieldInput.text // Do this to get input
fieldInput.text = "Type something here!" // Do this to set input
```

## Preview
Idle
<a href="url"><img src="https://github.com/fajaragungpramana/assets/blob/master/FieldInput/Idle.jpg" align="left" height="640" width="320" ></a>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>

Typing
<a href="url"><img src="https://github.com/fajaragungpramana/assets/blob/master/FieldInput/Typing.jpg" align="left" height="640" width="320" ></a>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>

Error
<a href="url"><img src="https://github.com/fajaragungpramana/assets/blob/master/FieldInput/Error.jpg" align="left" height="640" width="320" ></a>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>

## Documentation
Attribute for FieldInput
| Attribute Name | Default Value | Description |
|----------------|---------------|-------------|
| hint | null | For set hint of field |
| focusable | true | For activate focus |
| style | null | For set text appearance of text field |
| textAllCaps | false | For activate field text caps |
| drawableEnd | null | For put drawable in the right side |
| inputType | text | For input type field |
| textColor | null | For set color text field |
| textSize | 14sp | For set text size field |

## Contributing
Pull requests are welcome. For major changes, please open an issue first to 
discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
Copyright 2021 Fajar Agung Pramana

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
