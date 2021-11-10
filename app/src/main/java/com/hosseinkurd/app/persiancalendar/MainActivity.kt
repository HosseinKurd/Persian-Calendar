package com.hosseinkurd.app.persiancalendar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hosseinkurd.app.persiancalendarlibrary.WorkCalendarView
import com.hosseinkurd.app.persiancalendarlibrary.enums.EnumWorkCalendarState
import com.hosseinkurd.app.persiancalendarlibrary.interfaces.OnClickListenerPersianCalendarLibrary
import com.hosseinkurd.app.persiancalendarlibrary.models.WorkCalendarModel


class MainActivity : AppCompatActivity() {

    private lateinit var workCalendarView: WorkCalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        workCalendarView = findViewById(R.id.workCalendarView)
        workCalendarView.onClickListenerPersianCalendarLibrary =
            object : OnClickListenerPersianCalendarLibrary {
                override fun onPersianCalendarLibraryClicked(
                    viewId: Int,
                    workCalendarModel: WorkCalendarModel?
                ) {

                }

            }
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

    override fun onResume() {
        super.onResume()
        val list: MutableList<WorkCalendarModel> = mutableListOf()
        list.apply {
            add(
                WorkCalendarModel(
                    workCalendarState = EnumWorkCalendarState.NORMAL_DAY,
                    isoDate = "2021-11-09T09:54:06.678+00:00",
                    persianDate = "۱۴۰۰/۰۸/۱۹"
                )
            )
            add(
                WorkCalendarModel(
                    workCalendarState = EnumWorkCalendarState.RESERVED_DAY,
                    isoDate = "2021-11-10T09:54:06.678+00:00",
                    persianDate = "۱۴۰۰/۰۸/۲۰"
                )
            )
            add(
                WorkCalendarModel(
                    workCalendarState = EnumWorkCalendarState.NORMAL_DAY,
                    isoDate = "2021-11-11T09:54:06.678+00:00",
                    persianDate = "۱۴۰۰/۰۸/۲۱"
                )
            )
            add(
                WorkCalendarModel(
                    workCalendarState = EnumWorkCalendarState.SELECTED_DAY,
                    isoDate = "2021-11-12T09:54:06.678+00:00",
                    persianDate = "۱۴۰۰/۰۸/۲۲"
                )
            )
            add(
                WorkCalendarModel(
                    workCalendarState = EnumWorkCalendarState.NORMAL_DAY,
                    isoDate = "2021-11-13T09:54:06.678+00:00",
                    persianDate = "۱۴۰۰/۰۸/۲۳"
                )
            )
            add(
                WorkCalendarModel(
                    workCalendarState = EnumWorkCalendarState.RESERVED_DAY,
                    isoDate = "2021-11-14T09:54:06.678+00:00",
                    persianDate = "۱۴۰۰/۰۸/۲۴"
                )
            )
            add(
                WorkCalendarModel(
                    workCalendarState = EnumWorkCalendarState.NORMAL_DAY,
                    isoDate = "2021-11-15T09:54:06.678+00:00",
                    persianDate = "۱۴۰۰/۰۸/۲۵"
                )
            )
            add(
                WorkCalendarModel(
                    workCalendarState = EnumWorkCalendarState.NORMAL_DAY,
                    isoDate = "2021-11-09T09:54:06.678+00:00",
                    persianDate = "۱۴۰۰/۰۸/۱۹"
                )
            )
            add(
                WorkCalendarModel(
                    workCalendarState = EnumWorkCalendarState.RESERVED_DAY,
                    isoDate = "2021-11-10T09:54:06.678+00:00",
                    persianDate = "۱۴۰۰/۰۸/۲۰"
                )
            )
            add(
                WorkCalendarModel(
                    workCalendarState = EnumWorkCalendarState.NORMAL_DAY,
                    isoDate = "2021-11-11T09:54:06.678+00:00",
                    persianDate = "۱۴۰۰/۰۸/۲۱"
                )
            )
            add(
                WorkCalendarModel(
                    workCalendarState = EnumWorkCalendarState.RESERVED_DAY,
                    isoDate = "2021-11-12T09:54:06.678+00:00",
                    persianDate = "۱۴۰۰/۰۸/۲۲"
                )
            )
            add(
                WorkCalendarModel(
                    workCalendarState = EnumWorkCalendarState.NORMAL_DAY,
                    isoDate = "2021-11-13T09:54:06.678+00:00",
                    persianDate = "۱۴۰۰/۰۸/۲۳"
                )
            )
            add(
                WorkCalendarModel(
                    workCalendarState = EnumWorkCalendarState.RESERVED_DAY,
                    isoDate = "2021-11-14T09:54:06.678+00:00",
                    persianDate = "۱۴۰۰/۰۸/۲۴"
                )
            )
            add(
                WorkCalendarModel(
                    workCalendarState = EnumWorkCalendarState.NORMAL_DAY,
                    isoDate = "2021-11-15T09:54:06.678+00:00",
                    persianDate = "۱۴۰۰/۰۸/۲۵"
                )
            )
        }
        workCalendarView.addItems(list)
    }

}