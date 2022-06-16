package com.ht.githubdemo.widget.base

/**
 * Created by ZOMATO on 26,May,2022
 */
class Widget<Config: WidgetConfig, VM: WidgetVM>(var config: Config, val vm: VM? = null)