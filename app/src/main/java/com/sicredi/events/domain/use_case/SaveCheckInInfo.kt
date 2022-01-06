package com.sicredi.events.domain.use_case

import com.sicredi.events.domain.boundary.UserInfoRepository
import com.sicredi.events.domain.form.CheckInForm

class SaveCheckInInfo constructor(
    private val userInfoRepository: UserInfoRepository
) {

    fun execute(checkInForm: CheckInForm) {
        checkInForm.validate()
        userInfoRepository.saveCheckInInfo(checkInForm)
    }
}