package com.hosseinkurd.app.persiancalendarlibrary.models

import android.os.Parcelable
import android.text.SpannableStringBuilder
import kotlinx.parcelize.Parcelize

@Parcelize
data class DailyWorkCalendarModel(
    var id: String,
    var avatar: String,
    var name: String,
    var type: String,
    var time: String,
    var durationFrom: String,
    var durationTo: String,
) : Parcelable{
    var duration: SpannableStringBuilder? = null

    init {

    }

    override fun toString(): String {
        return "id : $id , avatar : $avatar , name: $name , type: $type , time: $time , duration: $duration"
    }
}