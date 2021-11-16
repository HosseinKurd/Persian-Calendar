package com.hosseinkurd.app.persiancalendarlibrary.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hosseinkurd.app.persiancalendarlibrary.R
import com.hosseinkurd.app.persiancalendarlibrary.databinding.ItemWorkCalendarNormalBinding
import com.hosseinkurd.app.persiancalendarlibrary.databinding.ItemWorkCalendarReservedBinding
import com.hosseinkurd.app.persiancalendarlibrary.databinding.ItemWorkCalendarSelectedBinding
import com.hosseinkurd.app.persiancalendarlibrary.enums.EnumWorkCalendarState
import com.hosseinkurd.app.persiancalendarlibrary.models.WorkCalendarModel

class WorkCalendarAdapter(
    context: Context?,
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
            EnumWorkCalendarState.SELECTED_DAY.state -> {
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

    inner class ViewHolderWorkCalendarNormal(private val binding: ItemWorkCalendarNormalBinding) :
        AbstractViewHolder(binding) {
        override fun onFill(position: Int) {
            println("Normal >> items : ${items[position]} , position: $position")
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
        override fun onFill(position: Int) {
            println("Reserved >> items : ${items[position]} , position: $position")
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
        override fun onFill(position: Int) {
            println("Selected >> items : ${items[position]} , position: $position")
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