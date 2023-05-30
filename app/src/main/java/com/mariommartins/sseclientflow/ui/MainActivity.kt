package com.mariommartins.sseclientflow.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mariommartins.sseclientflow.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeEvents()
    }

    private fun observeEvents() {
        viewModel.eventTypeLiveData.observe(this) { binding.mainEventType.text = it }
        viewModel.eventContentLiveData.observe(this) { content ->
            binding.mainEventContent.apply { content?.let { text = "$text\n$it" } }
        }
    }
}
