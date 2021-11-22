package com.hosseinkurd.component.persianweekcalendar.utils

import java.text.DecimalFormat
import java.text.NumberFormat


fun Int.twoDigitsPersianCalendarLibrary(): String {
    val numberFormat: NumberFormat = DecimalFormat("00")
    return numberFormat.format(this)
}

fun Long.twoDigitsPersianCalendarLibrary(): String {
    val numberFormat: NumberFormat = DecimalFormat("00")
    return numberFormat.format(this)
}
