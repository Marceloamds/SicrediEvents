package com.sicredi.events.data.entity

import com.sicredi.events.domain.entity.event.Event

data class ApiEvent(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val longitude: Float,
    val latitude: Float,
    val price: Double
) {

    fun toDomainObject() = Event(
        id = id,
        title = title,
        description = description,
        image = image,
        longitude = longitude,
        latitude = latitude,
        price = price,
    )
}