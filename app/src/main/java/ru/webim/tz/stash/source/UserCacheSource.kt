package ru.webim.tz.stash.source

interface UserCacheSource {

    suspend fun saveUserToken(token: String?)

    suspend fun getUserToken(): String?
}






