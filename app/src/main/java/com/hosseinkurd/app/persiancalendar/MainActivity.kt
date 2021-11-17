package com.hosseinkurd.app.persiancalendar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hosseinkurd.app.persiancalendarlibrary.WorkCalendarView
import com.hosseinkurd.app.persiancalendarlibrary.enums.EnumWorkCalendarState
import com.hosseinkurd.app.persiancalendarlibrary.interfaces.OnClickListenerPersianCalendarLibrary
import com.hosseinkurd.app.persiancalendarlibrary.models.WorkCalendarModel
import java.util.*


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
        workCalendarView.onClickListenerPersianCalendarLibrary =
            object : OnClickListenerPersianCalendarLibrary {
                override fun onPersianCalendarLibraryStartClicked() {
                    workCalendarView.showTodayTasks()
                }

                override fun onPersianCalendarLibraryEndClicked() {
                    workCalendarView.showSelectedDayTasks()
                }

                override fun onPersianCalendarLibraryClicked(
                    workCalendarModel: WorkCalendarModel
                ) {
                    workCalendarView.changeSelectedItem(workCalendarModel)
                }

            }
    }

    private fun addItemsToCalendar() {
        workCalendarView.addItems(mutableListOf<WorkCalendarModel>().apply { addAll(items) })
        workCalendarView.showSelectedDayTasks()
    }

    private val items = mutableListOf<WorkCalendarModel>().apply {
        add(
            WorkCalendarModel(
                id = UUID.randomUUID().toString(),
                workCalendarState = EnumWorkCalendarState.RESERVED_DAY,
                isoDate = "2021-11-11T09:54:06.678+00:00",
                persianDate = "۱۴۰۰/۰۸/۲۰"
            )
        )
        add(
            WorkCalendarModel(
                id = UUID.randomUUID().toString(),
                workCalendarState = EnumWorkCalendarState.NORMAL_DAY,
                isoDate = "2021-11-12T09:54:06.678+00:00",
                persianDate = "۱۴۰۰/۰۸/۲۱"
            )
        )
        add(
            WorkCalendarModel(
                id = UUID.randomUUID().toString(),
                workCalendarState = EnumWorkCalendarState.RESERVED_DAY,
                isoDate = "2021-11-13T09:54:06.678+00:00",
                persianDate = "۱۴۰۰/۰۸/۲۲"
            )
        )
        add(
            WorkCalendarModel(
                id = UUID.randomUUID().toString(),
                workCalendarState = EnumWorkCalendarState.NORMAL_DAY,
                isoDate = "2021-11-14T09:54:06.678+00:00",
                persianDate = "۱۴۰۰/۰۸/۲۳"
            )
        )
        add(
            WorkCalendarModel(
                id = UUID.randomUUID().toString(),
                workCalendarState = EnumWorkCalendarState.RESERVED_DAY,
                isoDate = "2021-11-15T09:54:06.678+00:00",
                persianDate = "۱۴۰۰/۰۸/۲۴"
            )
        )
        add(
            WorkCalendarModel(
                id = UUID.randomUUID().toString(),
                workCalendarState = EnumWorkCalendarState.NORMAL_DAY,
                isoDate = "2021-11-16T09:54:06.678+00:00",
                persianDate = "۱۴۰۰/۰۸/۲۵"
            )
        )
        add(
            WorkCalendarModel(
                id = UUID.randomUUID().toString(),
                workCalendarState = EnumWorkCalendarState.TODAY,
                isoDate = "2021-11-17T09:54:06.678+00:00",
                persianDate = "۱۴۰۰/۰۸/۲۶",
                isSelected = true
            )
        )
        add(
            WorkCalendarModel(
                id = UUID.randomUUID().toString(),
                workCalendarState = EnumWorkCalendarState.NORMAL_DAY,
                isoDate = "2021-11-18T09:54:06.678+00:00",
                persianDate = "۱۴۰۰/۰۸/۲۷"
            )
        )
        add(
            WorkCalendarModel(
                id = UUID.randomUUID().toString(),
                workCalendarState = EnumWorkCalendarState.RESERVED_DAY,
                isoDate = "2021-11-19T09:54:06.678+00:00",
                persianDate = "۱۴۰۰/۰۸/۲۸"
            )
        )
        add(
            WorkCalendarModel(
                id = UUID.randomUUID().toString(),
                workCalendarState = EnumWorkCalendarState.NORMAL_DAY,
                isoDate = "2021-11-20T09:54:06.678+00:00",
                persianDate = "۱۴۰۰/۰۸/۲۹"
            )
        )
        add(
            WorkCalendarModel(
                id = UUID.randomUUID().toString(),
                workCalendarState = EnumWorkCalendarState.RESERVED_DAY,
                isoDate = "2021-11-21T09:54:06.678+00:00",
                persianDate = "۱۴۰۰/۰۸/۳۰"
            )
        )
    }

}