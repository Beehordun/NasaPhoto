package com.example.nasaphoto.util

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Calendar

object DateUtil {
    fun isDateGreaterThanNow(date: String): Boolean {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val selectedDate = dateFormat.parse(date)!!.time
        val nowInMs = Calendar.getInstance(Locale.getDefault()).timeInMillis
        return selectedDate > nowInMs
    }
}
