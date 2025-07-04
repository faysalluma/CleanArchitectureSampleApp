package com.groupec.cleanarchitecturesampleapp


import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.groupec.cleanarchitecturesampleapp.core.designsystem.theme.CleanArchitectureSampleAppTheme
import com.groupec.cleanarchitecturesampleapp.ui.MainScreen
import com.groupec.retrofitcleanarchictecturesampleapp.remote.utils.ConnectivityManagerUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val connectivityManagerUtils by lazy { ConnectivityManagerUtils(this, lifecycleScope) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // throw RuntimeException("Test Crash") // Force a crash
        setContent {
            val connectionState by connectivityManagerUtils.connectionAsStateFlow.collectAsStateWithLifecycle()
            CleanArchitectureSampleAppTheme {
                MainScreen(connectionState)
            }
        }
    }
}


