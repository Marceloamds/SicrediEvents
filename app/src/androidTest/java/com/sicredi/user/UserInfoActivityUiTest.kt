package com.sicredi.user

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.sicredi.event.eventListMock
import com.sicredi.events.R
import com.sicredi.events.domain.boundary.EventRepository
import com.sicredi.events.domain.boundary.UserInfoRepository
import com.sicredi.events.domain.entity.user.UserInfo
import com.sicredi.events.presentation.SicrediEventsApplication
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
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever

@RunWith(AndroidJUnit4::class)
@LargeTest
class UserInfoActivityUiTest : KoinTest {

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
                whenever(eventCheckIn(any(), any())) doReturn Unit
            }
        }
        declareMock<UserInfoRepository> {
            runBlocking {
                whenever(getUserInfo()) doReturn UserInfo("John Doe", "johndoe@test.com")
            }
        }
    }

    @Test
    fun isCheckInBeingMade() {
        val intent = EventDetailsActivity.createIntent(context, eventListMock.first())
        ActivityScenario.launch<EventDetailsActivity>(intent)
        onView(withId(R.id.button_make_check_in)).perform(scrollTo(), click())
        onView(withText(context.getString(R.string.user_info_dialog_change_info))).perform(click())
        onView(withId(R.id.name_edit_text)).perform(typeText("Marcelo"))
        onView(withId(R.id.email_edit_text)).perform(
            scrollTo(),
            typeText("marcelo.augusto9@hotmail.com")
        )
        onView(withId(R.id.button_submit)).perform(scrollTo(), click())
        onView(withId(R.id.button_make_check_in)).perform(click())
        onView(withText(context.getString(R.string.user_info_dialog_keep_info))).perform(click())
        onView(withId(R.id.text_view_check_in_done)).check(matches(isDisplayed()))
    }

    @Test
    fun areErrorsBeingDisplayed() {
        val intent = EventDetailsActivity.createIntent(context, eventListMock.first())
        ActivityScenario.launch<EventDetailsActivity>(intent)
        onView(withId(R.id.button_make_check_in)).perform(scrollTo(), click())
        onView(withText(context.getString(R.string.user_info_dialog_change_info))).perform(click())
        onView(withId(R.id.button_submit)).perform(scrollTo(), click())
        onView(withText(R.string.check_in_email_error)).check(matches(isDisplayed()))
        onView(withText(R.string.check_in_name_error)).check(matches(isDisplayed()))
    }
}