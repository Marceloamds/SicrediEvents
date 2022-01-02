package com.sicredi.events.domain.boundary

import com.sicredi.events.domain.entity.event.Event

interface EventRepository {

    suspend fun getEventsList(): List<Event>?
    suspend fun getEventDetail(eventId: Int): Event?
    suspend fun eventCheckIn()
}