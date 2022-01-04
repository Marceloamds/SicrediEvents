package com.sicredi.events.domain.interactor

import com.sicredi.events.domain.boundary.UserInfoRepository

class GetUserInfo constructor(
    private val userInfoRepository: UserInfoRepository
) {

    suspend fun execute() = userInfoRepository.getUserInfo()
}