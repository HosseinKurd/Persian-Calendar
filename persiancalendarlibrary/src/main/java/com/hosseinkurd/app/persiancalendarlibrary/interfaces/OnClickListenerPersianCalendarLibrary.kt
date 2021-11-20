package com.hosseinkurd.app.persiancalendarlibrary.interfaces

import com.hosseinkurd.app.persiancalendarlibrary.models.WorkCalendarModel

interface OnClickListenerPersianCalendarLibrary {
    fun onPersianCalendarLibraryStartClicked()
    fun onPersianCalendarLibraryEndClicked()
    fun onPersianCalendarLibraryClicked(workCalendarModel: WorkCalendarModel)
    fun onPersianCalendarLibraryScrolled(firstWorkCalendarModel: WorkCalendarModel?, lastWorkCalendarModel: WorkCalendarModel?)
}