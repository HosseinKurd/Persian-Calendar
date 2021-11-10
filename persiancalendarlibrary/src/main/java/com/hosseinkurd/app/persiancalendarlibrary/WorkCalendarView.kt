package com.hosseinkurd.app.persiancalendarlibrary

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.hosseinkurd.app.persiancalendarlibrary.adapters.WorkCalendarAdapter
import com.hosseinkurd.app.persiancalendarlibrary.databinding.WorkCalendarViewBinding
import com.hosseinkurd.app.persiancalendarlibrary.enums.EnumWorkCalendarState
import com.hosseinkurd.app.persiancalendarlibrary.interfaces.OnClickListenerPersianCalendarLibrary
import com.hosseinkurd.app.persiancalendarlibrary.models.WorkCalendarModel

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
    private val tag = javaClass.simpleName
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
            binding.recyclerView.adapter =
                WorkCalendarAdapter(context = it, items = list)
        }
    }

    private fun initializeView() {
        binding.recyclerView.setHasFixedSize(true)
    }

    private fun initializeListeners() {
        binding.imageViewStart.setOnClickListener {
            onClickListenerPersianCalendarLibrary?.onPersianCalendarLibraryClicked(it.id, null)
        }
        binding.imageViewEnd.setOnClickListener {
            onClickListenerPersianCalendarLibrary?.onPersianCalendarLibraryClicked(it.id, null)
        }
    }

}