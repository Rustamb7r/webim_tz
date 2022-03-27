package ru.webim.tz.data.cache.abstraction

interface UserCacheDataSource {

    suspend fun saveUserToken(token: String?)

    suspend fun getUserToken(): String?
}






