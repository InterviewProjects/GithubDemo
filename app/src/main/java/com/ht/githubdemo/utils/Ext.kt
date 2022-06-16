package com.ht.githubdemo.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by ZOMATO on 15,June,2022
 */
object Ext {
    fun Any?.toGsonString() = if (this == null) null else Gson().toJson(this)

    inline fun <reified T> String?.toObject() =
        try {
            when {
                this == null -> {
                    null
                }
                T::class == String::class -> {
                    this as T
                }
                else -> {
                    Gson().fromJson<T>(this, object : TypeToken<T?>() {}.type)
                }
            }
        } catch (ex: Exception) {
            null
        }
}