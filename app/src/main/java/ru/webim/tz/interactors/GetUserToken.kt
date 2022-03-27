package ru.webim.tz.interactors

import ru.webim.tz.data.cache.abstraction.UserCacheDataSource
import javax.inject.Inject

class GetUserToken @Inject constructor(
    private val userCacheDataSource: UserCacheDataSource
) {
    suspend fun get() = userCacheDataSource.getUserToken()
}