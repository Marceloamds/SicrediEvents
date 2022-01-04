package com.sicredi.events.presentation.view.events.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.sicredi.events.R
import com.sicredi.events.databinding.ActivityEventDetailsBinding
import com.sicredi.events.domain.entity.event.Event
import com.sicredi.events.presentation.util.extension.getDayDescription
import com.sicredi.events.presentation.util.extension.load
import com.sicredi.events.presentation.util.extension.onGoTo
import com.sicredi.events.presentation.util.extension.setSafeClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventDetailsActivity : AppCompatActivity() {

    private val _viewModel: EventDetailsViewModel by viewModel()

    private lateinit var binding: ActivityEventDetailsBinding
    private val event by lazy {
        intent.getParcelableExtra(EVENT_EXTRA) as? Event
            ?: throw RuntimeException("Event not found")
    }
    private val googleMap by lazy { supportFragmentManager.findFragmentByTag(MAP_TAG) as SupportMapFragment }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_event_details)
        setupEventInfo()
        setupUi()
        subscribeUi()
    }

    private fun subscribeUi() {
        _viewModel.goTo.observe(this, ::onGoTo)
    }

    private fun setupUi() {
        with(binding) {
            buttonGoBack.setSafeClickListener { finish() }
            buttonMakeCheckIn.setSafeClickListener { _viewModel.onCheckInClicked(event.id) }
        }
    }

    private fun setupEventInfo() {
        with(binding) {
            imageViewEventPoster.load(event.image)
            textViewEventTitle.text = event.title
            textViewSynopsis.text = event.description
            textViewOpeningDate.text = event.date.getDayDescription(root.context)
            googleMap.getMapAsync { it.goToPosition(event.location) }
        }
    }

    private fun GoogleMap.goToPosition(location: LatLng) {
        animateCamera(CameraUpdateFactory.newLatLng(location))
        addMarker(MarkerOptions().position(location))
    }

    companion object {
        const val MAP_TAG = "google_map"
        private const val EVENT_EXTRA = "EVENT_EXTRA"

        fun createIntent(context: Context, event: Event): Intent {
            return Intent(context, EventDetailsActivity::class.java).apply {
                putExtra(EVENT_EXTRA, event)
            }
        }
    }
}