package com.hosseinkurd.app.persiancalendarlibrary.enums

enum class EnumWorkCalendarState(val state: Int) {
    NORMAL_DAY(0),
    RESERVED_DAY(1),
    TODAY(2);

    companion object {
        fun getByValue(value: Int): EnumWorkCalendarState {
            return when (value) {
                NORMAL_DAY.state -> NORMAL_DAY
                RESERVED_DAY.state -> RESERVED_DAY
                TODAY.state -> TODAY
                else -> NORMAL_DAY
            }
        }
    }
}