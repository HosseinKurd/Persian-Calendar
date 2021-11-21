package com.hosseinkurd.app.persiancalendarlibrary.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object PublicFunctions {
    fun getCalendarFromISO(dateString: String, pattern: String): Calendar? {
        val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        try {
            dateFormat.parse(dateString)?.let { date ->
                val calendar: Calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault())
                calendar.time = date
                calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1)
                return calendar
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }
}