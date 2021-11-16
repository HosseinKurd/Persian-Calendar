package com.hosseinkurd.app.persiancalendarlibrary

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.hosseinkurd.app.persiancalendarlibrary.adapters.AbstractAdapter
import com.hosseinkurd.app.persiancalendarlibrary.adapters.DailyWorkCalendarAdapter
import com.hosseinkurd.app.persiancalendarlibrary.adapters.WorkCalendarAdapter
import com.hosseinkurd.app.persiancalendarlibrary.databinding.WorkCalendarViewBinding
import com.hosseinkurd.app.persiancalendarlibrary.enums.EnumWorkCalendarState
import com.hosseinkurd.app.persiancalendarlibrary.interfaces.OnClickListenerPersianCalendarLibrary
import com.hosseinkurd.app.persiancalendarlibrary.models.WorkCalendarModel
import com.hosseinkurd.app.persiancalendarlibrary.utils.PersianCalendarWrapper
import java.text.DateFormat
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
        initializeView()
        initializeListeners()
    }

    fun addItems(list: MutableList<WorkCalendarModel>) {
        context?.let {
            binding.recyclerViewDay.adapter?.let { adapter ->
                if (adapter is WorkCalendarAdapter) {
                    adapter.items.addAll(list)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    fun showSelectedDayTasks() {
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter is WorkCalendarAdapter) {
                adapter.items.firstOrNull {
                    it.workCalendarState == EnumWorkCalendarState.SELECTED_DAY
                }?.let { item ->
                    val index = adapter.items.indexOf(item)
                    binding.recyclerViewDay.smoothScrollToPosition(index)
                    binding.textViewTitle.text = getYearMonth(item.isoDate!!)
                    context?.let {
                        binding.recyclerViewTask.adapter =
                            DailyWorkCalendarAdapter(it, item.dailyWorkCalendarModels)
                    }
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
                    context?.let {
                        binding.recyclerViewTask.adapter =
                            DailyWorkCalendarAdapter(it, item.dailyWorkCalendarModels)
                    }
                }
            }
        }
    }

    private fun getYearMonth(isoDate: String): String {
        var persianYearMonth = ""
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSS", Locale.getDefault())
        format.parse(isoDate)?.let { date ->
            persianYearMonth = PersianCalendarWrapper(date.time).getPersianYearMonth()
        }
        return persianYearMonth
    }

    private fun initializeView() {
        binding.recyclerViewDay.setHasFixedSize(true)
        context?.let {
            binding.recyclerViewDay.adapter =
                WorkCalendarAdapter(context = it)
            binding.recyclerViewTask.addItemDecoration(
                DividerItemDecoration(
                    it,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerViewDay)
    }

    private fun initializeListeners() {
        binding.imageViewStart.setOnClickListener {
            onClickListenerPersianCalendarLibrary?.onPersianCalendarLibraryStartClicked()
        }
        binding.imageViewEnd.setOnClickListener {
            onClickListenerPersianCalendarLibrary?.onPersianCalendarLibraryEndClicked()
        }
        binding.recyclerViewDay.adapter?.let { adapter ->
            if (adapter is WorkCalendarAdapter) adapter.onItemClickListener =
                object : AbstractAdapter.OnItemClickListener<WorkCalendarModel> {
                    override fun onClicked(actionId: Int, position: Int, item: WorkCalendarModel) {
                        binding.textViewTitle.text = getYearMonth(item.isoDate!!)
                        context?.let {
                            binding.recyclerViewTask.adapter =
                                DailyWorkCalendarAdapter(it, item.dailyWorkCalendarModels)
                        }
                    }
                }
        }
    }

}