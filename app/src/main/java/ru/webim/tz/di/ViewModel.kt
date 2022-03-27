package ru.webim.tz.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.webim.tz.presentation.authorization.AuthorizationViewModel

@Module
abstract class ViewModel {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AuthorizationViewModel::class)
    internal abstract fun bindAuthViewModel(authViewModel: AuthorizationViewModel): ViewModel
}