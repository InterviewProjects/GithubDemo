package com.ht.githubdemo.base

import android.content.Context
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ht.githubdemo.widget.base.*

/**
 * Created by ZOMATO on 15,June,2022
 */

@Suppress("UNCHECKED_CAST")
class BaseAdapter(private val lifecycleOwner: LifecycleOwner): ListAdapter<Widget<*, *>, WidgetVH<*, *, *>>(DiffCallback()) {
    private val map = mutableMapOf<Int, (Context) -> WidgetView<*, *>>()
    override fun getItemViewType(position: Int) = getItem(position).config.viewType()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WidgetVH<*, *, *> {
        return WidgetVH(
            map[viewType]?.invoke(parent.context)!!.apply {
                this.lifecycleOwner = this@BaseAdapter.lifecycleOwner
            }
        )
    }

    override fun onBindViewHolder(holder: WidgetVH<*, *, *>, position: Int) {
        val widget = getItem(position)
        (holder as WidgetVH<*, WidgetConfig, WidgetVM>).bindView(widget as Widget<WidgetConfig, WidgetVM>)
    }

    override fun onViewRecycled(holder: WidgetVH<*, *, *>) {
        super.onViewRecycled(holder)
        (holder as WidgetVH<*, WidgetConfig, WidgetVM>).recycleView(holder.absoluteAdapterPosition)
    }

    override fun submitList(list: List<Widget<*, *>>?) {
        list?.forEach { widget -> map[widget.config.viewType()] = widget.config.view() }
        super.submitList(list)
    }

    class DiffCallback : DiffUtil.ItemCallback<Widget<*, *>>() {
        override fun areItemsTheSame(oldItem: Widget<*, *>, newItem: Widget<*, *>) = oldItem.config.areItemsTheSame(newItem.config)
        override fun areContentsTheSame(oldItem: Widget<*, *>, newItem: Widget<*, *>) = oldItem.config.areContentsTheSame(newItem.config)
    }
}