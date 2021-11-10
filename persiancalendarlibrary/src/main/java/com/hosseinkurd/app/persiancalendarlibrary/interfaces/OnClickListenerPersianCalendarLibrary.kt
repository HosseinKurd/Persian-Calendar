package com.hosseinkurd.app.persiancalendarlibrary.interfaces

import com.hosseinkurd.app.persiancalendarlibrary.models.WorkCalendarModel

interface OnClickListenerPersianCalendarLibrary {
    fun onPersianCalendarLibraryStartClicked()
    fun onPersianCalendarLibraryEndClicked()
    fun onPersianCalendarLibraryClicked(viewId: Int, workCalendarModel: WorkCalendarModel?)
}