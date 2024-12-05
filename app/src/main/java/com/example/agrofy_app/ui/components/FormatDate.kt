package com.example.agrofy_app.ui.components

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun formatISOToDate(isoString: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = inputFormat.parse(isoString)
        val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        outputFormat.format(date!!)
    } catch (e: Exception) {
        "Invalid Date"
    }
}

fun formatDate(date: Int): String {
    val dateStr = date.toString()
    val parser = SimpleDateFormat("yyyyMMdd", Locale("id"))
    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale("id"))

    return try {
        val parsedDate = parser.parse(dateStr)
        formatter.format(parsedDate as Date)
    } catch (e: Exception) {
        dateStr
    }
}