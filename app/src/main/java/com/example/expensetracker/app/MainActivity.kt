package com.example.expensetracker.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.expensetracker.ui.screens.AddExpenseScreen
import com.example.expensetracker.ui.screens.ExpenseCard
import com.example.expensetracker.ui.screens.SplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        splashScreen.setKeepOnScreenCondition {
            false
        }

        setContent {
            val darkTheme = isSystemInDarkTheme()
            val colors = if (darkTheme) DarkColors else LightColors
            MaterialTheme(
                colorScheme = colors,
                content = {
                    var showSplash by remember { mutableStateOf(true) }

                    if (showSplash) {
                        SplashScreen(
                            onTimeout = { showSplash = false }
                        )
                    } else {
                        ExpenseCard()
                    }
                }
            )
        }
    }
}

val LightColors = lightColorScheme(
    primary = Color.Black,
//    secondary = Color(0xFF03DAC6),
//    background = Color.White,
//    onBackground = Color.Black,
//    surface = Color.White,
//    onPrimary = Color.White,
//    onSurface = Color(0xFFF8F8F4)
)

val DarkColors = darkColorScheme(
    primary = Color.White,
//    background = Color.Black,
//    onBackground = Color.White,
//    onSurface = Color(0xFF393D3D)

)