package com.hosseinkurd.app.persiancalendarlibrary.utils

import java.util.*

/**
 * Created by Kurdia on 4/15/17.
 */
class PersianCalendarWrapper {

    var separator = "-"

    var separatorJalali = "/"

    /**
     * JavaSource_Calendar:
     * The default constructor uses the current Gregorian date to initialize the
     * other private members of the class (Persian and Julian dates).
     */
    constructor() {
        val calendar: Calendar = GregorianCalendar()
        setGregorianDate(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    constructor(milliSeconds: Long) {
        val calendar: Calendar = Calendar.getInstance(Locale.getDefault())
        calendar.timeInMillis = milliSeconds
        setGregorianDate(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        hour = calendar.get(Calendar.HOUR_OF_DAY).toLong()
        minute = calendar.get(Calendar.MINUTE).toLong()
    }

    /**
     * toString:
     * Overrides the default toString() method to return all dates.
     *
     * @return String
     */
    override fun toString(): String {
        return getWeekDayStr() +
                ", Gregorian:[" + getGregorianDate() +
                "], Julian:[" + getJulianDate() +
                "], Persian:[" + getPersianDate() + "]"
    }

    /**
     * JavaSource_Calendar:
     * This constructor receives a Gregorian date and initializes the other private
     * members of the class accordingly.
     *
     * @param date    String
     */
    constructor(date: String) {
        val strY = date.substring(0, date.indexOf(separator))
        val strM = date.substring(date.indexOf(separator) + 1, date.lastIndexOf(separator))
        val strD = date.substring(date.lastIndexOf(separator) + 1, date.length)
        // Log.e("TAG_TAG", "showItems-->Date Year : " + strY + " , Month : " + strM + " , Day : " + strD)
        // val intY = strY.toInt()
        // val intM = strM.toInt()
        // val intD = strD.toInt()
        setGregorianDate(strY.toInt(), strM.toInt(), strD.toInt())
    }

    /**
     * JavaSource_Calendar:
     * This constructor receives a Gregorian date and initializes the other private
     * members of the class accordingly.
     *
     * @param date          String
     * @param separatoro    String
     */
    constructor(date: String, separator: String) {
        this.separator = separator
        val strY = date.substring(0, date.indexOf(separator))
        val strM = date.substring(date.indexOf(separator) + 1, date.lastIndexOf(separator))
        val strD = date.substring(date.lastIndexOf(separator) + 1, date.length)
        // Log.e("TAG_TAG", "showItems-->Date Year : " + strY + " , Month : " + strM + " , Day : " + strD)
        setGregorianDate(strY.toInt(), strM.toInt(), strD.toInt())
    }

    /**
     * JavaSource_Calendar:
     * This constructor receives a Gregorian date and initializes the other private
     * members of the class accordingly.
     *
     * @param year  int
     * @param month int
     * @param day   int
     */
    constructor(year: Int, month: Int, day: Int) {
        setGregorianDate(year, month, day)
    }

    /**
     * getPersianYear:
     * Returns the 'year' part of the Persian date.
     *
     * @return int
     */
    fun getPersianYear(): Int {
        return irYear
    }

    /**
     * getPersianMonth:
     * Returns the 'month' part of the Persian date.
     *
     * @return int
     */
    fun getPersianMonth(): Int {
        return irMonth
    }

    fun getPersianYearMonth(): String {
        return "${getPersianYear()} ${getMonthPersianStr()}"
    }

    /**
     * getPersianDay:
     * Returns the 'day' part of the Persian date.
     *
     * @return int
     */
    fun getPersianDay(): Int {
        return irDay
    }

    /**
     * getGregorianYear:
     * Returns the 'year' part of the Gregorian date.
     *
     * @return int
     */
    fun getGregorianYear(): Int {
        return gYear
    }

    /**
     * getGregorianMonth:
     * Returns the 'month' part of the Gregorian date.
     *
     * @return int
     */
    fun getGregorianMonth(): Int {
        return gMonth
    }

    /**
     * getGregorianDay:
     * Returns the 'day' part of the Gregorian date.
     *
     * @return int
     */
    fun getGregorianDay(): Int {
        return gDay
    }

    /**
     * getJulianYear:
     * Returns the 'year' part of the Julian date.
     *
     * @return int
     */
    fun getJulianYear(): Int {
        return juYear
    }

    /**
     * getJulianMonth:
     * Returns the 'month' part of the Julian date.
     *
     * @return int
     */
    fun getJulianMonth(): Int {
        return juMonth
    }

    /**
     * getJulianDay()
     * Returns the 'day' part of the Julian date.
     *
     * @return int
     */
    fun getJulianDay(): Int {
        return juDay
    }

    /**
     * getPersianDate:
     * Returns a string version of Persian date
     *
     * @return String
     */
    fun getPersianDate(): String {
        return "$irYear$separatorJalali$irMonth$separatorJalali$irDay"
    }

    /**
     * getGregorianDate:
     * Returns a string version of Gregorian date
     *
     * @return String
     */
    fun getGregorianDate(): String {
        return "$gYear$separator$gMonth$separator$gDay"
    }

    /**
     * getJulianDate:
     * Returns a string version of Julian date
     *
     * @return String
     */
    fun getJulianDate(): String {
        return "$juYear$separator$juMonth$separator$juDay"
    }

    /**
     * getWeekDayStr:
     * Returns the week day name.
     *
     * @return String
     */
    fun getWeekDayStr(): String {
        val weekDayStr = arrayOf(
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday"
        )
        return weekDayStr[getDayOfWeek()]
    }

    /**
     * getWeekDayPersian:
     * Returns the week day name in Persianp.
     *
     * @return String
     */
    fun getWeekDayPersian(): String {
        val weekDayStr = arrayOf(
            "دوشنبه",
            "سه شنبه",
            "چهارشنبه",
            "پنجشنبه",
            "جمعه",
            "شنبه",
            "یکشنبه"
        )
        return weekDayStr[getDayOfWeek()]
    }

    fun getMonthPersianStr(): String {
        val months = arrayOf(
            "فروردین",
            "اردیبهشت",
            "خرداد",
            "تیر",
            "مرداد",
            "شهریور",
            "مهر",
            "آبان",
            "آذر",
            "دی",
            "بهمن",
            "اسفند",
        )
        return months[getPersianMonth() - 1]
    }

    /**
     * getDayOfWeek:
     * Returns the week day number. Monday=0..Sunday=6
     *
     * @return int
     */
    fun getDayOfWeek(): Int {
        return julianDayNumber % 7
    }

    fun getTime(): String {
        return "${hour.twoDigitsPersianCalendarLibrary()}:${minute.twoDigitsPersianCalendarLibrary()}"
    }

    fun getTimeAmPm(): String {
        return if (hour < 13)
            "${hour.twoDigitsPersianCalendarLibrary()}:${minute.twoDigitsPersianCalendarLibrary()} ق.ظ"
        else
            "${(hour - 12).twoDigitsPersianCalendarLibrary()}:${minute.twoDigitsPersianCalendarLibrary()} ب.ظ"
    }

    /**
     * nextDay:
     * Go to next julian day number (JulianDayNumber) and adjusts the other dates.
     */
    fun nextDay() {
        julianDayNumber++
        julianDayNumberToPersian()
        julianDayNumberToJulian()
        julianDayNumberToGregorian()
    }

    /**
     * nextDay:
     * Overload the nextDay() method to accept the number of days to go ahead and
     * adjusts the other dates accordingly.
     *
     * @param days int
     */
    fun nextDay(days: Int) {
        julianDayNumber += days
        julianDayNumberToPersian()
        julianDayNumberToJulian()
        julianDayNumberToGregorian()
    }

    /**
     * previousDay:
     * Go to previous julian day number (JulianDayNumber) and adjusts the otehr dates.
     */
    fun previousDay() {
        julianDayNumber--
        julianDayNumberToPersian()
        julianDayNumberToJulian()
        julianDayNumberToGregorian()
    }

    /**
     * previousDay:
     * Overload the previousDay() method to accept the number of days to go backward
     * and adjusts the other dates accordingly.
     *
     * @param days int
     */
    fun previousDay(days: Int) {
        julianDayNumber -= days
        julianDayNumberToPersian()
        julianDayNumberToJulian()
        julianDayNumberToGregorian()
    }

    /**
     * setPersianDate:
     * Sets the date according to the Persian calendar and adjusts the other dates.
     *
     * @param year  int
     * @param month int
     * @param day   int
     */
    fun setPersianDate(year: Int, month: Int, day: Int) {
        irYear = year
        irMonth = month
        irDay = day
        julianDayNumber = persianDateToJulianDayNumber()
        julianDayNumberToPersian()
        julianDayNumberToJulian()
        julianDayNumberToGregorian()
    }

    /**
     * setGregorianDate:
     * Sets the date according to the Gregorian calendar and adjusts the other dates.
     *
     * @param year  int
     * @param month int
     * @param day   int
     */
    fun setGregorianDate(year: Int, month: Int, day: Int) {
        gYear = year
        gMonth = month
        gDay = day
        if (month < 1) {
            gMonth = 1
        }
        if (day < 1) {
            gDay = 1
        }

        julianDayNumber = gregorianDateToJulianDayNumber(year, month, day)
        julianDayNumberToPersian()
        julianDayNumberToJulian()
        julianDayNumberToGregorian()
    }

    /**
     * setJulianDate:
     * Sets the date according to the Julian calendar and adjusts the other dates.
     *
     * @param year  int
     * @param month int
     * @param day   int
     */
    fun setJulianDate(year: Int, month: Int, day: Int) {
        juYear = year
        juMonth = month
        juDay = day
        julianDayNumber = julianDateToJulianDayNumber(year, month, day)
        julianDayNumberToPersian()
        julianDayNumberToJulian()
        julianDayNumberToGregorian()
    }

    /**
     * PersianCalendar:
     * This method determines if the Persian (Jalali) year is leap (366-day long)
     * or is the common year (365 days), and finds the day in March (Gregorian
     * Calendar)of the first day of the Persian year ('irYear').Persian year (irYear)
     * ranges from (-61 to 3177).This method will set the following private data
     * members as follows:
     * leap: Number of years since the last leap year (0 to 4)
     * Gy: Gregorian year of the beginning of Persian year
     * march: The March day of Farvardin the 1st (first day of jaYear)
     */
    private fun persianCalendar() {
        // Persian years starting the 33-year rule
        val Breaks = intArrayOf(
            -61, 9, 38, 199, 426, 686, 756, 818, 1111, 1181,
            1210, 1635, 2060, 2097, 2192, 2262, 2324, 2394, 2456, 3178
        )
        var jm: Int
        var N: Int
        var leapJ: Int
        val leapG: Int
        var jp: Int
        var j: Int
        var jump: Int
        gYear = irYear + 621
        leapJ = -14
        jp = Breaks[0]
        // Find the limiting years for the Persian year 'irYear'
        j = 1
        do {
            jm = Breaks[j]
            jump = jm - jp
            if (irYear >= jm) {
                leapJ += jump / 33 * 8 + jump % 33 / 4
                jp = jm
            }
            j++
        } while (j < 20 && irYear >= jm)
        N = irYear - jp
        // Find the number of leap years from AD 621 to the begining of the current
        // Persian year in the Persian (Jalali) calendar
        leapJ += N / 33 * 8 + (N % 33 + 3) / 4
        if (jump % 33 == 4 && jump - N == 4) leapJ++
        // And the same in the Gregorian date of Farvardin the first
        leapG = gYear / 4 - (gYear / 100 + 1) * 3 / 4 - 150
        march = 20 + leapJ - leapG
        // Find how many years have passed since the last leap year
        if (jump - N < 6) N = N - jump + (jump + 4) / 33 * 33
        leap = ((N + 1) % 33 - 1) % 4
        if (leap == -1) leap = 4
    }

    /**
     * IsLeap:
     * This method determines if the Persian (Jalali) year is leap (366-day long)
     * or is the common year (365 days), and finds the day in March (Gregorian
     * Calendar)of the first day of the Persian year ('irYear').Persian year (irYear)
     * ranges from (-61 to 3177).This method will set the following private data
     * members as follows:
     * leap: Number of years since the last leap year (0 to 4)
     * Gy: Gregorian year of the begining of Persian year
     * march: The March day of Farvardin the 1st (first day of jaYear)
     */
    fun IsLeap(irYear1: Int): Boolean {
        // Persian years starting the 33-year rule
        val Breaks = intArrayOf(
            -61, 9, 38, 199, 426, 686, 756, 818, 1111, 1181,
            1210, 1635, 2060, 2097, 2192, 2262, 2324, 2394, 2456, 3178
        )
        var jm: Int
        var N: Int
        var leapJ: Int
        val leapG: Int
        var jp: Int
        var j: Int
        var jump: Int
        gYear = irYear1 + 621
        leapJ = -14
        jp = Breaks[0]
        // Find the limiting years for the Persian year 'irYear'
        j = 1
        do {
            jm = Breaks[j]
            jump = jm - jp
            if (irYear1 >= jm) {
                leapJ += jump / 33 * 8 + jump % 33 / 4
                jp = jm
            }
            j++
        } while (j < 20 && irYear1 >= jm)
        N = irYear1 - jp
        // Find the number of leap years from AD 621 to the begining of the current
        // Persian year in the Persian (Jalali) calendar
        leapJ += N / 33 * 8 + (N % 33 + 3) / 4
        if (jump % 33 == 4 && jump - N == 4) leapJ++
        // And the same in the Gregorian date of Farvardin the first
        leapG = gYear / 4 - (gYear / 100 + 1) * 3 / 4 - 150
        march = 20 + leapJ - leapG
        // Find how many years have passed since the last leap year
        if (jump - N < 6) N = N - jump + (jump + 4) / 33 * 33
        leap = ((N + 1) % 33 - 1) % 4
        if (leap == -1) leap = 4
        return leap == 4 || leap == 0
    }

    /**
     * PersianDateToJulianDayNumber:
     * Converts a date of the Persian calendar to the Julian Day Number. It first
     * invokes the 'PersianCalender' private method to convert the Persian date to
     * Gregorian date and then returns the Julian Day Number based on the Gregorian
     * date. The Persian date is obtained from 'irYear'(1-3100),'irMonth'(1-12) and
     * 'irDay'(1-29/31).
     *
     * @return long (Julian Day Number)
     */
    private fun persianDateToJulianDayNumber(): Int {
        persianCalendar()
        return gregorianDateToJulianDayNumber(
            gYear,
            3,
            march
        ) + (irMonth - 1) * 31 - irMonth / 7 * (irMonth - 7) + irDay - 1
    }

    /**
     * JulianDayNumberToPersian:
     * Converts the current value of 'JulianDayNumber' Julian Day Number to a date in the
     * Persian calendar. The caller should make sure that the current value of
     * 'JulianDayNumber' is set correctly. This method first converts the JulianDayNumber to Gregorian
     * calendar and then to Persian calendar.
     */
    private fun julianDayNumberToPersian() {
        julianDayNumberToGregorian()
        irYear = gYear - 621
        persianCalendar() // This invocation will updateCode 'leap' and 'march'
        val julianDayNumber1F = gregorianDateToJulianDayNumber(gYear, 3, march)
        var k = julianDayNumber - julianDayNumber1F
        if (k >= 0) {
            if (k <= 185) {
                irMonth = 1 + k / 31
                irDay = k % 31 + 1
                return
            } else k -= 186
        } else {
            irYear--
            k += 179
            if (leap == 1) k++
        }
        irMonth = 7 + k / 30
        irDay = k % 30 + 1
    }

    /**
     * julianDateToJulianDayNumber:
     * Calculates the julian day number (JulianDayNumber) from Julian calendar dates. This
     * integer number corresponds to the noon of the date (i.e. 12 hours of
     * Universal Time). This method was tested to be good (valid) since 1 March,
     * -100100 (of both calendars) up to a few millions (10^6) years into the
     * future. The algorithm is based on D.A.Hatcher, Q.Jl.R.Astron.Soc. 25(1984),
     * 53-55 slightly modified by K.M. Borkowski, Post.Astron. 25(1987), 275-279.
     *
     * @param year  int
     * @param month int
     * @param day   int
     * @return int
     */
    private fun julianDateToJulianDayNumber(year: Int, month: Int, day: Int): Int {
        return (year + (month - 8) / 6 + 100100) * 1461 / 4 + (153 * ((month + 9) % 12) + 2) / 5 + day - 34840408
    }

    /**
     * JulianDayNumberToJulian:
     * Calculates Julian calendar dates from the julian day number (JulianDayNumber) for the
     * period since JulianDayNumber=-34839655 (i.e. the year -100100 of both calendars) to
     * some millions (10^6) years ahead of the present. The algorithm is based on
     * D.A. Hatcher, Q.Jl.R.Astron.Soc. 25(1984), 53-55 slightly modified by K.M.
     * Borkowski, Post.Astron. 25(1987), 275-279).
     */
    private fun julianDayNumberToJulian() {
        val j = 4 * julianDayNumber + 139361631
        val i = j % 1461 / 4 * 5 + 308
        juDay = i % 153 / 5 + 1
        juMonth = i / 153 % 12 + 1
        juYear = j / 1461 - 100100 + (8 - juMonth) / 6
    }

    /**
     * gregorianDateToJulianDayNumber:
     * Calculates the julian day number (JulianDayNumber) from Gregorian calendar dates. This
     * integer number corresponds to the noon of the date (i.e. 12 hours of
     * Universal Time). This method was tested to be good (valid) since 1 March,
     * -100100 (of both calendars) up to a few millions (10^6) years into the
     * future. The algorithm is based on D.A.Hatcher, Q.Jl.R.Astron.Soc. 25(1984),
     * 53-55 slightly modified by K.M. Borkowski, Post.Astron. 25(1987), 275-279.
     *
     * @param year  int
     * @param month int
     * @param day   int
     * @return int
     */
    private fun gregorianDateToJulianDayNumber(year: Int, month: Int, day: Int): Int {
        var JulianDayNumber =
            (year + (month - 8) / 6 + 100100) * 1461 / 4 + (153 * ((month + 9) % 12) + 2) / 5 + day - 34840408
        JulianDayNumber = JulianDayNumber - (year + 100100 + (month - 8) / 6) / 100 * 3 / 4 + 752
        return JulianDayNumber
    }

    /**
     * JulianDayNumberToGregorian:
     * Calculates Gregorian calendar dates from the julian day number (JulianDayNumber) for
     * the period since JulianDayNumber=-34839655 (i.e. the year -100100 of both calendars) to
     * some millions (10^6) years ahead of the present. The algorithm is based on
     * D.A. Hatcher, Q.Jl.R.Astron.Soc. 25(1984), 53-55 slightly modified by K.M.
     * Borkowski, Post.Astron. 25(1987), 275-279).
     */
    private fun julianDayNumberToGregorian() {
        var j = 4 * julianDayNumber + 139361631
        j += ((4 * julianDayNumber + 183187720) / 146097 * 3 / 4 * 4 - 3908)
        val i = j % 1461 / 4 * 5 + 308
        gDay = i % 153 / 5 + 1
        gMonth = i / 153 % 12 + 1
        gYear = j / 1461 - 100100 + (8 - gMonth) / 6
    }

    private fun setTime(hour: Long, minute: Long) {
        this.hour = hour
        this.minute = minute
    }

    private var irYear // Year part of a Persian date
            = 0
    private var irMonth // Month part of a Persian date
            = 0
    private var irDay // Day part of a Persian date
            = 0
    private var gYear // Year part of a Gregorian date
            = 0
    private var gMonth // Month part of a Gregorian date
            = 0
    private var gDay // Day part of a Gregorian date
            = 0
    private var juYear // Year part of a Julian date
            = 0
    private var juMonth // Month part of a Julian date
            = 0
    private var juDay // Day part of a Julian date
            = 0
    private var leap // Number of years since the last leap year (0 to 4)
            = 0
    private var julianDayNumber // Julian Day Number
            = 0
    private var march // The march day of Farvardin the first (First day of jaYear)
            = 0
    private var hour: Long = 0
    private var minute: Long = 0
}