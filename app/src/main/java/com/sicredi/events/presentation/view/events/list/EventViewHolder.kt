package com.sicredi.events.presentation.view.events.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sicredi.events.R
import com.sicredi.events.databinding.ItemEventBinding
import com.sicredi.events.domain.entity.event.Event

class EventViewHolder(
    private var binding: ItemEventBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun setupBinding(
        event: Event,
        callback: (Event) -> Unit,
    ) {
        with(binding) {
            root.setOnClickListener { callback(event) }
            textViewEventTitle.text = event.title
            textViewEventReview.text = event.description
            setupLike(event)
        }
    }

    private fun setupLike(event: Event) {
        binding.buttonFavorite.setImageDrawable(
            ContextCompat.getDrawable(
                binding.root.context,
                R.drawable.ic_favorite
            )
        )
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