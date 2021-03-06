package com.sicredi.events.domain.boundary

import com.sicredi.events.domain.entity.event.Event
import com.sicredi.events.domain.entity.user.UserInfo

interface EventRepository {

    suspend fun getEventsList(): List<Event>?
    suspend fun eventCheckIn(eventId: Int, userInfo: UserInfo)
}