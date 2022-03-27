package ru.webim.tz.di

import dagger.Subcomponent
import ru.webim.tz.presentation.tickets.ListFragment

@ListScope
@Subcomponent(
    modules = [
        ListModule::class,
        ListVM::class
    ]
)
interface TicketListComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): TicketListComponent
    }

    fun inject(ticketListFragment: ListFragment)

}