package com.example.feeddemo.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feeddemo.adapter.NewsFeedAdapter
import com.example.feeddemo.data.api.ApiHelperImpl
import com.example.feeddemo.data.api.RetrofitBuilder
import com.example.feeddemo.data.db.DatabaseBuilder
import com.example.feeddemo.data.db.DatabaseHelperImpl
import com.example.feeddemo.data.model.FeedModel
import com.example.feeddemo.databinding.ActivityNewsFeedBinding
import com.example.feeddemo.utils.Status
import com.example.feeddemo.utils.ViewModelFactory
import com.example.feeddemo.viewModel.NewsFeedViewModel

class NewsFeedActivity : AppCompatActivity() {

    private lateinit var viewModel: NewsFeedViewModel
    private lateinit var adapter: NewsFeedAdapter
    private lateinit var binding: ActivityNewsFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        setupViewModel()
        setupObserver()
    }


    private fun setupUI() {
        binding.rvFeed.layoutManager = LinearLayoutManager(this)
        adapter =
            NewsFeedAdapter(
                arrayListOf()
            )
        binding.rvFeed.addItemDecoration(
            DividerItemDecoration(
                binding.rvFeed.context,
                (binding.rvFeed.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.rvFeed.adapter = adapter

        binding.swipeToRefreshLayout.setOnRefreshListener {
            adapter.clearList()
            viewModel.refreshList()
        }
    }

    private fun setupObserver() {
        viewModel.getAllRows().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    binding.rvFeed.visibility = View.VISIBLE
                    binding.swipeToRefreshLayout.isRefreshing = false
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvFeed.visibility = View.GONE
                    binding.swipeToRefreshLayout.isRefreshing = true
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    binding.swipeToRefreshLayout.isRefreshing = false
                }
            }
        })
    }

    private fun renderList(users: List<FeedModel.Row>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            )
        ).get(NewsFeedViewModel::class.java)
    }
}
