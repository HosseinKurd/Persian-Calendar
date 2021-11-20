package com.hosseinkurd.app.persiancalendarlibrary

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.hosseinkurd.app.persiancalendarlibrary.adapters.AbstractAdapter
import com.hosseinkurd.app.persiancalendarlibrary.adapters.WorkCalendarAdapter
import com.hosseinkurd.app.persiancalendarlibrary.databinding.WorkCalendarViewBinding
import com.hosseinkurd.app.persiancalendarlibrary.enums.EnumWorkCalendarState
import com.hosseinkurd.app.persiancalendarlibrary.interfaces.OnClickListenerPersianCalendarLibrary
import com.hosseinkurd.app.persiancalendarlibrary.models.CalendarDayModel
import com.hosseinkurd.app.persiancalendarlibrary.models.WorkCalendarModel
import com.hosseinkurd.app.persiancalendarlibrary.utils.PersianCalendarWrapper
import com.hosseinkurd.app.persiancalendarlibrary.utils.twoDigitsPersianCalendarLibrary
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
        isoDatePattern: String = "yyyy-MM-dd"
    ) {
        adaptToView(convertToWorkCalendarModelList(isoList, isoDatePattern))
    }

    fun addItemsToFist(
        isoList: MutableList<CalendarDayModel>,
        isoDatePattern: String = "yyyy-MM-dd"
    ) {
        adaptToViewToFirst(convertToWorkCalendarModelList(isoList, isoDatePattern))
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
                        dayOfMonth = PersianCalendarWrapper(dateItem.time).getPersianDay()
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

    fun showSelectedDayTasks() {
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter is WorkCalendarAdapter) {
                adapter.items.firstOrNull {
                    it.isSelected
                }?.let { item ->
                    val index = adapter.items.indexOf(item)
                    binding.recyclerViewDay.smoothScrollToPosition(index)
                    binding.textViewTitle.text = getYearMonth(item.isoDate!!)
                }
            }

        }
    }

    fun showTodayTasks() {
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter is WorkCalendarAdapter) {
                val dateFormat: DateFormat =
                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())// yyyy-MM-dd'T'HH:mmZ
                val nowAsString: String = dateFormat.format(Date())
                adapter.items.firstOrNull {
                    it.isoDate?.startsWith(nowAsString) ?: false
                }?.let { item ->
                    val index = adapter.items.indexOf(item)
                    binding.recyclerViewDay.smoothScrollToPosition(index)
                    adapter.changeSelectedItem(item)
                }
            }
        }
    }

    fun showNextWeek() {
        val lastDayOfWeek = getLastDayOfWeek()
        lastDayOfWeek?.let { workCalendarModel ->
            binding.recyclerViewDay.adapter?.let { adapter ->
                if (adapter is WorkCalendarAdapter) {
                    var lastDayOfWeekIndex = adapter.items.indexOf(workCalendarModel)
                    if (lastDayOfWeekIndex + 7 >= adapter.items.size)
                        lastDayOfWeekIndex = adapter.items.size
                    binding.recyclerViewDay.smoothScrollToPosition(lastDayOfWeekIndex)
                }
            }
        }
    }

    fun showPastWeek() {
        val firstDayOfWeek = getFirstDayOfWeek()
        firstDayOfWeek?.let { workCalendarModel ->
            binding.recyclerViewDay.adapter?.let { adapter ->
                if (adapter is WorkCalendarAdapter) {
                    var lastDayOfWeekIndex = adapter.items.indexOf(workCalendarModel)
                    if (lastDayOfWeekIndex - 7 < 0)
                        lastDayOfWeekIndex = adapter.items.size
                    binding.recyclerViewDay.smoothScrollToPosition(lastDayOfWeekIndex)
                }
            }
        }
    }

    fun getFirstDayOfWeek(): WorkCalendarModel? {
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter is WorkCalendarAdapter) {
                val findFirstCompletelyVisibleItemPosition =
                    (binding.recyclerViewDay.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                return adapter.items[findFirstCompletelyVisibleItemPosition]
            }
        }
        return null
    }

    fun getLastDayOfWeek(): WorkCalendarModel? {
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter is WorkCalendarAdapter) {
                val findLastCompletelyVisibleItemPosition =
                    (binding.recyclerViewDay.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                return adapter.items[findLastCompletelyVisibleItemPosition]
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
            (binding.recyclerViewDay.layoutParams as MarginLayoutParams).setMargins(
                0,
                dayListMarginTop.toInt(),
                0,
                0
            )
            weekCalendarBackgroundStart?.let { binding.imageViewStart.background = it }
            weekCalendarBackgroundEnd?.let { binding.imageViewEnd.background = it }
            weekCalendarSrcStart?.let { binding.imageViewStart.setImageDrawable(it) }
            weekCalendarSrcEnd?.let { binding.imageViewEnd.setImageDrawable(it) }
        }
        binding.recyclerViewDay.setHasFixedSize(true)
        context?.let {
            binding.recyclerViewDay.adapter =
                WorkCalendarAdapter(context = it)
        }
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerViewDay)
    }

    private fun initializeListeners() {
        binding.imageViewStart.setOnClickListener {
            println("imageViewStart >> setOnClickListener")
            showPastWeek()
            onClickListenerPersianCalendarLibrary?.onPersianCalendarLibraryStartClicked()
        }
        binding.imageViewEnd.setOnClickListener {
            println("imageViewEnd >> setOnClickListener")
            showNextWeek()
            onClickListenerPersianCalendarLibrary?.onPersianCalendarLibraryEndClicked()
        }
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter is WorkCalendarAdapter) adapter.onItemClickListener =
                object : AbstractAdapter.OnItemClickListener<WorkCalendarModel> {
                    override fun onClicked(actionId: Int, position: Int, item: WorkCalendarModel) {
                        onClickListenerPersianCalendarLibrary?.onPersianCalendarLibraryClicked(item)
                    }
                }
        }
        binding.recyclerViewDay.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                println("OnScrollListener >> onScrollStateChanged >> newState : $newState")
                if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                    onClickListenerPersianCalendarLibrary?.onPersianCalendarLibraryScrolled(
                        firstWorkCalendarModel = getFirstDayOfWeek(),
                        lastWorkCalendarModel = getLastDayOfWeek()
                    )
                }
            }
        })
    }

    private fun getYearMonth(isoDate: String): String {
        var persianYearMonth = ""
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        format.parse(isoDate)?.let { date ->
            persianYearMonth = PersianCalendarWrapper(date.time).getPersianYearMonth()
        }
        return persianYearMonth
    }

    private fun adaptToView(workCalendarModelList: MutableList<WorkCalendarModel>) {
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
                adapter.notifyDataSetChanged()
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
                return PersianCalendarWrapper(dateItem.time).getPersianDate()
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
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())// yyyy-MM-dd'T'HH:mmZ
        val nowAsString: String = dateFormat.format(Date())
        return isoDate.startsWith(nowAsString)
    }

}

/*adapter.items.forEachIndexed { index, workCalendarModel ->
   if(workCalendarModel.isSelected) {
       when(workCalendarModel.dayOfWeek){
           context.getString(R.string.saturday_persian_calendar_library)->{

           }
           context.getString(R.string.sunday_persian_calendar_library)->{

           }
           context.getString(R.string.monday_persian_calendar_library)->{

           }
           context.getString(R.string.tuesday_persian_calendar_library)->{

           }
           context.getString(R.string.wednesday_persian_calendar_library)->{

           }
           context.getString(R.string.thursday_persian_calendar_library)->{

           }
           context.getString(R.string.friday_persian_calendar_library)->{

           }
       }
   }
}*/