package com.hosseinkurd.app.persiancalendar

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomappbar.BottomAppBar
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
            Toast.makeText(this, "First Day In View : '${workCalendarView.getFirstDayOfWeek()?.persianDate}'", Toast.LENGTH_LONG).show()
        }
        findViewById<Button>(R.id.buttonLastDayInView).setOnClickListener {
            Toast.makeText(this, "Last Day In View : '${workCalendarView.getLastDayOfWeek()?.persianDate}'", Toast.LENGTH_LONG).show()
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

            }
    }

    private fun addItemsToCalendar() {
        workCalendarView.addItems(mutableListOf<CalendarDayModel>().apply { addAll(items) })
        workCalendarView.showSelectedDayTasks()
    }

    private val items = mutableListOf<CalendarDayModel>().apply {
        add(CalendarDayModel(isoDate = "2021-11-01T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-02T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-03T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-04T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-05T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-06T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-07T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-08T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-09T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-10T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-11T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-12T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-13T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-14T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-15T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-16T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-17T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-18T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-19T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-20T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-21T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-22T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-23T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-24T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-25T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-26T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-27T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-28T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-29T09:54:06.678+00:00"))
        add(CalendarDayModel(isoDate = "2021-11-30T09:54:06.678+00:00"))
    }

}