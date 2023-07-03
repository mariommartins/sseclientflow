package com.sseclientflow.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import com.sseclientflow.ui.main.widgets.MainContainer
import com.sseclientflow.ui.theme.SSEClientFlowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SSEClientFlowTheme {
                val eventHistory by viewModel.eventsState
                MainContainer(eventHistory)
            }
        }
    }
}
