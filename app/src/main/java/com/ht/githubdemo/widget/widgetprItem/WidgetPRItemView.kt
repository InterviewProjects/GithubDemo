package com.ht.githubdemo.widget.widgetprItem

import android.content.Context
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.ht.githubdemo.databinding.ItemWidgetPrItemBinding
import com.ht.githubdemo.widget.base.Widget
import com.ht.githubdemo.widget.base.WidgetVM
import com.ht.githubdemo.widget.base.WidgetView

/**
 * Created by ZOMATO on 15,June,2022
 */
class WidgetPRItemView constructor(context: Context): WidgetView<WidgetPRItemConfig, WidgetVM>(context) {
    private val binding: ItemWidgetPrItemBinding by lazy {
        ItemWidgetPrItemBinding.inflate(LayoutInflater.from(context), this, false)
    }

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        addView(binding.root)
    }

    override fun bind(widget: Widget<WidgetPRItemConfig, WidgetVM>?) {
        super.bind(widget)

        binding.apply {
            val prItem = widget?.config?.prItem

            /** profile */
            val imageURL = prItem?.user?.userImage
            Glide.with(context!!)
                .load(imageURL)
                .into(prProfileIv)

            /** pr title */
            prTitleTv.text = prItem?.title

            /** created by */
            prCreatedByValueTv.text = prItem?.user?.userName

            /** pr create */
            prCreateValueTv.text = prItem?.createdAt

            /** pr close */
            prCloseValueTv.text = prItem?.closedAt
        }
    }

}