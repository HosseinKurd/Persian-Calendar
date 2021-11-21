package com.hosseinkurd.app.persiancalendarlibrary.models

data class CalendarDayModel(val isoDate: String, val isReserved: Boolean = false) {
    override fun toString(): String {
        return "{isoDate : $isoDate , isReserved : $isReserved}"
    }
}