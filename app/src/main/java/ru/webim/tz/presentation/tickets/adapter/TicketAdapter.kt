package ru.webim.tz.presentation.tickets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.webim.tz.databinding.ItemTicketBinding
import ru.webim.tz.domain.model.Ticket

class TicketAdapter : ListAdapter<Ticket, TicketViewHolder>(DiffCallback()) {

    private class DiffCallback : DiffUtil.ItemCallback<Ticket>() {
        override fun areItemsTheSame(oldTicket: Ticket, newTicket: Ticket): Boolean {
            return oldTicket.dest == oldTicket.dest && oldTicket.from == oldTicket.from
                    && oldTicket.date == oldTicket.date
        }

        override fun areContentsTheSame(oldTicket: Ticket, newTicket: Ticket): Boolean {
            return oldTicket == newTicket
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val addressBinding =
            ItemTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TicketViewHolder(addressBinding)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}