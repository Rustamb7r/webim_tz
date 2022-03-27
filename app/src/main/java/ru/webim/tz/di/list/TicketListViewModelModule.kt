package ru.webim.tz.di.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.webim.tz.di.core.ViewModelFactory
import ru.webim.tz.di.core.ViewModelKey
import ru.webim.tz.presentation.tickets.TicketListViewModel

@Module
abstract class TicketListViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TicketListViewModel::class)
    internal abstract fun bindListViewModel(viewModel: TicketListViewModel): ViewModel
}