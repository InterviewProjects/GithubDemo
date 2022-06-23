package com.ht.githubdemo.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.ht.githubdemo.R
import com.ht.githubdemo.base.BaseAdapter
import com.ht.githubdemo.databinding.ActivityHomeBinding
import com.ht.githubdemo.utils.RecyclerViewLoadMoreListener
import com.ht.githubdemo.utils.ViewExt.visibility
import com.ht.githubdemo.widget.base.Widget
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val binding: ActivityHomeBinding by lazy {
        DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)
    }
    private val homeLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(this)
    }
    private val homeScrollListener: RecyclerViewLoadMoreListener by lazy {
        object : RecyclerViewLoadMoreListener(homeLayoutManager) {
            override fun onLoadMore(currentPage: Int) {
                viewModel.loadMore(currentPage)
            }
        }
    }

    private val viewModel : HomeVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** set view-model and observers */
        setUpObservers()

        /** setup UI */
        setUpUI()

        /** start view model */
        viewModel.resume()
    }

    /** set up UI */
    private fun setUpUI() {
        binding.apply {
            vmRv.apply {
                layoutManager = homeLayoutManager
                addOnScrollListener(homeScrollListener)
                adapter = BaseAdapter(this@HomeActivity)
            }

            layoutRetry.retryBtn.setOnClickListener {
                homeScrollListener.resetPreviousTotal()
                viewModel.resume()
            }
        }
    }

    /** observers */
    private fun setUpObservers() {
        viewModel.apply {
            /** loader */
            loaderLE.observe(this@HomeActivity) { binding.progress.visibility = it.visibility() }

            /** toast */
            errorLE.observe(this@HomeActivity) { handleError(it) }

            /** widget list */
            adapterListLE.observe(this@HomeActivity) { handleItems(it) }
        }
    }

    /** handle items */
    private fun handleItems(widgets: List<Widget<*, *>>?) {
        binding.apply {
            noItemsTv.visibility = (widgets != null && widgets.isEmpty()).visibility()

            vmRv.visibility = widgets.isNullOrEmpty().not().visibility()
            (vmRv.adapter as BaseAdapter).submitList(widgets)
        }
    }

    /** handle error */
    private fun handleError(errorMsg: String?) {
        binding.layoutRetry.apply {
            root.visibility = errorMsg.isNullOrBlank().not().visibility()
            retryTv.text = errorMsg
        }
    }
}