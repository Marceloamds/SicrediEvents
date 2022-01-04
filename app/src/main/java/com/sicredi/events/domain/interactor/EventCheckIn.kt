package com.sicredi.events.domain.interactor

import com.sicredi.events.domain.boundary.EventRepository
import com.sicredi.events.domain.boundary.UserInfoRepository
import com.sicredi.events.domain.entity.event.Event
import com.sicredi.events.domain.entity.user.UserInfo

class EventCheckIn constructor(
    private val eventRepository: EventRepository
) {

    suspend fun execute(eventId: Int, userInfo: UserInfo) = eventRepository.eventCheckIn(eventId, userInfo)
}