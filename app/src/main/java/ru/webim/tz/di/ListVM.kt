package ru.webim.tz.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.webim.tz.presentation.tickets.ListViewModel

@Module
abstract class ListVM {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    internal abstract fun bindListViewModel(viewModel: ListViewModel): ViewModel
}