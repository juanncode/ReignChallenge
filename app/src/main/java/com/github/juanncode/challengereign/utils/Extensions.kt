package com.github.juanncode.challengereign.utils

import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.*


fun convertDate(date: String): String {
    if (date.isEmpty()) return ""
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    val convertDate = dateFormat.parse(date)

    return PrettyTime().format(convertDate)
}