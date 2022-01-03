package com.sicredi.events.domain.entity.event

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import java.time.LocalDate
import java.util.*

@Parcelize
data class Event(
    val id: Int,
    val title: String,
    val date: LocalDate,
    val description: String,
    val image: String,
    val location: LatLng,
    val price: Double
) : Parcelable
