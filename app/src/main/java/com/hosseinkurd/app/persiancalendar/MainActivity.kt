package com.hosseinkurd.app.persiancalendar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hosseinkurd.app.persiancalendarlibrary.WorkCalendarView
import com.hosseinkurd.app.persiancalendarlibrary.enums.EnumWorkCalendarState
import com.hosseinkurd.app.persiancalendarlibrary.interfaces.OnClickListenerPersianCalendarLibrary
import com.hosseinkurd.app.persiancalendarlibrary.models.DailyWorkCalendarModel
import com.hosseinkurd.app.persiancalendarlibrary.models.WorkCalendarModel
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var workCalendarView: WorkCalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        workCalendarView = findViewById(R.id.workCalendarView)
        workCalendarView.onClickListenerPersianCalendarLibrary =
            object : OnClickListenerPersianCalendarLibrary {
                override fun onPersianCalendarLibraryStartClicked() {
                    workCalendarView.showTodayTasks()
                }

                override fun onPersianCalendarLibraryEndClicked() {
                    workCalendarView.showSelectedDayTasks()
                }

                override fun onPersianCalendarLibraryClicked(
                    viewId: Int,
                    workCalendarModel: WorkCalendarModel?
                ) {

                }

            }
        initRecyclerView()
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

    private fun initRecyclerView() {

    }

    private fun addItemsToCalendar() {
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
            add(
                WorkCalendarModel(
                    workCalendarState = EnumWorkCalendarState.NORMAL_DAY,
                    isoDate = "2021-11-16T09:54:06.678+00:00",
                    persianDate = "۱۴۰۰/۰۸/۲۵"
                )
            )
            add(
                WorkCalendarModel(
                    workCalendarState = EnumWorkCalendarState.RESERVED_DAY,
                    isoDate = "2021-11-17T09:54:06.678+00:00",
                    persianDate = "۱۴۰۰/۰۸/۲۶"
                )
            )
            add(
                WorkCalendarModel(
                    workCalendarState = EnumWorkCalendarState.NORMAL_DAY,
                    isoDate = "2021-11-18T09:54:06.678+00:00",
                    persianDate = "۱۴۰۰/۰۸/۲۷"
                )
            )
            add(
                WorkCalendarModel(
                    workCalendarState = EnumWorkCalendarState.RESERVED_DAY,
                    isoDate = "2021-11-19T09:54:06.678+00:00",
                    persianDate = "۱۴۰۰/۰۸/۲۸"
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
                    isoDate = "2021-11-20T09:54:06.678+00:00",
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
                    workCalendarState = EnumWorkCalendarState.SELECTED_DAY,
                    isoDate = "2021-11-12T09:54:06.678+00:00",
                    persianDate = "۱۴۰۰/۰۸/۲۲",
                    dailyWorkCalendarModels = getDailyWorkCalendarModels()
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
                    isoDate = "2021-12-15T09:54:06.678+00:00",
                    persianDate = "۱۴۰۰/۰۹/۲۵"
                )
            )
        }
        workCalendarView.addItems(list)
        workCalendarView.showSelectedDayTasks()
    }

    private fun getDailyWorkCalendarModels(): MutableList<DailyWorkCalendarModel> {
        return mutableListOf<DailyWorkCalendarModel>().apply {
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "امیررضا ملک زادگان",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = "6192240e8602c538a3f2831a",
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "هادی نعمتی",
                    type = "مشاوره آفلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "دانیال جلیل پور",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "امیررضا ملک زادگان",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = "6192240e8602c538a3f2831a",
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "هادی نعمتی",
                    type = "مشاوره آفلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "دانیال جلیل پور",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "امیررضا ملک زادگان",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = "6192240e8602c538a3f2831a",
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "هادی نعمتی",
                    type = "مشاوره آفلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "دانیال جلیل پور",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "امیررضا ملک زادگان",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = "6192240e8602c538a3f2831a",
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "هادی نعمتی",
                    type = "مشاوره آفلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "دانیال جلیل پور",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "امیررضا ملک زادگان",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = "6192240e8602c538a3f2831a",
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "هادی نعمتی",
                    type = "مشاوره آفلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "دانیال جلیل پور",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "امیررضا ملک زادگان",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = "6192240e8602c538a3f2831a",
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "هادی نعمتی",
                    type = "مشاوره آفلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "دانیال جلیل پور",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "امیررضا ملک زادگان",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = "6192240e8602c538a3f2831a",
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "هادی نعمتی",
                    type = "مشاوره آفلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "دانیال جلیل پور",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "امیررضا ملک زادگان",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = "6192240e8602c538a3f2831a",
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "هادی نعمتی",
                    type = "مشاوره آفلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "دانیال جلیل پور",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "امیررضا ملک زادگان",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = "6192240e8602c538a3f2831a",
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "هادی نعمتی",
                    type = "مشاوره آفلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "دانیال جلیل پور",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "امیررضا ملک زادگان",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = "6192240e8602c538a3f2831a",
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "هادی نعمتی",
                    type = "مشاوره آفلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "دانیال جلیل پور",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "امیررضا ملک زادگان",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = "6192240e8602c538a3f2831a",
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "هادی نعمتی",
                    type = "مشاوره آفلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "دانیال جلیل پور",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "امیررضا ملک زادگان",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = "6192240e8602c538a3f2831a",
                    avatar = "https://dummyimage.com/400x400/0026ff/fff.jpg&text=Avatar+1",
                    name = "هادی نعمتی",
                    type = "مشاوره آفلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
            add(
                DailyWorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    avatar = "https://dummyimage.com/400x400/ff0026/fff.jpg&text=Avatar+1",
                    name = "دانیال جلیل پور",
                    type = "مشاوره آنلاین",
                    time = "20 دقیقه",
                    durationFrom = "۱۴:۳۰",
                    durationTo = "۱۵:۳۰"
                )
            )
        }
    }

}