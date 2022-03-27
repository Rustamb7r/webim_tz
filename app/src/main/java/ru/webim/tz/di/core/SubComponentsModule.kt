package ru.webim.tz.di.core

import dagger.Module
import ru.webim.tz.di.auth.AuthComponent
import ru.webim.tz.di.list.TicketListComponent

@Module(
    subcomponents = [
        TicketListComponent::class,
        AuthComponent::class
    ]
)
class SubComponentsModule