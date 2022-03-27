package ru.webim.tz.interactors

import ru.webim.tz.data.cache.abstraction.UserCacheDataSource
import javax.inject.Inject

class IsAuthorizationNeeded @Inject constructor(
    private val userCacheDataSource: UserCacheDataSource
) {
    /* TODO we can change this to some real check when server method will be ready and token won't be static
       TODO since token is static I believe this is permissible check for demo app.
     */
    suspend fun get() = userCacheDataSource.getUserToken()?.trim().isNullOrBlank()
}