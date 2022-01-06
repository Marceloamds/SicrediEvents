package com.sicredi.events.presentation.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sicredi.events.domain.entity.error.RequestException
import com.sicredi.events.domain.entity.user.UserInfo
import com.sicredi.events.domain.form.CheckInForm.Companion.EMAIL_ID
import com.sicredi.events.domain.form.validator.InvalidFieldsException
import com.sicredi.events.domain.interactor.EventCheckIn
import com.sicredi.events.domain.interactor.GetUserInfo
import com.sicredi.events.domain.interactor.SaveCheckInInfo
import com.sicredi.events.getOrAwaitValue
import com.sicredi.events.presentation.util.error.ErrorHandler
import com.sicredi.events.presentation.view.events.details.EventDetailsViewModel
import com.sicredi.events.presentation.view.user.check_in.UserInfoNavData
import com.sicredi.events.presentation.view.user.check_in.UserInfoViewModel
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

class UserInfoViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val saveCheckInInfo: SaveCheckInInfo = mockk(relaxed = true)
    private lateinit var viewModel: UserInfoViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        viewModel = UserInfoViewModel(saveCheckInInfo)
        Dispatchers.setMain(Executors.newSingleThreadExecutor().asCoroutineDispatcher())
    }

    @Test
    fun `onSaveSuccess should be set if saveCheckInInfo completes successfully`() {
        coEvery { saveCheckInInfo.execute(any()) } returns Unit
        viewModel.onSubmitClicked("", "")
        assert(viewModel.onSaveSuccess.getOrAwaitValue() is Unit)
    }

    @Test
    fun `invalidFieldError should be set if saveCheckInInfo throws InvalidFieldsException`() {
        coEvery { saveCheckInInfo.execute(any()) } throws InvalidFieldsException()
        viewModel.onSubmitClicked("", "")
        assert(viewModel.invalidFields.getOrAwaitValue() != null)
    }

    @Test
    fun `onSaveInfoError should be set if saveCheckInInfo throws an exception`() {
        coEvery { saveCheckInInfo.execute(any()) } throws Exception()
        viewModel.onSubmitClicked("", "")
        assert(viewModel.onSaveInfoError.getOrAwaitValue() != null)
    }
}