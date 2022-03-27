package ru.webim.tz.presentation.tickets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.webim.tz.databinding.ItemTicketBinding
import ru.webim.tz.model.TicketParameter

class TicketAdapter : ListAdapter<TicketParameter, TicketViewHolder>(DiffCallback()) {

    private class DiffCallback : DiffUtil.ItemCallback<TicketParameter>() {
        override fun areItemsTheSame(oldTicket: TicketParameter, newTicket: TicketParameter): Boolean {
            return oldTicket.dest == oldTicket.dest && oldTicket.from == oldTicket.from
                    && oldTicket.date == oldTicket.date
        }

        override fun areContentsTheSame(oldTicket: TicketParameter, newTicket: TicketParameter): Boolean {
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