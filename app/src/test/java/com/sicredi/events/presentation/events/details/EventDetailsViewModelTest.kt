package com.sicredi.events.presentation.events.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sicredi.events.domain.entity.error.RequestException
import com.sicredi.events.domain.entity.user.UserInfo
import com.sicredi.events.domain.use_case.EventCheckIn
import com.sicredi.events.domain.use_case.GetUserInfo
import com.sicredi.events.getOrAwaitValue
import com.sicredi.events.presentation.util.error.ErrorHandler
import com.sicredi.events.presentation.view.events.details.EventDetailsViewModel
import com.sicredi.events.presentation.view.user.info.UserInfoNavData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.Executors
import kotlin.test.assertEquals

class EventDetailsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val eventCheckIn: EventCheckIn = mockk(relaxed = true)
    private val errorHandler: ErrorHandler = mockk(relaxed = true)
    private val getUserInfo: GetUserInfo = mockk(relaxed = true)
    private val userInfoMock = UserInfo("", "")
    private lateinit var viewModel: EventDetailsViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        viewModel = EventDetailsViewModel(eventCheckIn, errorHandler, getUserInfo)
        Dispatchers.setMain(Executors.newSingleThreadExecutor().asCoroutineDispatcher())
    }

    @Test
    fun `goTo should be UserInfoNavData when checkIn button is clicked and there is no user info`() {
        coEvery { getUserInfo.execute() } returns null
        viewModel.onCheckInClicked()
        assert(viewModel.goTo.getOrAwaitValue() is UserInfoNavData)
    }

    @Test
    fun `onUserInfo should be userInfoMock when checkIn button is clicked and there is a user info`() {
        coEvery { getUserInfo.execute() } returns userInfoMock
        viewModel.onCheckInClicked()
        assertEquals(viewModel.onUserInfo.getOrAwaitValue(), userInfoMock)
    }

    @Test
    fun `onCheckInSuccess should be set if makeCheckIn call completes`() {
        coEvery { eventCheckIn.execute(any(), any()) } returns Unit
        viewModel.makeCheckIn(1, userInfoMock)
        assert(viewModel.onCheckInSuccess.getOrAwaitValue() != null)
    }

    @Test
    fun `dialog should be set if makeCheckIn call throws a error`() {
        coEvery { eventCheckIn.execute(any(), any()) } throws RequestException.HttpError(404)
        viewModel.makeCheckIn(1, userInfoMock)
        assert(viewModel.dialog.getOrAwaitValue() != null)
    }
}