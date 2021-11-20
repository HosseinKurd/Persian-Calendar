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
    var workCalendarState: EnumWorkCalendarState = EnumWorkCalendarState.NORMAL_DAY,
    var isoDate: String? = null,
    var persianDate: String? = null,
    var isSelected: Boolean = false,
    var dayOfMonth: String? = null,
    var dayOfWeek: String? = null
) : Parcelable {

    override fun toString(): String {
        return "{workCalendarState : $workCalendarState , isoDate: $isoDate , persianDate: $persianDate , dayOfMonth: $dayOfMonth , dayOfWeek: $dayOfWeek}"
    }
}