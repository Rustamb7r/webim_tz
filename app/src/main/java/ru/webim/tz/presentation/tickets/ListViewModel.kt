package ru.webim.tz.presentation.tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.webim.tz.stash.source.UserCacheSource
import ru.webim.tz.model.AscendingOrder
import ru.webim.tz.model.TicketParameter
import ru.webim.tz.model.TicketSortType
import ru.webim.tz.interactors.GetTicketList
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val cacheDataSource: UserCacheSource,
    private val getTicketList: GetTicketList
) : ViewModel() {

    private val _isAuthenticated = MutableLiveData(false)
    val isAuthenticated: LiveData<Boolean> = _isAuthenticated

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _sortType = MutableLiveData<TicketSortType>(AscendingOrder())
    val sortType: LiveData<TicketSortType> = _sortType

    private val _ticketList = MutableLiveData<List<TicketParameter>>()
    val ticketList: LiveData<List<TicketParameter>> = _ticketList

    init {
        loadTickets()
    }

    fun logout() {
        viewModelScope.launch {
            cacheDataSource.saveUserToken(null)
            _isAuthenticated.postValue(false)
        }
    }

    fun loadTickets() {
        _isLoading.postValue(true)
        viewModelScope.launch {
            val list = getTicketList.load() ?: return@launch
            _ticketList.postValue(sortTickets(tickets = list))
            _isLoading.postValue(false)
        }
    }

    private fun sortTickets(
        _sortType: TicketSortType? = null,
        tickets: List<TicketParameter>
    ): List<TicketParameter> {
        val sortType = _sortType ?: sortType.value
        return if (sortType is AscendingOrder) {
            tickets.sortedWith(compareBy { it.cost })
        } else {
            tickets.sortedWith(compareBy { it.cost }).reversed()
        }
    }

    fun selectFilter(sortType: TicketSortType) {
        _sortType.postValue(sortType)
        _ticketList.postValue(sortTickets(sortType, _ticketList.value ?: emptyList()))
    }
}