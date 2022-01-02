package com.sicredi.events.domain.interactor

import com.sicredi.events.domain.boundary.EventRepository
import com.sicredi.events.domain.entity.event.Event

class EventCheckIn constructor(
    private val eventRepository: EventRepository
) {

    suspend fun execute(eventId: Int) = eventRepository.eventCheckIn()
}