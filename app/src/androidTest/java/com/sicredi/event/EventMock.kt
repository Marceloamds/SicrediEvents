package com.sicredi.event

import com.google.android.gms.maps.model.LatLng
import com.sicredi.events.domain.entity.event.Event
import java.time.LocalDate

val eventListMock = listOf(
    Event(
        id = 1,
        title = "Esse é um evento teste",
        LocalDate.now(),
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc at nunc quis velit pretium suscipit. Aenean id felis tempus, interdum velit venenatis, pretium magna. Nullam fermentum, augue eget consectetur sollicitudin, nisi metus lacinia libero, sit amet sodales ligula elit in quam. In mattis orci vel venenatis euismod. Sed a est in magna pellentesque dignissim vitae ut lorem.",
        image = "https://i.imgur.com/DpTHKb9.jpeg",
        location = LatLng(-22.0, -22.0),
        price = 25.0
    ),
    Event(
        id = 2,
        title = "Esse também é um evento teste",
        LocalDate.now(),
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc at nunc quis velit pretium suscipit. Aenean id felis tempus, interdum velit venenatis, pretium magna. Nullam fermentum, augue eget consectetur sollicitudin, nisi metus lacinia libero, sit amet sodales ligula elit in quam. In mattis orci vel venenatis euismod. Sed a est in magna pellentesque dignissim vitae ut lorem.",
        image = "https://i.imgur.com/DpTHKb9.jpeg",
        location = LatLng(-20.0, -20.0),
        price = 15.0
    )
)
