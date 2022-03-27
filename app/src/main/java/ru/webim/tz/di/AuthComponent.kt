package ru.webim.tz.di

import dagger.Subcomponent
import ru.webim.tz.presentation.authorization.AuthorizationFragment

@Scope
@Subcomponent(
    modules = [
        ViewModel::class
    ]
)
interface AuthComponent {
    @Subcomponent.Factory
    interface Factory {

        fun create(): AuthComponent
    }

    fun inject(authFragment: AuthorizationFragment)
}