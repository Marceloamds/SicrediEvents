package com.sicredi.events.data.repository

import com.sicredi.events.data.client.ApiClient
import com.sicredi.events.domain.boundary.EventRepository
import com.sicredi.events.domain.entity.event.Event

class DefaultEventRepository constructor(
    private val apiClient: ApiClient
) : EventRepository {

    override suspend fun getEventsList(): List<Event>? {
        return apiClient.getEventsList()?.map { it.toDomainObject() }
    }

    override suspend fun getEventDetail(eventId: Int): Event? {
        return apiClient.getEventDetail(eventId)?.toDomainObject()
    }

    override suspend fun eventCheckIn() {
       apiClient.eventCheckIn()
    }
}