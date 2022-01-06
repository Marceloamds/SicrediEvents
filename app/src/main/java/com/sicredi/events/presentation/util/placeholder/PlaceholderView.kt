package com.sicredi.events.presentation.util.placeholder

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.sicredi.events.databinding.LoadingPlaceholderBinding

class PlaceholderView constructor(
    context: Context,
    attrs: AttributeSet
) : FrameLayout(context, attrs) {

    private lateinit var binding: LoadingPlaceholderBinding
    private val layoutInflater by lazy { LayoutInflater.from(context) }

    init {
        loadBinding()
    }

    fun setPlaceholder(placeholder: Placeholder?) {
        placeholder?.let { setBaseVariables(it) }
    }

    private fun loadBinding() {
        binding = LoadingPlaceholderBinding.inflate(layoutInflater, this, true)
    }

    private fun setBaseVariables(placeholder: Placeholder) {
        binding.run {
            visible = placeholder.visible
            progressVisible = placeholder.progressVisible
        }
    }
}