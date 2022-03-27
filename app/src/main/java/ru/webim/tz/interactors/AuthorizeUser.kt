package ru.webim.tz.interactors

import android.util.Log
import ru.webim.tz.data.cache.abstraction.UserCacheDataSource
import ru.webim.tz.data.network.AviaService
import ru.webim.tz.domain.model.AuthError
import ru.webim.tz.domain.model.AuthState
import javax.inject.Inject

private const val LOGIN_ERROR = "invalid_login"
private const val PASSWORD_ERROR = "invalid_password"

class AuthorizeUser @Inject constructor(
    private val userCacheDataSource: UserCacheDataSource,
    private val aviaService: AviaService
) {
    private val TAG = "AuthorizeUser"

    suspend fun login(login: String, password: Int): AuthState {
        val token = userCacheDataSource.getUserToken()
        return if (token == null) {
            createUserAndCacheToken(login, password)
        } else {
            AuthState(
                isLoading = false,
                isAuthenticated = true,
                error = null
            )
        }
    }

    private suspend fun createUserAndCacheToken(login: String, password: Int): AuthState {
        return try {
            val response = aviaService.createUser(
                login = login,
                password = password
            )
            response.token?.let { userCacheDataSource.saveUserToken(it) }
            getAuthState(response.token, response.result)
        } catch (e: Exception) {
            Log.w(TAG, e)
            AuthState(
                isLoading = false,
                isAuthenticated = false,
                error = AuthError.UNKNOWN
            )
        }
    }

    private fun getAuthState(token: String?, error: String?): AuthState {
        return if (token.isNullOrBlank().not()) {
            AuthState(
                isLoading = false,
                isAuthenticated = true,
                error = null
            )
        } else {
            AuthState(
                isLoading = false,
                isAuthenticated = false,
                error = getAuthError(error)
            )
        }
    }

    private fun getAuthError(error: String?) = when (error) {
        LOGIN_ERROR -> {
            AuthError.WRONG_LOGIN
        }
        PASSWORD_ERROR -> {
            AuthError.WRONG_PASSWORD
        }
        else -> {
            AuthError.UNKNOWN
        }
    }
}