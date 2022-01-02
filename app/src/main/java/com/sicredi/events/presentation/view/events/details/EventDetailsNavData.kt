package com.sicredi.events.presentation.view.events.details

import android.content.Context
import com.sicredi.events.domain.entity.event.Event
import com.sicredi.events.presentation.util.navigation.NavData

class EventDetailsNavData(
    private val event: Event
) : NavData {

    override fun navigate(context: Context) {
        context.startActivity(EventDetailsActivity.createIntent(context, event))
    }
}