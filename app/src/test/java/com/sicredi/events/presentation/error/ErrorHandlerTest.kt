package com.sicredi.events.presentation.error

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sicredi.events.KoinTestApp
import com.sicredi.events.R
import com.sicredi.events.domain.entity.error.RequestException
import com.sicredi.events.presentation.util.error.ErrorHandler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
@Config(application = KoinTestApp::class, sdk = [29])
class ErrorHandlerTest {

    private val app: KoinTestApp = ApplicationProvider.getApplicationContext()
    private val context = app.applicationContext
    private lateinit var errorHandler: ErrorHandler

    @Before
    fun setup() {
        errorHandler = ErrorHandler(context)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `dialog data message should be default when Exception is generic`() {
        val throwable = Exception()
        val dialogData = errorHandler.getDialogData(throwable) { }
        assertEquals(dialogData.message, context.getString(R.string.error_default))
    }

    @Test
    fun `dialog data message should be default when Exception is a non suppoted request exception`() {
        val throwable = RequestException.HttpError(418)
        val dialogData = errorHandler.getDialogData(throwable) { }
        assertEquals(dialogData.message, context.getString(R.string.error_default))
    }
}