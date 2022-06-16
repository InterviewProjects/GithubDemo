package com.ht.githubdemo.utils

import android.view.View

/**
 * Created by ZOMATO on 15,June,2022
 */
object ViewExt {
    fun Boolean?.visibility() = if (this == true) View.VISIBLE else View.GONE
}