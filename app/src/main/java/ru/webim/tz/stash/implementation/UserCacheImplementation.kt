package ru.webim.tz.stash.implementation

import android.content.SharedPreferences
import ru.webim.tz.stash.source.UserCacheSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserCacheImplementation @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : UserCacheSource {

    override suspend fun saveUserToken(token: String?) = with(sharedPreferences.edit()) {
        putString(USER_TOKEN_ARG, token)
        apply()
    }

    override suspend fun getUserToken(): String? {
        return sharedPreferences.getString(USER_TOKEN_ARG, null)
    }

    companion object {
        private const val USER_TOKEN_ARG = "USER_TOKEN_ARG"
    }
}
