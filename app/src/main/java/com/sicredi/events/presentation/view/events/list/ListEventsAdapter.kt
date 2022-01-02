package com.sicredi.events.presentation.view.events.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sicredi.events.domain.entity.event.Event

class ListEventsAdapter(
    private val onEventClickedCallback: (Event) -> Unit,
) : ListAdapter<Event, EventViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.setupBinding(
            getItem(position),
            onEventClickedCallback,
        )
    }

    companion object DiffUtilCallback : DiffUtil.ItemCallback<Event>() {
        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
        }
    }
}