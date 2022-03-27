package ru.webim.tz.domain.model

data class AuthState(
    var isLoading: Boolean = false,
    var isAuthenticated: Boolean = false,
    var error: AuthError? = null
)