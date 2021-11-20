# Persian Week Calendar

# Usage:

Check this [link](https://jitpack.io/#HosseinKurd/Persian-Calendar/ "jitpack HosseinKurd ArrowStepper") to find Last version

[![](https://jitpack.io/v/HosseinKurd/Persian-Calendar.svg)](https://jitpack.io/#HosseinKurd/Persian-Calendar/0.30.1)

# Gradle:
Step 1. Add the JitPack repository to your build file
Add it to your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.HosseinKurd:Persian-Calendar:$version'
	}

# Kotlin sample Code:


# XML:

    <com.hosseinkurd.app.persiancalendarlibrary.WorkCalendarView
        android:id="@+id/workCalendarView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:dayListMarginTop="0dp"
        app:weekCalendarBackgroundStart="@drawable/dr_click"
        app:weekCalendarBackgroundEnd="@drawable/dr_click"
        app:weekCalendarSrcStart="@drawable/ic_arrow_start"
        app:weekCalendarSrcEnd="@drawable/ic_arrow_end" />

# Change Resources:

add and modify dimensions at dimen.xml and colors at colors.xml and drawable at res/drawable to what you expect

Special thanks [Hamidreza Amuzadeh](https://github.com/HamidrezaAmz "Hamidreza Amoozadeh")

Enjoy ;)
