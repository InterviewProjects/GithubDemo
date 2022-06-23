package com.ht.githubdemo.home

import com.ht.githubdemo.base.BaseIntr
import com.ht.githubdemo.pojos.ClosePRItem
import com.ht.githubdemo.widget.base.Widget
import com.ht.githubdemo.widget.widgetprItem.WidgetPRItemConfig
import javax.inject.Inject

/**
 * Created by ZOMATO on 15,June,2022
 */
class HomeIntr @Inject constructor(): BaseIntr() {
    fun getWidgets(closedPRs: List<ClosePRItem>?): List<Widget<*, *>> {
        val returnValue = mutableListOf<Widget<*, *>>()

        for (prItem in closedPRs ?: listOf()) {
            returnValue.add(
                Widget(
                    config = WidgetPRItemConfig(prItem = prItem),
                    vm = null
                )
            )
        }

        return returnValue
    }
}