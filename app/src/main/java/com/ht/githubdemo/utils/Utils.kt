package com.ht.githubdemo.utils

import android.annotation.SuppressLint
import com.ht.githubdemo.BuildConfig
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ZOMATO on 15,June,2022
 */
object Utils {
    const val INPUT_DATE_FORMAT_TYPE = "yyyy-MM-dd'T'HH:mm:ss"
    const val OUTPUT_DATE_FORMAT_TYPE = "dd MMM yy, hh:mm aa"

    @SuppressLint("SimpleDateFormat")
    fun getDate(dateStr: String?, inputDateFormat: String, outputDateFormat: String): String? {
        return try {
            val originalFormat = SimpleDateFormat(inputDateFormat, Locale.ENGLISH)
            originalFormat.timeZone = TimeZone.getTimeZone("UTC")
            val date = originalFormat.parse(dateStr!!)

            val targetFormat = SimpleDateFormat(outputDateFormat)
            targetFormat.timeZone = TimeZone.getDefault()
            val formattedDate = targetFormat.format(date!!)

            formattedDate
        } catch (ex: Exception) {
            if (BuildConfig.DEBUG) ex.printStackTrace()
            null
        }
    }

}