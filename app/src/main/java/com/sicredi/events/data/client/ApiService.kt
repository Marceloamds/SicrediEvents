package com.sicredi.events.data.client

import com.sicredi.events.data.entity.ApiEvent
import com.sicredi.events.data.entity.CheckInRequest
import com.sicredi.events.domain.entity.event.Event
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("events")
    suspend fun getEventsList(): Response<List<ApiEvent>>

    @POST("checkin")
    suspend fun eventCheckIn(@Body checkInRequest: CheckInRequest): Response<Void>
}