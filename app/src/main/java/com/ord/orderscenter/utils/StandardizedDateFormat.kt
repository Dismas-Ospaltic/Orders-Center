package com.ord.orderscenter.utils



import android.icu.text.SimpleDateFormat
import java.util.*

fun standardizedDateFormat(timestamp: Long): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(Date(timestamp))
}