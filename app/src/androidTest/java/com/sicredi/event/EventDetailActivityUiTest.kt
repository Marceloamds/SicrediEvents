package com.sicredi.event

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.sicredi.events.R
import com.sicredi.events.domain.boundary.EventRepository
import com.sicredi.events.presentation.SicrediEventsApplication
import com.sicredi.events.presentation.util.extension.getDayDescription
import com.sicredi.events.presentation.view.events.details.EventDetailsActivity
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever

@RunWith(AndroidJUnit4::class)
@LargeTest
class EventDetailActivityUiTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    private val context: SicrediEventsApplication = ApplicationProvider.getApplicationContext()

    @Before
    fun setUp() {
        declareMock<EventRepository> {
            runBlocking {
                whenever(getEventsList()) doReturn eventListMock
            }
        }
    }

    @Test
    fun isInformationDisplayedCorrectly() {
        val intent = EventDetailsActivity.createIntent(context, eventListMock.first())
        ActivityScenario.launch<EventDetailsActivity>(intent)
        onView(withId(R.id.text_view_event_title))
            .check(matches(withText(eventListMock.first().title)))
        onView(withId(R.id.text_view_description))
            .check(matches(withText(eventListMock.first().description)))
        onView(withId(R.id.text_view_event_date))
            .check(matches(withText(eventListMock.first().date.getDayDescription(context))))
        onView(withId(R.id.text_view_price))
            .check(matches(withSubstring(eventListMock.first().price.toString())))
    }
}