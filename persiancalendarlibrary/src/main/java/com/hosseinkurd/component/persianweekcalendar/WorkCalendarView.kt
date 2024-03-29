package com.hosseinkurd.component.persianweekcalendar

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.hosseinkurd.component.persianweekcalendar.adapters.AbstractAdapter
import com.hosseinkurd.component.persianweekcalendar.adapters.WorkCalendarAdapter
import com.hosseinkurd.component.persianweekcalendar.databinding.WorkCalendarViewBinding
import com.hosseinkurd.component.persianweekcalendar.enums.EnumWorkCalendarState
import com.hosseinkurd.component.persianweekcalendar.interfaces.OnClickListenerPersianCalendarLibrary
import com.hosseinkurd.component.persianweekcalendar.models.CalendarDayModel
import com.hosseinkurd.component.persianweekcalendar.models.WorkCalendarModel
import com.hosseinkurd.component.persianweekcalendar.utils.PersianCalendarConvertor
import com.hosseinkurd.component.persianweekcalendar.utils.PublicFunctions
import com.hosseinkurd.component.persianweekcalendar.utils.PublicValues
import com.hosseinkurd.component.persianweekcalendar.utils.twoDigitsPersianCalendarLibrary
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class WorkCalendarView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout
    (
    context,
    attributeSet,
    defStyleAttr
) {
    var reverseDayList = false
    var autoGenerateDays = false
    var autoIncreaseDays = true
    private var binding: WorkCalendarViewBinding
    var onClickListenerPersianCalendarLibrary: OnClickListenerPersianCalendarLibrary? = null

    init {
        val inflater =
            (getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        binding = DataBindingUtil.inflate(inflater, R.layout.work_calendar_view, this, true)
        initializeView(attributeSet, defStyleAttr)
        initializeListeners()
    }

    fun addItems(
        isoList: MutableList<CalendarDayModel>,
        isoDatePattern: String = PublicValues.PATTERN_YYYY_MM_dd
    ) {
        adaptToView(convertToWorkCalendarModelList(isoList, isoDatePattern))
    }

    fun addItemsToFist(
        isoList: MutableList<CalendarDayModel>,
        isoDatePattern: String = PublicValues.PATTERN_YYYY_MM_dd
    ) {
        adaptToViewToFirst(convertToWorkCalendarModelList(isoList, isoDatePattern))
    }

    fun showSelectedDayTasks() {
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter is WorkCalendarAdapter) {
                adapter.items.firstOrNull {
                    it.isSelected
                }?.let { item ->
                    val index = adapter.items.indexOf(item)
                    isManualScroll = false
                    binding.recyclerViewDay.scrollToPosition(index)
                    binding.textViewTitle.text = getYearMonth(item.isoDate!!)
                    callOnScrolledManually()
                }
            }
        }
    }

    fun showTodayTasks() {
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter is WorkCalendarAdapter) {
                val dateFormat: DateFormat =
                    SimpleDateFormat(
                        PublicValues.PATTERN_YYYY_MM_dd,
                        Locale.getDefault()
                    )// yyyy-MM-dd'T'HH:mmZ
                val nowAsString: String = dateFormat.format(Date())
                adapter.items.firstOrNull {
                    it.isoDate?.startsWith(nowAsString) ?: false
                }?.let { item ->
                    val index = adapter.items.indexOf(item)
                    isManualScroll = false
                    binding.recyclerViewDay.scrollToPosition(index)
                    adapter.changeSelectedItem(item)
                    binding.textViewTitle.text = getYearMonth(item.isoDate!!)
                    callOnScrolledManually()
                }
            }
        }
    }

    fun showNextWeek() {
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter is WorkCalendarAdapter) {
                var lastDayOfWeekIndex =
                    (binding.recyclerViewDay.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() + 7
                if (lastDayOfWeekIndex >= adapter.itemCount) lastDayOfWeekIndex = adapter.itemCount
                isManualScroll = false
                binding.recyclerViewDay.smoothScrollToPosition(lastDayOfWeekIndex)
                callOnScrolledManually()
            }
        }
    }

    fun showPastWeek() {
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter is WorkCalendarAdapter) {
                var firstDayOfWeekIndex =
                    (binding.recyclerViewDay.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() - 7
                if (firstDayOfWeekIndex < 0) firstDayOfWeekIndex = 0
                isManualScroll = false
                binding.recyclerViewDay.smoothScrollToPosition(firstDayOfWeekIndex)
                callOnScrolledManually()
            }
        }
    }

    fun getSelectedDayOfWeek(): WorkCalendarModel? {
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter.itemCount < 1) return null
            if (adapter is WorkCalendarAdapter) {
                return adapter.items.firstOrNull { it.isSelected }
            }
        }
        return null
    }

    fun getDays(): MutableList<WorkCalendarModel>? {
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter.itemCount < 1) return null
            if (adapter is WorkCalendarAdapter) {
                return adapter.items
            }
        }
        return null
    }

    fun getFirstDayOfWeek(): WorkCalendarModel? {
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter.itemCount < 1) return null
            if (adapter is WorkCalendarAdapter) {
                val findFirstVisibleItemPosition =
                    (binding.recyclerViewDay.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                if (findFirstVisibleItemPosition < 0 || findFirstVisibleItemPosition >= adapter.itemCount) return null
                return adapter.getItem(position = findFirstVisibleItemPosition)
            }
        }
        return null
    }

    fun getLastDayOfWeek(): WorkCalendarModel? {
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter.itemCount < 1) return null
            if (adapter is WorkCalendarAdapter) {
                val findLastVisibleItemPosition =
                    (binding.recyclerViewDay.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (findLastVisibleItemPosition < 0 || findLastVisibleItemPosition >= adapter.itemCount) return null
                return adapter.getItem(position = findLastVisibleItemPosition)
            }
        }
        return null
    }

    fun changeSelectedItem(
        item: WorkCalendarModel,
    ) {
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter is WorkCalendarAdapter) adapter.changeSelectedItem(item)
        }
    }

    fun changeItemState(
        isoDate: String,
        workCalendarState: EnumWorkCalendarState = EnumWorkCalendarState.NORMAL_DAY
    ) {
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter is WorkCalendarAdapter) adapter.changeItemState(isoDate, workCalendarState)
        }
    }

    fun changeItemState(
        isoDates: MutableList<String>,
        workCalendarState: EnumWorkCalendarState = EnumWorkCalendarState.NORMAL_DAY
    ) {
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter is WorkCalendarAdapter) adapter.changeItemStates(
                isoDates,
                workCalendarState
            )
        }
    }

    fun setTitle(title: String) {
        binding.textViewTitle.text = title
    }

    fun getVisibleItemList(): MutableList<WorkCalendarModel> {
        return mutableListOf<WorkCalendarModel>().apply {
            binding.recyclerViewDay.adapter?.let { adapter ->
                if (adapter.itemCount > 0) {
                    if (adapter is WorkCalendarAdapter) {
                        val firstItemPosition =
                            (binding.recyclerViewDay.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                        val lastItemPosition =
                            (binding.recyclerViewDay.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                        if (lastItemPosition >= 0 && lastItemPosition < adapter.itemCount) {
                            if (firstItemPosition >= 0 || firstItemPosition < adapter.itemCount) {
                                if (firstItemPosition == lastItemPosition) {
                                    add(adapter.getItem(position = firstItemPosition))
                                } else {
                                    addAll(
                                        adapter.items.subList(
                                            firstItemPosition,
                                            lastItemPosition
                                        )
                                    )
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    fun getMoreNumerousMonthInWeek(): String {
        val workCalendarModelList = getVisibleItemList()
        if (workCalendarModelList.isEmpty()) return ""
        var firstYearAndMonthCount = 0
        var secondYearAndMonthCount = 0
        var firstYearAndMonth = ""
        var secondYearAndMonth = ""
        workCalendarModelList.forEach { item ->
            val yearAndMonth = getYearMonth(item.isoDate!!)
            if (firstYearAndMonth.isEmpty() || firstYearAndMonth == yearAndMonth) {
                firstYearAndMonth = yearAndMonth
                firstYearAndMonthCount++
            } else if (secondYearAndMonth.isEmpty() || secondYearAndMonth == yearAndMonth) {
                secondYearAndMonth = yearAndMonth
                secondYearAndMonthCount++
            }
        }
        if (firstYearAndMonthCount > secondYearAndMonthCount) return firstYearAndMonth
        return secondYearAndMonth
    }

    private fun callOnScrolledManually() {
        Handler(Looper.getMainLooper()).postDelayed({
            onClickListenerPersianCalendarLibrary?.onPersianCalendarLibraryScrolled(
                firstWorkCalendarModel = getFirstDayOfWeek(),
                lastWorkCalendarModel = getLastDayOfWeek()
            )
            isManualScroll = true
        }, 800)
    }

    private fun initializeView(attributeSet: AttributeSet? = null, defStyleAttr: Int) {
        if (attributeSet != null) {
            val typedArray = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.WorkCalendarView,
                defStyleAttr,
                0
            )
            val dayListMarginTop = typedArray.getDimension(
                R.styleable.WorkCalendarView_dayListMarginTop,
                context.resources.getDimension(R.dimen.recyclerview_day_margin_top_persian_calendar_library)
            )
            val weekCalendarButtonPadding = typedArray.getDimension(
                R.styleable.WorkCalendarView_weekCalendarButtonPadding,
                context.resources.getDimension(R.dimen.padding_small_persian_calendar_library)
            ).toInt()
            val weekCalendarBackgroundStart = typedArray.getDrawable(
                R.styleable.WorkCalendarView_weekCalendarBackgroundStart,
            )
            val weekCalendarBackgroundEnd = typedArray.getDrawable(
                R.styleable.WorkCalendarView_weekCalendarBackgroundEnd,
            )
            val weekCalendarSrcStart = typedArray.getDrawable(
                R.styleable.WorkCalendarView_weekCalendarSrcStart,
            )
            val weekCalendarSrcEnd = typedArray.getDrawable(
                R.styleable.WorkCalendarView_weekCalendarSrcEnd,
            )
            reverseDayList = typedArray.getBoolean(
                R.styleable.WorkCalendarView_reverseDayList, false
            )
            autoGenerateDays = typedArray.getBoolean(
                R.styleable.WorkCalendarView_autoGenerateDays, false
            )
            autoIncreaseDays = typedArray.getBoolean(
                R.styleable.WorkCalendarView_autoIncreaseDays, true
            )
            (binding.recyclerViewDay.layoutParams as MarginLayoutParams).setMargins(
                0,
                dayListMarginTop.toInt(),
                0,
                0
            )
            binding.imageViewStart.setPadding(
                weekCalendarButtonPadding,
                weekCalendarButtonPadding,
                weekCalendarButtonPadding,
                weekCalendarButtonPadding
            )
            binding.imageViewEnd.setPadding(
                weekCalendarButtonPadding,
                weekCalendarButtonPadding,
                weekCalendarButtonPadding,
                weekCalendarButtonPadding
            )
            weekCalendarBackgroundStart?.let { binding.imageViewStart.background = it }
            weekCalendarBackgroundEnd?.let { binding.imageViewEnd.background = it }
            weekCalendarSrcStart?.let { binding.imageViewStart.setImageDrawable(it) }
            weekCalendarSrcEnd?.let { binding.imageViewEnd.setImageDrawable(it) }
        }
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerViewDay)
        binding.recyclerViewDay.setHasFixedSize(true)
        post {
            initializeDays()
            display()
        }
    }

    private var isManualScroll = true

    private fun initializeListeners() {
        binding.imageViewStart.setOnClickListener {
            onClickListenerPersianCalendarLibrary?.onPersianCalendarLibraryStartClicked()
        }
        binding.imageViewEnd.setOnClickListener {
            onClickListenerPersianCalendarLibrary?.onPersianCalendarLibraryEndClicked()
        }
        binding.recyclerViewDay.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (RecyclerView.SCROLL_STATE_IDLE == newState && isManualScroll) {
//                    checkForPreviousDays()
//                    checkFoNextDays()
                    onClickListenerPersianCalendarLibrary?.onPersianCalendarLibraryScrolled(
                        firstWorkCalendarModel = getFirstDayOfWeek(),
                        lastWorkCalendarModel = getLastDayOfWeek()
                    )
                }
            }
        })
    }

    private fun initializeDays() {
        var dayItemWidth: Int = binding.recyclerViewDay.width / 7
        if (dayItemWidth <= 0) dayItemWidth =
            resources.getDimension(R.dimen.width_adapter_item_persian_calendar_library).toInt()
        context?.let {
            binding.recyclerViewDay.layoutManager =
                LinearLayoutManager(it, LinearLayoutManager.HORIZONTAL, reverseDayList)
            binding.recyclerViewDay.adapter =
                WorkCalendarAdapter(context = it, dayItemWidth)
        }
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter is WorkCalendarAdapter) adapter.onItemClickListener =
                object : AbstractAdapter.OnItemClickListener<WorkCalendarModel> {
                    override fun onClicked(actionId: Int, position: Int, item: WorkCalendarModel) {
                        onClickListenerPersianCalendarLibrary?.onPersianCalendarLibraryClicked(item)
                        binding.textViewTitle.text = getYearMonth(item.isoDate!!)
                    }
                }
        }
    }

    private fun display() {
        if (autoGenerateDays) generateCalendarDays()
    }

    private fun convertToWorkCalendarModelList(
        isoList: MutableList<CalendarDayModel>,
        isoDatePattern: String
    ): MutableList<WorkCalendarModel> {
        val workCalendarModelList: MutableList<WorkCalendarModel> = mutableListOf()
        isoList.forEach { item ->
            var dayOfMonth: String? = null
            var dayOfWeek: String? = null
            val calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault())
            calendar.firstDayOfWeek = Calendar.SATURDAY
            val dateFormat = SimpleDateFormat(isoDatePattern, Locale.getDefault())
            try {
                val date: Date? = dateFormat.parse(item.isoDate)
                date?.let { dateAndTime ->
                    calendar.time = dateAndTime
                    dateFormat.parse(item.isoDate)?.let { dateItem ->
                        dayOfMonth = PersianCalendarConvertor(dateItem.time).getPersianDay()
                            .twoDigitsPersianCalendarLibrary()
                    }
                    context?.let {
                        dayOfWeek = when (calendar.get(Calendar.DAY_OF_WEEK)) {
                            Calendar.SATURDAY -> context.getString(R.string.saturday_persian_calendar_library)
                            Calendar.SUNDAY -> context.getString(R.string.sunday_persian_calendar_library)
                            Calendar.MONDAY -> context.getString(R.string.monday_persian_calendar_library)
                            Calendar.TUESDAY -> context.getString(R.string.tuesday_persian_calendar_library)
                            Calendar.WEDNESDAY -> context.getString(R.string.wednesday_persian_calendar_library)
                            Calendar.THURSDAY -> context.getString(R.string.thursday_persian_calendar_library)
                            else -> context.getString(R.string.friday_persian_calendar_library)
                        }
                    }
                }
            } catch (e: ParseException) {
                e.printStackTrace()
                dayOfMonth = "--"
                dayOfWeek = "--"
            }
            workCalendarModelList.add(
                WorkCalendarModel(
                    id = UUID.randomUUID().toString(),
                    workCalendarState = getWorkCalendarStateByIsoDate(
                        isoDate = item.isoDate,
                        item.isReserved
                    ),
                    isoDate = item.isoDate,
                    persianDate = getPersianDate(item.isoDate, isoDatePattern),
                    isSelected = isToday(item.isoDate),
                    dayOfMonth = dayOfMonth,
                    dayOfWeek = dayOfWeek
                )
            )
        }
        return workCalendarModelList
    }

    private fun checkForPreviousDays() {
        if (autoIncreaseDays.not()) return
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter is WorkCalendarAdapter) {
                if ((binding.recyclerViewDay.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() <= 10) {
                    adapter.getItem(position = 0).isoDate?.let { isoDate ->
                        PublicFunctions.getCalendarFromISO(isoDate, PublicValues.PATTERN_YYYY_MM_dd)
                            ?.let { calendar ->
                                val items = mutableListOf<CalendarDayModel>()
                                val dateFormat = SimpleDateFormat(
                                    PublicValues.PATTERN_YYYY_MM_dd,
                                    Locale.getDefault()
                                )
                                for (i in -30..-1) {
                                    calendar.add(Calendar.DATE, -1)
                                    val day = dateFormat.format(calendar.time)
                                    items.add(CalendarDayModel(isoDate = day))
                                }
                                addItemsToFist(items)
                            }
                    }
                }
            }
        }
    }

    private fun checkFoNextDays() {
        if (autoIncreaseDays.not()) return
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter is WorkCalendarAdapter) {
                if ((binding.recyclerViewDay.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() >= adapter.itemCount - 10) {
                    adapter.getItem(position = adapter.itemCount - 1).isoDate?.let { isoDate ->
                        PublicFunctions.getCalendarFromISO(isoDate, PublicValues.PATTERN_YYYY_MM_dd)
                            ?.let { calendar ->
                                val items = mutableListOf<CalendarDayModel>()
                                val dateFormat = SimpleDateFormat(
                                    PublicValues.PATTERN_YYYY_MM_dd,
                                    Locale.getDefault()
                                )
                                for (i in 1..30) {
                                    calendar.add(Calendar.DATE, 1)
                                    val day = dateFormat.format(calendar.time)
                                    items.add(CalendarDayModel(isoDate = day))
                                }
                                addItemsToFist(items)
                            }
                    }
                }
            }
        }
    }

    private fun generateCalendarDays() {
        val items = mutableListOf<CalendarDayModel>()
        val dateFormat = SimpleDateFormat(PublicValues.PATTERN_YYYY_MM_dd, Locale.getDefault())
        for (i in -366..366) {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, i)
            val day = dateFormat.format(calendar.time)
            items.add(CalendarDayModel(isoDate = day))
        }
