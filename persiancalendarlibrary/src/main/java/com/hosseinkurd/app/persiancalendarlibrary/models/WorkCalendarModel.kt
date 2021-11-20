package com.hosseinkurd.app.persiancalendarlibrary.models

import android.os.Parcelable
import com.hosseinkurd.app.persiancalendarlibrary.enums.EnumWorkCalendarState
import kotlinx.parcelize.Parcelize

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as WorkCalendarModel
        if (id != other.id) return false
        if (isoDate != other.isoDate) return false
        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (isoDate?.hashCode() ?: 0)
        return result
    }
}