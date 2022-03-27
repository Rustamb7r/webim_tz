package ru.webim.tz.di.list

import dagger.Subcomponent
import ru.webim.tz.presentation.tickets.TicketListFragment

@TicketListFragmentScope
@Subcomponent(
    modules = [
        CoreTicketListModule::class,
        TicketListViewModelModule::class
    ]
)
interface TicketListComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(): TicketListComponent
    }

    fun inject(ticketListFragment: TicketListFragment)

}