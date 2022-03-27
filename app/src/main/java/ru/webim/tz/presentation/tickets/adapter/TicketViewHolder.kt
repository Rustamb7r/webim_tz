package ru.webim.tz.presentation.tickets.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.webim.tz.R
import ru.webim.tz.databinding.ItemTicketBinding
import ru.webim.tz.model.TicketParameter
import ru.webim.tz.presentation.util.toStringOrEmpty

class TicketViewHolder(private val binding: ItemTicketBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(ticket: TicketParameter) {
        binding.cost.text = ticket.cost.toStringOrEmpty()
        binding.fromToView.text =
            binding.root.context.getString(R.string.from_to_dest, ticket.from, ticket.dest)

        binding.date.text = ticket.date
    }
}