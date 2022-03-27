package ru.webim.tz.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.webim.tz.di.auth.AuthComponent
import ru.webim.tz.di.core.CoreModule
import ru.webim.tz.di.core.NetworkModule
import ru.webim.tz.di.core.SubComponentsModule
import ru.webim.tz.di.list.TicketListComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        CoreModule::class,
        SubComponentsModule::class
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