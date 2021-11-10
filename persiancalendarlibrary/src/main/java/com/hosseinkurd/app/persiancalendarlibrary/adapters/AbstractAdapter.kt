package com.hosseinkurd.app.persiancalendarlibrary.adapters

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Kurdia.Epic on 5/12/2018.
 */
abstract class AbstractAdapter<VH : RecyclerView.ViewHolder?, T> : RecyclerView.Adapter<VH> {

    protected val tag = javaClass.simpleName

    /**
     * Get T typed item [List]
     * @return T typed item [List]
     */
    var items: MutableList<T>
    protected var inflater: LayoutInflater
    var onItemClickListener: OnItemClickListener<T>? = null
    var onAdapterItemLongPressListener: OnAdapterItemLongPressListener<T>? = null

    constructor(context: Context, items: MutableList<T>) : super() {
        inflater = LayoutInflater.from(context)
        this.items = items
    }

    constructor(context: Context) : this(context = context, items = mutableListOf())

    /**
     * @param position Index of target item
     * @return T type
     */
    fun getItem(position: Int): T = items[position]

    fun addItem(item: T) {
        items.add(item)
    }

    fun addItem(index: Int, item: T) {
        items.add(index, item)
    }

    /**
     * Get size of Items
     * @return Item size
     */
    override fun getItemCount(): Int = items.size

    /**
     * Item Click Listener callback Interface
     * @param <T> Type of item Extends [Object]
    </T> */
    interface OnItemClickListener<T> {
        fun onClicked(actionId: Int, position: Int, item: T)
    }

    interface OnAdapterItemLongPressListener<T> {
        fun onAdapterItemLongPressed(actionId: Int, position: Int, item: T)
    }
}