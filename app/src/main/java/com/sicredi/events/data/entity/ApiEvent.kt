package com.sicredi.events.data.entity

import com.google.android.gms.maps.model.LatLng
import com.sicredi.events.domain.entity.event.Event
import java.time.Instant
import java.time.ZoneId

data class ApiEvent(
    val id: Int,
    val title: String,
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
    val price: Double
) {

    fun toDomainObject() = Event(
        id = id,
        title = title,
        date = Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate(),
        description = description,
        image = image,
        location = LatLng(latitude, longitude),
        price = price,
    )
}