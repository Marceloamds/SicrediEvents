package com.sicredi.events.presentation.events.details

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.google.android.gms.maps.model.LatLng
import com.sicredi.events.KoinTestApp
import com.sicredi.events.presentation.assertText
import com.sicredi.events.presentation.assertTextContains
import com.sicredi.events.databinding.ActivityEventDetailsBinding
import com.sicredi.events.domain.entity.event.Event
import com.sicredi.events.presentation.getBinding
import com.sicredi.events.presentation.view.events.details.EventDetailsActivity
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.time.LocalDate

@RunWith(RobolectricTestRunner::class)
@Config(application = KoinTestApp::class, sdk = [29])
class EventDetailsActivityTest {

    private lateinit var scenario: ActivityScenario<EventDetailsActivity>
    private val app: KoinTestApp = ApplicationProvider.getApplicationContext()

    private val eventMock = Event(
        id = 1,
        title = "Esse é um evento teste",
        LocalDate.now(),
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc at nunc quis velit pretium suscipit. Aenean id felis tempus, interdum velit venenatis, pretium magna. Nullam fermentum, augue eget consectetur sollicitudin, nisi metus lacinia libero, sit amet sodales ligula elit in quam. In mattis orci vel venenatis euismod. Sed a est in magna pellentesque dignissim vitae ut lorem.",
        image = "https://i.imgur.com/DpTHKb9.jpeg",
        location = LatLng(-22.0, -22.0),
        price = 25.0
    )

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `Event Information should be displayed correctly`() {
        val intent = EventDetailsActivity.createIntent(app, eventMock)
        scenario = ActivityScenario.launch(intent)
        scenario.onActivity {
            val binding = it.getBinding<ActivityEventDetailsBinding>()
            binding.textViewDescription.assertText(eventMock.description)
            binding.textViewPrice.assertTextContains(eventMock.price.toString())
            binding.textViewEventTitle.assertText(eventMock.title)
        }
    }
}

