package com.hosseinkurd.app.persiancalendarlibrary.models

import android.os.Parcelable
import com.hosseinkurd.app.persiancalendarlibrary.enums.EnumWorkCalendarState
import com.hosseinkurd.app.persiancalendarlibrary.utils.PersianCalendarWrapper
import com.hosseinkurd.app.persiancalendarlibrary.utils.twoDigitsPersianCalendarLibrary
import kotlinx.parcelize.Parcelize
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
class WorkCalendarModel(
    var id: String = "",
    var workCalendarState
    : EnumWorkCalendarState = EnumWorkCalendarState.NORMAL_DAY,
    var isoDate: String? = null,
    var persianDate: String? = null,
    var isSelected: Boolean = false,
) : Parcelable {
    var dayOfMonth: String? = null
    var dayOfWeek: String? = null

    init {
        isoDate?.let { isoDateAndTime ->
            val calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault())
            calendar.firstDayOfWeek = Calendar.SATURDAY
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
            try {
                val date: Date? = dateFormat.parse(isoDateAndTime)
                date?.let { dateAndTime ->
                    calendar.time = dateAndTime
                    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SSS", Locale.getDefault())
                    format.parse(isoDate)?.let { date ->
                        dayOfMonth = PersianCalendarWrapper(date.time).getPersianDay().twoDigitsPersianCalendarLibrary()
                    }
                    dayOfWeek = when (calendar.get(Calendar.DAY_OF_WEEK)) {
                        Calendar.SATURDAY -> "شنبه"
                        Calendar.SUNDAY -> "یکشنبه"
                        Calendar.MONDAY -> "دوشنبه"
                        Calendar.TUESDAY -> "سه شنبه"
                        Calendar.WEDNESDAY -> "چهارشنبه"
                        Calendar.THURSDAY -> "پنجشنبه"
                        else -> "جمعه"
                    }
                }
            } catch (e: ParseException) {
                e.printStackTrace()
                dayOfMonth = "N/A"
                dayOfWeek = "N/A"
            }
        }
    }

    override fun toString(): String {
        return "{workCalendarState : $workCalendarState , isoDate: $isoDate , persianDate: $persianDate , dayOfMonth: $dayOfMonth , dayOfWeek: $dayOfWeek}"
    }
}