package ru.webim.tz.di.auth

import dagger.Subcomponent
import ru.webim.tz.presentation.auth.AuthFragment

@AuthFragmentScope
@Subcomponent(
    modules = [
        AuthViewModelModule::class
    ]
)
interface AuthComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): AuthComponent
    }

    fun inject(authFragment: AuthFragment)

}