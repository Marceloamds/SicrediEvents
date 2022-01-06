package com.sicredi.events.presentation.view.user.info

import android.content.Context
import com.sicredi.events.presentation.util.navigation.NavData

class UserInfoNavData : NavData {

    override fun navigate(context: Context) {
        context.startActivity(UserInfoActivity.createIntent(context))
    }
}