package com.sicredi.events.presentation.view.events.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.sicredi.events.R
import com.sicredi.events.presentation.util.extension.onGoTo
import com.sicredi.events.presentation.util.extension.onDialog
import com.sicredi.events.databinding.ActivityListEventsBinding
import com.sicredi.events.presentation.util.extension.setSafeClickListener
import com.sicredi.events.presentation.util.extension.setVisible
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListEventsActivity : AppCompatActivity() {

    private val _viewModel: ListEventsViewModel by viewModel()

    private lateinit var binding: ActivityListEventsBinding
    private lateinit var adapter: ListEventsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_events)
        supportActionBar?.title = getString(R.string.choose_your_event)
        setupRecyclerView()
        setupUi()
        subscribeUi()
    }

    private fun subscribeUi() {
        _viewModel.eventList.observe(this) { it?.let(adapter::submitList) }
        _viewModel.placeholder.observe(this) { binding.placeholderView.setPlaceholder(it) }
        _viewModel.showEmptyPlaceholder.observe(this, ::onEmptyPlaceholder)
        _viewModel.goTo.observe(this, ::onGoTo)
        _viewModel.dialog.observe(this, ::onDialog)
    }

    private fun setupRecyclerView() {
        adapter = ListEventsAdapter(_viewModel::onEventSelected)
        binding.recyclerViewEvents.layoutManager = LinearLayoutManager(this@ListEventsActivity)
        binding.recyclerViewEvents.adapter = adapter
    }

    private fun setupUi() {
        binding.buttonTryAgain.setSafeClickListener { _viewModel.getAllEvents() }
    }

    private fun onEmptyPlaceholder(showEmptyPlaceholder: Boolean?) {
        binding.emptyListLayout.setVisible(showEmptyPlaceholder)
    }

    companion object {

        fun createIntent(context: Context) =
            Intent(context, ListEventsActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
    }
}