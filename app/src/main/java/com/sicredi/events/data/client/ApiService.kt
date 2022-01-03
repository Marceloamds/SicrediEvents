package com.sicredi.events.data.client

import com.sicredi.events.data.entity.ApiEvent
import com.sicredi.events.domain.entity.event.Event
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("events")
    suspend fun getEventsList(): Response<List<ApiEvent>>

    @GET("events")
    suspend fun eventCheckIn(): Response<Event>
}