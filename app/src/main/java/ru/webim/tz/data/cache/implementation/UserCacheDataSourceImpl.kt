package ru.webim.tz.data.cache.implementation

import android.content.SharedPreferences
import ru.webim.tz.data.cache.abstraction.UserCacheDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserCacheDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : UserCacheDataSource {

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
