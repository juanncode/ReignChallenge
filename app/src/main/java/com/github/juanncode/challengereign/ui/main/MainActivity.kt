package com.github.juanncode.challengereign.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.github.juanncode.challengereign.HitApp
import com.github.juanncode.challengereign.R
import com.github.juanncode.challengereign.data.database.RoomDataSource
import com.github.juanncode.challengereign.data.repository.HitRepository
import com.github.juanncode.challengereign.data.service.HitDataSource
import com.github.juanncode.challengereign.databinding.ActivityMainBinding
import com.github.juanncode.challengereign.ui.detail.DetailActivity
import com.github.juanncode.challengereign.ui.main.MainViewModel.UiHit
import com.github.juanncode.challengereign.ui.main.MainViewModel.UiHit.GetData
import com.github.juanncode.challengereign.ui.main.MainViewModel.UiHit.Loading


class MainActivity : AppCompatActivity() {
    private lateinit var hitAdapter: HitsAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(
                HitRepository(
                    HitDataSource(),
                    RoomDataSource((applicationContext as HitApp).db)
                )
            )
        )[MainViewModel::class.java]

        initViews()
        initViewModel()
    }

    private fun initViews() {
        hitAdapter = HitsAdapter {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.URL, it.story_url)
            startActivity(intent)
        }

        binding.recyclerNews.adapter = hitAdapter

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerNews)

        binding.swipeRefreshHits.setOnRefreshListener { viewModel.loadHit() }
    }

    private fun initViewModel() {
        viewModel.modelHit.observe(this, modelHitObserver)
    }

    private val modelHitObserver = Observer<UiHit> { model ->
        if (model != Loading) binding.progress.visibility = View.GONE

        when (model) {
            is GetData -> {
                if (model.hits.isEmpty()) {
                    binding.textMessage.visibility = View.VISIBLE
                    binding.textMessage.text = getString(R.string.list_empty_message)
                } else {
                    hitAdapter.hits = model.hits.reversed().toMutableList()
                    binding.textMessage.visibility = View.GONE
                }
                binding.swipeRefreshHits.isRefreshing = false
            }
            Loading -> {
                binding.progress.visibility = View.VISIBLE
            }
            is UiHit.DeleteHit -> {
                hitAdapter.hits = model.hits.reversed().toMutableList()
            }
        }
    }

    private val callback = object : SwipeGesture(this) {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            when (direction) {
                ItemTouchHelper.LEFT -> {

                    viewModel.removeHit(hitAdapter.hits[viewHolder.absoluteAdapterPosition])
                }
            }
        }

    }

}