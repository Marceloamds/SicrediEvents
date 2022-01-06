package com.sicredi.events.domain.boundary

import com.sicredi.events.domain.entity.user.UserInfo
import com.sicredi.events.domain.form.CheckInForm

interface UserInfoRepository {

    fun saveCheckInInfo(checkInForm: CheckInForm)
    suspend fun getUserInfo(): UserInfo?
}