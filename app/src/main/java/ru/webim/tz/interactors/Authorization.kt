package ru.webim.tz.interactors

import ru.webim.tz.stash.source.UserCacheSource
import javax.inject.Inject

class Authorization @Inject constructor(
    private val userCacheDataSource: UserCacheSource
) {
    suspend fun get() = userCacheDataSource.getUserToken()?.trim().isNullOrBlank()
}