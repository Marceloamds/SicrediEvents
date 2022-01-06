package com.sicredi.events.data.client

import com.sicredi.events.data.entity.ApiEvent
import com.sicredi.events.data.entity.CheckInRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("events")
    suspend fun getEventsList(): Response<List<ApiEvent>>

    @POST("checkin")
    suspend fun eventCheckIn(@Body checkInRequest: CheckInRequest): Response<Void>
}