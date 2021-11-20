package com.hosseinkurd.app.persiancalendar

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hosseinkurd.app.persiancalendarlibrary.WorkCalendarView
import com.hosseinkurd.app.persiancalendarlibrary.interfaces.OnClickListenerPersianCalendarLibrary
import com.hosseinkurd.app.persiancalendarlibrary.models.CalendarDayModel
import com.hosseinkurd.app.persiancalendarlibrary.models.WorkCalendarModel


class MainActivity : AppCompatActivity() {

    private lateinit var workCalendarView: WorkCalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        workCalendarView = findViewById(R.id.workCalendarView)
        initInterface()
        addItemsToCalendar()
        /*val itemWidth = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics: WindowMetrics =
                windowManager.currentWindowMetrics
            val insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
        } else {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }*/
    }

    private fun initInterface() {
        findViewById<Button>(R.id.buttonGoToToday).setOnClickListener {
            workCalendarView.showTodayTasks()
        }
        findViewById<Button>(R.id.buttonGoToSelected).setOnClickListener {
            workCalendarView.showSelectedDayTasks()
        }
        findViewById<Button>(R.id.buttonFirstDayInView).setOnClickListener {
            Toast.makeText(
                this,
                "First Day In View : '${workCalendarView.getFirstDayOfWeek()?.persianDate}'",
                Toast.LENGTH_LONG
            ).show()
        }
        findViewById<Button>(R.id.buttonLastDayInView).setOnClickListener {
            Toast.makeText(
                this,
                "Last Day In View : '${workCalendarView.getLastDayOfWeek()?.persianDate}'",
                Toast.LENGTH_LONG
            ).show()
        }
        workCalendarView.onClickListenerPersianCalendarLibrary =
            object : OnClickListenerPersianCalendarLibrary {
                override fun onPersianCalendarLibraryStartClicked() {

                }

                override fun onPersianCalendarLibraryEndClicked() {

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
    }

    private fun addItemsToCalendar() {
        workCalendarView.addItems(isoList = mutableListOf<CalendarDayModel>().apply { addAll(items) })
        workCalendarView.showSelectedDayTasks()
    }

    private val items = mutableListOf<CalendarDayModel>().apply {
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

}