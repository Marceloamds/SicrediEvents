package com.sicredi.events.presentation.view.events.list

import android.content.Context
import com.sicredi.events.presentation.util.navigation.NavData

class ListEventsNavData : NavData {

    override fun navigate(context: Context) {
        context.startActivity(ListEventsActivity.createIntent(context))
    }
}