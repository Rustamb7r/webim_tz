package ru.webim.tz.interactors

import android.util.Log
import ru.webim.tz.stash.source.UserCacheSource
import ru.webim.tz.web.AviaIntService
import ru.webim.tz.model.TicketError
import ru.webim.tz.model.TicketState
import javax.inject.Inject

private const val LOGIN_ERROR = "invalid_login"
private const val PASSWORD_ERROR = "invalid_password"

class AuthorizeUser @Inject constructor(
    private val userCacheDataSource: UserCacheSource,
    private val aviaService: AviaIntService
) {
    private val TAG = "AuthorizeUser"

    suspend fun login(login: String, password: Int): TicketState {
        val token = userCacheDataSource.getUserToken()
        return if (token == null) {
            createUserAndCacheToken(login, password)
        } else {
            TicketState(
                isLoading = false,
                isAuthenticated = true,
                error = null
            )
        }
    }

    private suspend fun createUserAndCacheToken(login: String, password: Int): TicketState {
        return try {
            val response = aviaService.createUser(
                login = login,
                password = password
            )
            response.token?.let { userCacheDataSource.saveUserToken(it) }
            getAuthState(response.token, response.result)
        } catch (e: Exception) {
            Log.w(TAG, e)
            TicketState(
                isLoading = false,
                isAuthenticated = false,
                error = TicketError.UNKNOWN
            )
        }
    }

    private fun getAuthState(token: String?, error: String?): TicketState {
        return if (token.isNullOrBlank().not()) {
            TicketState(
                isLoading = false,
                isAuthenticated = true,
                error = null
            )
        } else {
            TicketState(
                isLoading = false,
                isAuthenticated = false,
                error = getAuthError(error)
            )
        }
    }

    private fun getAuthError(error: String?) = when (error) {
        LOGIN_ERROR -> {
            TicketError.WRONG_LOGIN
        }
        PASSWORD_ERROR -> {
            TicketError.WRONG_PASSWORD
        }
        else -> {
            TicketError.UNKNOWN
        }
    }
}