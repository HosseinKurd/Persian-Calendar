package com.hosseinkurd.app.persiancalendarlibrary

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout

class WorkCalendarView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout
    (
    context,
    attributeSet,
    defStyleAttr
){
    private val tag = javaClass.simpleName
    
    init {
        val inflater =
            (getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
        val view = inflater.inflate(R.layout.work_calendar_view, this, true)
        initializeView()
        initializeListeners()
    }

    private fun initializeView() {

    }

    private fun initializeListeners() {

    }

}