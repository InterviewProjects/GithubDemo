package com.ht.githubdemo.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by ZOMATO on 22 June, 2022
 */
abstract class RecyclerViewLoadMoreListener protected constructor(
        private val mLinearLayoutManager: LinearLayoutManager,
        private val PER_PAGE_PRODUCT_COUNT: Int = 30,
        private val OFFSET: Int = 10) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0
    private var currentPage = 1
    private var loading = true
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val totalItemCount = mLinearLayoutManager.itemCount
        val lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition()
        if (loading && totalItemCount > previousTotal) {
            loading = false
            previousTotal = totalItemCount
        }
        if (!loading && totalItemCount >= currentPage * PER_PAGE_PRODUCT_COUNT && totalItemCount - lastVisibleItemPosition <= OFFSET) {
            currentPage++
            onLoadMore(currentPage)
            loading = true
        }

        if (dy > 10) onScroll()
    }

    fun resetPreviousTotal() {
        previousTotal = 0
        currentPage = 1
    }

    abstract fun onLoadMore(currentPage: Int)
    open fun onScroll() {}
}