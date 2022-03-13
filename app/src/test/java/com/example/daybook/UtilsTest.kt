package com.example.daybook

import com.example.daybook.presentation.Utils
import org.junit.Assert.assertEquals
import org.junit.Test


class UtilsTest {

    @Test
    fun shouldReturnCorrectHour(){
        val actual = Utils.getFormattedHour("13:34")
        val expected = 13
        assertEquals(expected,actual)
    }

    @Test
    fun shouldReturnCorrectMinute(){
        val actual = Utils.getFormattedMinute("13:34")
        val expected = 34
        assertEquals(expected,actual)
    }
}