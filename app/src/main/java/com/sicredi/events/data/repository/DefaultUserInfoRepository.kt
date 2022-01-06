package com.sicredi.events.data.repository

import com.sicredi.events.data.util.storage.Cache
import com.sicredi.events.domain.boundary.UserInfoRepository
import com.sicredi.events.domain.entity.user.UserInfo
import com.sicredi.events.domain.form.CheckInForm
import com.sicredi.events.presentation.util.extension.safeLet

class DefaultUserInfoRepository constructor(private val cache: Cache) : UserInfoRepository {

    override fun saveCheckInInfo(checkInForm: CheckInForm) {
        cache.set(USER_EMAIL, checkInForm.emailValidation.text)
        cache.set(USER_NAME, checkInForm.nameValidation.text)
    }

    override suspend fun getUserInfo(): UserInfo? {
        val name  = cache.get<String>(USER_NAME, String::class.java)
        val email = cache.get<String>(USER_EMAIL, String::class.java)
        return safeLet(name, email) { safeName, safeEmail -> UserInfo(safeName, safeEmail) }
    }

    companion object {
        const val USER_NAME = "USER_NAME"
        const val USER_EMAIL = "USER_EMAIL"
    }
}