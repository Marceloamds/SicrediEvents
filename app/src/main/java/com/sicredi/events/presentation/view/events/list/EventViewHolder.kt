package com.sicredi.events.presentation.view.events.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sicredi.events.R
import com.sicredi.events.databinding.ItemEventBinding
import com.sicredi.events.domain.entity.event.Event
import com.sicredi.events.presentation.util.extension.getDayDescription
import com.sicredi.events.presentation.util.extension.load
import com.sicredi.events.presentation.util.extension.setSafeClickListener
import com.sicredi.events.presentation.util.extension.toMoneyString

class EventViewHolder(
    private var binding: ItemEventBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun setupBinding(
        event: Event,
        onEventClickedCallback: (Event) -> Unit,
    ) {
        with(binding) {
            root.setSafeClickListener { onEventClickedCallback(event) }
            textViewEventTitle.text = event.title
            textViewEventDescription.text = event.description
            textViewEventDate.text = event.date.getDayDescription(root.context)
            textViewEventPrice.text = event.price.toMoneyString(root.context)
            imageViewEventPoster.load(event.image, R.drawable.border_logo)
        }
    }

    companion object {
        fun inflate(parent: ViewGroup?) = EventViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent?.context),
                R.layout.item_event,
                parent,
                false
            )
        )
    }
}