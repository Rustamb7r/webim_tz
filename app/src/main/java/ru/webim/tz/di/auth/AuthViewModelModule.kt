package ru.webim.tz.di.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.webim.tz.di.core.ViewModelFactory
import ru.webim.tz.di.core.ViewModelKey
import ru.webim.tz.presentation.auth.AuthViewModel

@Module
abstract class AuthViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    internal abstract fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel
}