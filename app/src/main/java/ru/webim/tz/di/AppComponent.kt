package ru.webim.tz.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        CoreModule::class,
        Module::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application,
            @BindsInstance applicationContext: Context
        ): AppComponent
    }

    fun authComponent(): AuthComponent.Factory

    fun listComponent(): TicketListComponent.Factory
}