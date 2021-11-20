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

Generate isoDates List:

    isoList = mutableListOf<CalendarDayModel>().apply {
        add(CalendarDayModel(isoDate = "2021-11-01"))
        add(CalendarDayModel(isoDate = "2021-11-02"))
        add(CalendarDayModel(isoDate = "2021-11-03"))
        add(CalendarDayModel(isoDate = "2021-11-04"))
        add(CalendarDayModel(isoDate = "2021-11-05"))
        add(CalendarDayModel(isoDate = "2021-11-06"))
        add(CalendarDayModel(isoDate = "2021-11-07"))
        add(CalendarDayModel(isoDate = "2021-11-08"))
        add(CalendarDayModel(isoDate = "2021-11-09"))
        add(CalendarDayModel(isoDate = "2021-11-10"))
        add(CalendarDayModel(isoDate = "2021-11-11"))
        add(CalendarDayModel(isoDate = "2021-11-12"))
        add(CalendarDayModel(isoDate = "2021-11-13"))
        add(CalendarDayModel(isoDate = "2021-11-14"))
        add(CalendarDayModel(isoDate = "2021-11-15"))
        add(CalendarDayModel(isoDate = "2021-11-16"))
        add(CalendarDayModel(isoDate = "2021-11-17"))
        add(CalendarDayModel(isoDate = "2021-11-18"))
        add(CalendarDayModel(isoDate = "2021-11-19"))
        add(CalendarDayModel(isoDate = "2021-11-20"))
        add(CalendarDayModel(isoDate = "2021-11-21"))
        add(CalendarDayModel(isoDate = "2021-11-22"))
        add(CalendarDayModel(isoDate = "2021-11-23"))
        add(CalendarDayModel(isoDate = "2021-11-24"))
        add(CalendarDayModel(isoDate = "2021-11-25"))
        add(CalendarDayModel(isoDate = "2021-11-26"))
        add(CalendarDayModel(isoDate = "2021-11-27"))
        add(CalendarDayModel(isoDate = "2021-11-28"))
        add(CalendarDayModel(isoDate = "2021-11-29"))
        add(CalendarDayModel(isoDate = "2021-11-30"))
    }

Add to View :

    workCalendarView.addItems(isoList = isoList)

Change state to Reserved:

    workCalendarView.changeItemState("2021-11-10", EnumWorkCalendarState.RESERVED_DAY)

Get First Day Of Current Week in screen:

    workCalendarView.getFirstDayOfWeek()

Get last day of current week in screen:

    workCalendarView.getLastDayOfWeek()

Show past week in screen:

    workCalendarView.showPastWeek()

Show next week in screen:

    workCalendarView.showNextWeek()

Set interface for desired actions

        workCalendarView.onClickListenerPersianCalendarLibrary =
            object : OnClickListenerPersianCalendarLibrary {
                override fun onPersianCalendarLibraryStartClicked() {
                    //  Scroll to past Week
                }

                override fun onPersianCalendarLibraryEndClicked() {
                    //  Scroll to next Week
                }

                override fun onPersianCalendarLibraryClicked(
                    workCalendarModel: WorkCalendarModel
                ) {
                    workCalendarView.changeSelectedItem(workCalendarModel)
                }

                override fun onPersianCalendarLibraryScrolled(
                    firstWorkCalendarModel: WorkCalendarModel?,
                    lastWorkCalendarModel: WorkCalendarModel?
                ) {
                    println("onPersianCalendarLibraryScrolled >> firstWorkCalendarModel : $firstWorkCalendarModel")
                    println("onPersianCalendarLibraryScrolled >> lastWorkCalendarModel : $lastWorkCalendarModel")
                    println("onPersianCalendarLibraryScrolled >> *************************************************")
                }

            }

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
