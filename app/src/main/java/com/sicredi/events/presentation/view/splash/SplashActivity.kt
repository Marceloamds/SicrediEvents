package com.sicredi.events.presentation.view.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sicredi.events.R
import com.sicredi.events.databinding.ActivitySplashBinding
import com.sicredi.events.presentation.util.extension.onGoTo
import com.sicredi.events.presentation.util.extension.transparentStatusAndNavigation
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.root.transparentStatusAndNavigation(window)
        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.goTo.observe(this, ::onGoTo)
    }
}