//        items = items.distinctBy { it.isoDate }.toMutableList()
//        items.sortBy { it.isoDate }
        addItems(items)
        showTodayTasks()
    }

    private fun getYearMonth(isoDate: String): String {
        var persianYearMonth = ""
        val format = SimpleDateFormat(PublicValues.PATTERN_YYYY_MM_dd, Locale.getDefault())
        format.parse(isoDate)?.let { date ->
            persianYearMonth = PersianCalendarConvertor(date.time).getPersianYearMonth()
        }
        return persianYearMonth
    }

    private fun adaptToView(workCalendarModelList: MutableList<WorkCalendarModel>) {
        workCalendarModelList.firstOrNull { it.isSelected }?.let {
            binding.textViewTitle.text = getYearMonth(it.isoDate!!)
        }
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter is WorkCalendarAdapter) {
                adapter.items.addAll(workCalendarModelList)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun adaptToViewToFirst(workCalendarModelList: MutableList<WorkCalendarModel>) {
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter is WorkCalendarAdapter) {
                adapter.items.addAll(0, workCalendarModelList)
                adapter.notifyItemRangeInserted(0, workCalendarModelList.size)
            }
        }
    }

    private fun getPersianDate(isoDate: String, isoDatePattern: String): String {
        val calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault())
        calendar.firstDayOfWeek = Calendar.SATURDAY
        val dateFormat = SimpleDateFormat(isoDatePattern, Locale.getDefault())
        val date: Date? = dateFormat.parse(isoDate)
        date?.let { dateAndTime ->
            calendar.time = dateAndTime
            dateFormat.parse(isoDate)?.let { dateItem ->
                return PersianCalendarConvertor(dateItem.time).getPersianDate()
            }
        }
        return ""
    }

    private fun getWorkCalendarStateByIsoDate(
        isoDate: String,
        isReserved: Boolean
    ): EnumWorkCalendarState {
        return when {
            isToday(isoDate) -> EnumWorkCalendarState.TODAY
            isReserved -> EnumWorkCalendarState.RESERVED_DAY
            else -> EnumWorkCalendarState.NORMAL_DAY
        }
    }

    private fun isToday(
        isoDate: String,
    ): Boolean {
        val dateFormat: DateFormat =
            SimpleDateFormat(
                PublicValues.PATTERN_YYYY_MM_dd,
                Locale.getDefault()
            )
        val nowAsString: String = dateFormat.format(Date())
        return isoDate.startsWith(nowAsString)
    }

}