package com.ht.githubdemo.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ht.githubdemo.R
import com.ht.githubdemo.base.BaseAdapter
import com.ht.githubdemo.databinding.ActivityHomeBinding
import com.ht.githubdemo.utils.ViewExt.visibility

class HomeActivity : AppCompatActivity() {
    private val binding: ActivityHomeBinding by lazy {
        DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)
    }
    private lateinit var viewModel: HomeVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** set view-model and observers */
        viewModel = ViewModelProvider(this)[HomeVM::class.java]
        setUpObservers()

        /** setup UI */
        setUpUI()

        /** start view model */
        viewModel.resume()
    }

    /** set up UI */
    private fun setUpUI() {
        binding.vmRv.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = BaseAdapter(this@HomeActivity)
        }
    }

    /** observers */
    private fun setUpObservers() {
        viewModel.apply {
            /** loader */
            loaderLE.observe(this@HomeActivity) { binding.progress.visibility = it.visibility() }

            /** toast */
            toastLE.observe(this@HomeActivity) { Toast.makeText(this@HomeActivity, it, Toast.LENGTH_SHORT).show() }

            /** widget list */
            adapterListLE.observe(this@HomeActivity) { (binding.vmRv.adapter as BaseAdapter).submitList(it) }
        }
    }
}