package ru.webim.tz.presentation.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.webim.tz.model.TicketState
import ru.webim.tz.interactors.AuthorizeUser
import ru.webim.tz.interactors.Authorization
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val getShowWelcomeScreenUseCase: AuthorizeUser,
    private val isAuthorizationNeeded: Authorization
) : ViewModel() {

    private val _authState = MutableLiveData(TicketState())
    val authState: LiveData<TicketState> = _authState

    init {
        viewModelScope.launch {
            _authState.value = _authState.value?.copy(
                isAuthenticated = isAuthorizationNeeded.get().not()
            )
        }
    }

    fun login(login: String, password: Int) {
        _authState.value = _authState.value?.copy(
            isLoading = true
        )
        viewModelScope.launch {
            try {
                val state = getShowWelcomeScreenUseCase.login(login = login, password = password)
                _authState.value = state
            } catch (e: Exception) {
                _authState.value = _authState.value?.copy(
                    isLoading = false
                )
            }
        }
    }
}