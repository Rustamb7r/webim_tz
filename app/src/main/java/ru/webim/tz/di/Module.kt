package ru.webim.tz.di

import dagger.Module

@Module(
    subcomponents = [
        TicketListComponent::class,
        AuthComponent::class
    ]
)
class Module