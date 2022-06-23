package com.ht.githubdemo.widget.widgetloadmore

import android.content.Context
import android.view.LayoutInflater
import com.ht.githubdemo.databinding.ItemWidgetLoadMoreBinding
import com.ht.githubdemo.widget.base.WidgetVM
import com.ht.githubdemo.widget.base.WidgetView

/**
 * Created by ZOMATO on 15,June,2022
 */
class WidgetLoadMoreView constructor(context: Context): WidgetView<WidgetLoadMoreConfig, WidgetVM>(context) {
    private val binding: ItemWidgetLoadMoreBinding by lazy {
        ItemWidgetLoadMoreBinding.inflate(LayoutInflater.from(context), this, false)
    }

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        addView(binding.root)
    }
}