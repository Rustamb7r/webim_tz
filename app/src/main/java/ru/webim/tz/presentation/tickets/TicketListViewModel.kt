package ru.webim.tz.presentation.tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.webim.tz.data.cache.abstraction.UserCacheDataSource
import ru.webim.tz.domain.model.AscendingOrder
import ru.webim.tz.domain.model.Ticket
import ru.webim.tz.domain.model.TicketSortType
import ru.webim.tz.interactors.GetTicketList
import javax.inject.Inject

class TicketListViewModel @Inject constructor(
    private val cacheDataSource: UserCacheDataSource,
    private val getTicketList: GetTicketList
) : ViewModel() {

    private val _isAuthenticated = MutableLiveData(false)
    val isAuthenticated: LiveData<Boolean> = _isAuthenticated

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _sortType = MutableLiveData<TicketSortType>(AscendingOrder())
    val sortType: LiveData<TicketSortType> = _sortType

    private val _ticketList = MutableLiveData<List<Ticket>>()
    val ticketList: LiveData<List<Ticket>> = _ticketList

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
        tickets: List<Ticket>
    ): List<Ticket> {
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