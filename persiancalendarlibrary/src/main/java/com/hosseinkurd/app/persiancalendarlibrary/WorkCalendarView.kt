package com.hosseinkurd.app.persiancalendarlibrary

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.hosseinkurd.app.persiancalendarlibrary.adapters.AbstractAdapter
import com.hosseinkurd.app.persiancalendarlibrary.adapters.WorkCalendarAdapter
import com.hosseinkurd.app.persiancalendarlibrary.databinding.WorkCalendarViewBinding
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
            binding.recyclerView.adapter?.let { adapter ->
                if (adapter is WorkCalendarAdapter) {
                    adapter.items.addAll(list)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun initializeView() {
        binding.recyclerView.setHasFixedSize(true)
        context?.let {
            binding.recyclerView.adapter =
                WorkCalendarAdapter(context = it)
        }
    }

    private fun initializeListeners() {
        binding.imageViewStart.setOnClickListener {
            onClickListenerPersianCalendarLibrary?.onPersianCalendarLibraryStartClicked()
        }
        binding.imageViewEnd.setOnClickListener {
            onClickListenerPersianCalendarLibrary?.onPersianCalendarLibraryEndClicked()
        }
        binding.recyclerView.adapter?.let { adapter ->
            if (adapter is WorkCalendarAdapter) adapter.onItemClickListener =
                object : AbstractAdapter.OnItemClickListener<WorkCalendarModel> {
                    override fun onClicked(actionId: Int, position: Int, item: WorkCalendarModel) {

                    }
                }
        }
    }

}