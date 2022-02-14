package com.example.daybook

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun getFormattedDate(date: Date?): String? {
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return simpleDateFormat.format(date)
    }

    fun getFormattedHour(time: String): Int {
        val simpleDateFormat = time.substringBefore(':')
        return simpleDateFormat.toInt()
    }

    fun getFormattedMinute(time: String): Int {
        val simpleDateFormat = time.substringAfter(':')
        return simpleDateFormat.toInt()
    }

    fun getDate(s: String): String {
        try {
            val sdf = SimpleDateFormat("dd-MM-yyyy")
            val netDate = java.sql.Date(s.toLong())
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }

    fun getTime(s: String): String {
        try {
            val sdf = SimpleDateFormat("HH:mm")
            val netDate = java.sql.Date(s.toLong())
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }


}