package com.sicredi.events.presentation.events.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.android.gms.maps.model.LatLng
import com.sicredi.events.domain.entity.event.Event
import com.sicredi.events.domain.use_case.GetEventsList
import com.sicredi.events.getOrAwaitValue
import com.sicredi.events.presentation.util.error.ErrorHandler
import com.sicredi.events.presentation.view.events.details.EventDetailsNavData
import com.sicredi.events.presentation.view.events.list.ListEventsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate
import java.util.concurrent.Executors
import kotlin.test.assertEquals

class ListEventsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getEventsList: GetEventsList = mockk(relaxed = true)
    private val errorHandler: ErrorHandler = mockk(relaxed = true)
    private val eventMock = Event(
        id = 1,
        title = "Esse é um evento teste",
        LocalDate.now(),
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc at nunc quis velit pretium suscipit. Aenean id felis tempus, interdum velit venenatis, pretium magna. Nullam fermentum, augue eget consectetur sollicitudin, nisi metus lacinia libero, sit amet sodales ligula elit in quam. In mattis orci vel venenatis euismod. Sed a est in magna pellentesque dignissim vitae ut lorem.",
        image = "https://i.imgur.com/DpTHKb9.jpeg",
        location = LatLng(-22.0, -22.0),
        price = 25.0
    )

    private lateinit var viewModel: ListEventsViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(Executors.newSingleThreadExecutor().asCoroutineDispatcher())
        viewModel = ListEventsViewModel(getEventsList, errorHandler)
    }

    @Test
    fun `should go to EventDetails when onEventSelected is called`() {
        viewModel.onEventSelected(eventMock)
        assert(viewModel.goTo.getOrAwaitValue() is EventDetailsNavData)
    }

    @Test
    fun `eventList should be set if getEventsList completes successfully`() {
        val eventList = listOf(eventMock)
        coEvery { getEventsList.execute() } returns eventList
        viewModel.getAllEvents()
        assert(viewModel.eventList.getOrAwaitValue() != null)
    }

    @Test
    fun `showEmptyPlaceholder should be set if getEventsList returns a empty list`() {
        coEvery { getEventsList.execute() } returns listOf()
        viewModel.getAllEvents()
        assertEquals(viewModel.showEmptyPlaceholder.getOrAwaitValue(), true)
    }

    @Test
    fun `dialog should be set if saveCheckInInfo throws an exception`() {
        coEvery { getEventsList.execute() } throws Exception()
        viewModel.getAllEvents()
        assert(viewModel.dialog.getOrAwaitValue() != null)
    }
}