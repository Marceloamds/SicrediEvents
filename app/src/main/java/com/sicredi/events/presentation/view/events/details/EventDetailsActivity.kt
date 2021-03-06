package com.sicredi.events.presentation.view.events.details

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import com.sicredi.events.domain.entity.user.UserInfo
import com.sicredi.events.presentation.util.dialog.DialogData
import com.sicredi.events.presentation.util.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventDetailsActivity : AppCompatActivity() {

    private val _viewModel: EventDetailsViewModel by viewModel()

    private lateinit var binding: ActivityEventDetailsBinding
    private val event by lazy {
        intent.getParcelableExtra(EVENT_EXTRA) as? Event ?: throw Exception("Event not found")
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
        _viewModel.dialog.observe(this, ::onDialog)
        _viewModel.onUserInfo.observe(this, ::onUserInfo)
        _viewModel.placeholder.observe(this) { binding.placeholderView.setPlaceholder(it) }
        _viewModel.onCheckInSuccess.observe(this) { onCheckInSuccess() }
    }

    private fun setupUi() {
        with(binding) {
            buttonGoBack.setSafeClickListener { finish() }
            buttonShare.setSafeClickListener { shareEvent() }
            buttonMakeCheckIn.setSafeClickListener { _viewModel.onCheckInClicked() }
        }
    }

    private fun setupEventInfo() {
        with(binding) {
            textViewEventTitle.text = event.title
            textViewDescription.text = event.description
            textViewPrice.text = event.price.toMoneyString(root.context)
            textViewEventDate.text = event.date.getDayDescription(root.context)
            imageViewEventPoster.load(event.image)
            googleMap.getMapAsync { it.goToPosition(event.location) }
        }
    }

    private fun onUserInfo(userInfo: UserInfo?) {
        userInfo?.let {
            showDialog(DialogData(
                title = getString(R.string.user_info_dialog_title),
                message = getString(R.string.user_info_dialog_description, it.name, it.email),
                confirmButtonText = getString(R.string.user_info_dialog_keep_info),
                onConfirm = { _viewModel.makeCheckIn(event.id, it) },
                cancelButtonText = getString(R.string.user_info_dialog_change_info),
                onCancel = { _viewModel.goToUserInfo() }
            ))
        }
    }

    private fun onCheckInSuccess() {
        binding.buttonMakeCheckIn.setVisible(false)
        binding.textViewCheckInDone.setVisible(true)
    }

    private fun GoogleMap.goToPosition(location: LatLng) {
        setOnMapClickListener { onMapCLicked(location) }
        animateCamera(CameraUpdateFactory.newLatLng(location))
        addMarker(MarkerOptions().position(location))
    }

    private fun onMapCLicked(location: LatLng) {
        val mapIntent = Intent(Intent.ACTION_VIEW, location.getNavigationIntentUri())
        startActivity(mapIntent)
    }

    private fun shareEvent() {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                getString(
                    R.string.share_event_description,
                    event.title,
                    event.date.getDayDescription(this@EventDetailsActivity)
                )
            )
            type = INTENT_TEXT_TYPE
        }
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_event_send_to)))
    }

    private fun LatLng.getNavigationIntentUri(): Uri? {
        return Uri.parse("$NAVIGATION_URL$latitude,$longitude")
    }

    companion object {
        const val MAP_TAG = "google_map"
        const val EVENT_EXTRA = "EVENT_EXTRA"
        private const val NAVIGATION_URL = "google.navigation:q="

        fun createIntent(context: Context, event: Event): Intent {
            return Intent(context, EventDetailsActivity::class.java).apply {
                putExtra(EVENT_EXTRA, event)
            }
        }
    }
}