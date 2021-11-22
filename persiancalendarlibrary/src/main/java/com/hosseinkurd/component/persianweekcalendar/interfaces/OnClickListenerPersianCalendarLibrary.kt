package com.hosseinkurd.component.persianweekcalendar.interfaces

import com.hosseinkurd.component.persianweekcalendar.models.WorkCalendarModel

interface OnClickListenerPersianCalendarLibrary {
    fun onPersianCalendarLibraryStartClicked()
    fun onPersianCalendarLibraryEndClicked()
    fun onPersianCalendarLibraryClicked(workCalendarModel: WorkCalendarModel)
    fun onPersianCalendarLibraryScrolled(firstWorkCalendarModel: WorkCalendarModel?, lastWorkCalendarModel: WorkCalendarModel?)
}