package com.sicredi.events.data.client

import com.sicredi.events.data.entity.ApiEvent
import com.sicredi.events.data.entity.CheckInRequest
import com.sicredi.events.data.util.request.RequestHandler

class ApiClient constructor(
    private val apiService: ApiService
) : RequestHandler() {

    suspend fun getEventsList(): List<ApiEvent>? {
        return makeRequest(apiService.getEventsList())
    }

    suspend fun eventCheckIn(checkInRequest: CheckInRequest) {
        makeRequest(apiService.eventCheckIn(checkInRequest))
    }
}