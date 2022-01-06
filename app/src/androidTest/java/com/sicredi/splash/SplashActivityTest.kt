package com.sicredi.splash

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.sicredi.event.eventListMock
import com.sicredi.events.R
import com.sicredi.events.domain.boundary.EventRepository
import com.sicredi.events.presentation.view.splash.SplashActivity
import com.sicredi.events.presentation.view.splash.SplashViewModel.Companion.SPLASH_DELAY
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
class SplashActivityTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Before
    fun setUp() {
        declareMock<EventRepository> {
            runBlocking {
                whenever(getEventsList()) doReturn eventListMock
            }
        }
    }

    @Test
    fun isNavigationBeingMade() {
        ActivityScenario.launch(SplashActivity::class.java)
        Thread.sleep(SPLASH_DELAY + 500)
        onView(withId(R.id.recycler_view_events)).check(matches(isDisplayed()))
    }
}