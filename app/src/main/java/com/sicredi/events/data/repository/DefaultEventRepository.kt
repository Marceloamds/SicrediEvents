package com.sicredi.events.data.repository

import com.sicredi.events.data.client.ApiClient
import com.sicredi.events.data.entity.CheckInRequest
import com.sicredi.events.domain.boundary.EventRepository
import com.sicredi.events.domain.entity.event.Event
import com.sicredi.events.domain.entity.user.UserInfo

class DefaultEventRepository constructor(
    private val apiClient: ApiClient
) : EventRepository {

    override suspend fun getEventsList(): List<Event>? {
        return apiClient.getEventsList()?.map { it.toDomainObject() }
    }

    override suspend fun eventCheckIn(eventId: Int, userInfo: UserInfo) {
        apiClient.eventCheckIn(CheckInRequest(eventId, userInfo.name, userInfo.email))
    }
}