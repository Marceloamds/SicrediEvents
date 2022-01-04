package com.sicredi.events.data.entity

data class CheckInRequest(
    val eventId: Int,
    val name: String,
    val email: String
)