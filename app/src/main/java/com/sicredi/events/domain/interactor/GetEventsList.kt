package com.sicredi.events.domain.interactor

import com.sicredi.events.domain.boundary.EventRepository

class GetEventsList constructor(
    private val eventRepository: EventRepository
) {

    suspend fun execute() = eventRepository.getEventsList()
}