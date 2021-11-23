package com.hosseinkurd.component.persianweekcalendar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hosseinkurd.component.persianweekcalendar.R
import com.hosseinkurd.component.persianweekcalendar.databinding.ItemWorkCalendarNormalBinding
import com.hosseinkurd.component.persianweekcalendar.databinding.ItemWorkCalendarReservedBinding
import com.hosseinkurd.component.persianweekcalendar.databinding.ItemWorkCalendarSelectedBinding
import com.hosseinkurd.component.persianweekcalendar.enums.EnumWorkCalendarState
import com.hosseinkurd.component.persianweekcalendar.models.WorkCalendarModel

class WorkCalendarAdapter(
    context: Context?,
    val containerWidth: Int,
) : AbstractAdapter<WorkCalendarAdapter.AbstractViewHolder, WorkCalendarModel>(
    context!!,
) {

    override fun getItemViewType(position: Int): Int {
        return items[position].workCalendarState.state
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WorkCalendarAdapter.AbstractViewHolder {
        return when (viewType) {
            EnumWorkCalendarState.RESERVED_DAY.state -> {
                val binding: ItemWorkCalendarReservedBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_work_calendar_reserved,
                    parent,
                    false
                )
                ViewHolderWorkCalendarReserved(binding)
            }
            EnumWorkCalendarState.TODAY.state -> {
                val binding: ItemWorkCalendarSelectedBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_work_calendar_selected,
                    parent,
                    false
                )
                ViewHolderWorkCalendarSelected(binding)
            }
            else -> {
                val binding: ItemWorkCalendarNormalBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_work_calendar_normal,
                    parent,
                    false
                )
                ViewHolderWorkCalendarNormal(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: AbstractViewHolder, position: Int) {
        holder.onFill(position = position)
    }

    fun changeSelectedItem(
        newItem: WorkCalendarModel,
    ) {
        items.firstOrNull { it.isSelected }
            ?.let {
                it.isSelected = false
                notifyItemChanged(items.indexOf(it))
            }
        newItem.isSelected = true
        notifyItemChanged(items.indexOf(newItem))
    }

    fun changeItemState(
        isoDate: String,
        workCalendarState: EnumWorkCalendarState = EnumWorkCalendarState.NORMAL_DAY
    ) {
        items.firstOrNull { it.isoDate == isoDate }
            ?.let {
                it.workCalendarState = workCalendarState
                notifyItemChanged(items.indexOf(it))
            }
    }

    inner class ViewHolderWorkCalendarNormal(private val binding: ItemWorkCalendarNormalBinding) :
        AbstractViewHolder(binding) {
        init {
            binding.constraintLayoutParent.updateLayoutParams { width = containerWidth }
        }

        override fun onFill(position: Int) {
            if (getItem(position = position).isSelected) {
                binding.viewUnderline.setBackgroundResource(R.drawable.background_underline_blue_dark)
            } else binding.viewUnderline.setBackgroundResource(0)
            binding.workCalendarModel = getItem(position = position)
            binding.constraintLayoutParent.setOnClickListener {
                onItemClickListener?.onClicked(
                    actionId = it.id,
                    position = position,
                    item = items[position]
                )
            }
        }
    }

    inner class ViewHolderWorkCalendarReserved(private val binding: ItemWorkCalendarReservedBinding) :
        AbstractViewHolder(binding) {
        init {
            binding.constraintLayoutParent.updateLayoutParams { width = containerWidth }
        }

        override fun onFill(position: Int) {
            if (getItem(position = position).isSelected) {
                binding.viewUnderline.setBackgroundResource(R.drawable.background_underline_blue_dark)
            } else binding.viewUnderline.setBackgroundResource(0)
            binding.workCalendarModel = getItem(position = position)
            binding.constraintLayoutParent.setOnClickListener {
                onItemClickListener?.onClicked(
                    actionId = it.id,
                    position = position,
                    item = items[position]
                )
            }
        }
    }

    inner class ViewHolderWorkCalendarSelected(private val binding: ItemWorkCalendarSelectedBinding) :
        AbstractViewHolder(binding) {
        init {
            binding.constraintLayoutParent.updateLayoutParams { width = containerWidth }
        }

        override fun onFill(position: Int) {
            if (getItem(position = position).isSelected) {
                binding.viewUnderline.setBackgroundResource(R.drawable.background_underline_blue_dark)
            } else binding.viewUnderline.setBackgroundResource(0)
            binding.workCalendarModel = getItem(position = position)
            binding.constraintLayoutParent.setOnClickListener {
                onItemClickListener?.onClicked(
                    actionId = it.id,
                    position = position,
                    item = items[position]
                )
            }
        }
    }

    abstract inner class AbstractViewHolder(binding: androidx.databinding.ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun onFill(position: Int)
    }
}