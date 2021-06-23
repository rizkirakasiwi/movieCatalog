package com.dicoding.moviecatalog.util

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    fun formatDate(value:String?):String?{
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = sdf.parse(value!!)
        val convert = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return convert.format(date!!)
    }
}