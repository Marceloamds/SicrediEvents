package com.sicredi.events.data.client

import com.sicredi.events.data.entity.ApiEvent
import com.sicredi.events.data.util.request.RequestHandler
import com.sicredi.events.domain.entity.event.Event

class ApiClient constructor(
    private val apiService: ApiService
) : RequestHandler() {

    suspend fun getEventsList(): List<ApiEvent>? {
        return makeRequest(apiService.getEventsList())
    }

    suspend fun getEventDetail(eventId: Int): ApiEvent? {
        return makeRequest(apiService.getEventDetail(eventId))
    }

    suspend fun eventCheckIn() {
        makeRequest(apiService.eventCheckIn())
    }
}