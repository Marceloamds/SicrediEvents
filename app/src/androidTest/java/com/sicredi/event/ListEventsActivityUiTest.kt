package com.sicredi.event

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.sicredi.events.R
import com.sicredi.events.domain.boundary.EventRepository
import com.sicredi.events.presentation.SicrediEventsApplication
import com.sicredi.events.presentation.util.extension.getDayDescription
import com.sicredi.events.presentation.view.events.list.ListEventsActivity
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
class ListEventsActivityUiTest : KoinTest {

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
        ActivityScenario.launch(ListEventsActivity::class.java)
        onView(withId(R.id.recycler_view_events))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, scrollTo()))
            .check(matches(hasDescendant(withSubstring(eventListMock.first().description))))
            .check(matches(hasDescendant(withSubstring(eventListMock.first().title))))
            .check(matches(hasDescendant(withSubstring(eventListMock.first().price.toString()))))
            .check(
                matches(
                    hasDescendant(withSubstring(eventListMock.first().date.getDayDescription(context)))
                )
            )
    }

    @Test
    fun isNavigationBeingMade() {
        ActivityScenario.launch(ListEventsActivity::class.java)
        onView(withId(R.id.recycler_view_events))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, scrollTo()))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.image_view_event_poster)).check(matches(isDisplayed()))
    }
}