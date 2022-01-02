package com.sicredi.events.domain.interactor

import com.sicredi.events.domain.boundary.EventRepository

class GetEventDetail constructor(
    private val eventRepository: EventRepository
) {

    suspend fun execute(eventId: Int) = eventRepository.getEventDetail(eventId)
}