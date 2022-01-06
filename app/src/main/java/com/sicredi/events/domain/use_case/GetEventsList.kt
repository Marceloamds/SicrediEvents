package com.sicredi.events.domain.use_case

import com.sicredi.events.domain.boundary.EventRepository

class GetEventsList constructor(
    private val eventRepository: EventRepository
) {

    suspend fun execute() = eventRepository.getEventsList()
}