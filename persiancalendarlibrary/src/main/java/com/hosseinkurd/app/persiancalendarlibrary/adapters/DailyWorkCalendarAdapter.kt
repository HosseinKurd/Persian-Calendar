package com.hosseinkurd.app.persiancalendarlibrary.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hosseinkurd.app.persiancalendarlibrary.R
import com.hosseinkurd.app.persiancalendarlibrary.databinding.ItemDailyWorkCalendarBinding
import com.hosseinkurd.app.persiancalendarlibrary.models.DailyWorkCalendarModel
class DailyWorkCalendarAdapter(
    context: Context?, items: MutableList<DailyWorkCalendarModel>,
) : AbstractAdapter<DailyWorkCalendarAdapter.ViewHolderDailyWorkCalendarReserved, DailyWorkCalendarModel>(
    context!!,
    items
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DailyWorkCalendarAdapter.ViewHolderDailyWorkCalendarReserved {
        val binding: ItemDailyWorkCalendarBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_daily_work_calendar,
            parent,
            false
        )
        return ViewHolderDailyWorkCalendarReserved(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderDailyWorkCalendarReserved, position: Int) {
        holder.onFill(position = position)
    }


    inner class ViewHolderDailyWorkCalendarReserved(val binding: ItemDailyWorkCalendarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onFill(position: Int) {
            binding.dailyWorkCalendarModel = getItem(position = position)
            Glide.with(binding.root.context)
                .load(getItem(position = position).avatar)
                .placeholder(R.drawable.ic_user_default_persian_calendar_library)
                .fallback(R.drawable.ic_user_default_persian_calendar_library)
                .fallback(R.drawable.ic_user_default_persian_calendar_library)
                .error(R.drawable.ic_user_default_persian_calendar_library)
                .circleCrop()
                .into(binding.imageViewAvatar)
            binding.constraintLayoutParent.setOnClickListener {
                onItemClickListener?.onClicked(
                    actionId = it.id,
                    position = position,
                    item = getItem(position = position)
                )
            }
        }
    }
}
