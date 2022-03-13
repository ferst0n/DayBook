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

    fun getFormattedTime(time: String):String {
        val formattedTime = time + ":" + "0"
        return formattedTime
    }

    fun getFormattedDateView(s: String): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd")
            val outputFormat = SimpleDateFormat("dd MMMM yyyy")
            val objDate :Date = inputFormat.parse(s)
            outputFormat.format(objDate)
        } catch (e: Exception) {
            e.toString()
        }
    }

    fun getDate(s: String): String {
        return try {
            val sdf = SimpleDateFormat("dd-MM-yyyy")
            val netDate = java.sql.Date(s.toLong())
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }

    fun getTime(s: String): String {
        return try {
            val sdf = SimpleDateFormat("HH:mm")
            val netDate = java.sql.Date(s.toLong())
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }


}