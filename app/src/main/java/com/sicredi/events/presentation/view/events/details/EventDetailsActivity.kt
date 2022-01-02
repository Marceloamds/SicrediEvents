package com.sicredi.events.presentation.view.events.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.sicredi.events.R
import com.sicredi.events.databinding.ActivityEventDetailsBinding
import com.sicredi.events.domain.entity.event.Event
import com.sicredi.events.presentation.util.extension.setSafeClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventDetailsActivity : AppCompatActivity() {

    private val _viewModel: EventDetailsViewModel by viewModel()

    private lateinit var binding: ActivityEventDetailsBinding
    private val event by lazy { intent.getSerializableExtra(EVENT_EXTRA) as Event }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_event_details)
        setupEventInfo()
        setupLike()
        setupUi()
    }

    private fun subscribeUi() {

    }

    private fun setupUi() {
        with(binding) {
            buttonShare.setSafeClickListener { _viewModel.onShareClicked(event) }
            buttonGoBack.setSafeClickListener { finish() }
        }
    }

    private fun setupEventInfo() {
        with(binding) {
            textViewEventTitle.text = event.title
            textViewSynopsis.text = event.description
        }
    }

    private fun setupLike() {
        binding.buttonFavorite.setImageDrawable(
            ContextCompat.getDrawable(
                binding.root.context,
               R.drawable.ic_favorite
            )
        )
    }

    companion object {
        private const val EVENT_EXTRA = "EVENT_EXTRA"

        fun createIntent(context: Context, event: Event): Intent {
            return Intent(context, EventDetailsActivity::class.java).apply {
                putExtra(EVENT_EXTRA, event)
            }
        }
    }
}