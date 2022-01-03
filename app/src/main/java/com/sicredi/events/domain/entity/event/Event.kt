package com.sicredi.events.domain.entity.event

import java.io.Serializable
import java.util.*

data class Event(
    val id: Int,
    val title: String,
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Float,
    val latitude: Float,
    val price: Double
) : Serializable
