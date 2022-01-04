package com.sicredi.events.presentation.view.events.check_in

import android.content.Context
import com.sicredi.events.presentation.util.navigation.NavData

class EventCheckInNavData() : NavData {

    override fun navigate(context: Context) {
        context.startActivity(EventCheckInActivity.createIntent(context))
    }
}