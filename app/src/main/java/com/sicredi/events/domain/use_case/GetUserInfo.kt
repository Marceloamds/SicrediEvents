package com.sicredi.events.domain.use_case

import com.sicredi.events.domain.boundary.UserInfoRepository

class GetUserInfo constructor(
    private val userInfoRepository: UserInfoRepository
) {

    suspend fun execute() = userInfoRepository.getUserInfo()
}