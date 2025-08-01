package io.github.numq.composedesktopdecoration.application

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import io.github.numq.composedesktopdecoration.decoration.WindowDecoration
import io.github.numq.composedesktopdecoration.decoration.WindowDecorationColors
import io.github.numq.composedesktopdecoration.theme.CustomTheme
import kotlin.system.exitProcess

private const val APP_NAME = "Compose desktop decoration"

private val minWindowSize = DpSize(768.dp, 512.dp)

private val decorationHeight = 32.dp

fun main() = application {
    val isSystemInDarkTheme = isSystemInDarkTheme()

    val (isDarkTheme, setIsDarkTheme) = remember(isSystemInDarkTheme) {
        mutableStateOf(isSystemInDarkTheme)
    }

    CustomTheme(isDarkTheme = isDarkTheme) {
        WindowDecoration(
            minWindowSize = minWindowSize,
            decorationHeight = decorationHeight,
            windowDecorationColors = WindowDecorationColors(
                surface = MaterialTheme.colorScheme.surface,
                switchSchemeButton = MaterialTheme.colorScheme.primary,
                minimizeButton = MaterialTheme.colorScheme.primary,
                fullscreenButton = MaterialTheme.colorScheme.primary,
                closeButton = MaterialTheme.colorScheme.primary
            ),
            isDarkTheme = isDarkTheme,
            setIsDarkTheme = setIsDarkTheme,
            close = { exitProcess(0) },
            decoration = {
                Text(APP_NAME, color = MaterialTheme.colorScheme.primary)
            },
            content = {
                Scaffold(
                    modifier = Modifier.fillMaxSize(), contentColor = MaterialTheme.colorScheme.surface
                ) { paddingValues ->
                    Column(
                        modifier = Modifier.fillMaxSize().padding(paddingValues),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(space = 8.dp, alignment = Alignment.CenterVertically)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                space = 8.dp, alignment = Alignment.CenterHorizontally
                            ), verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Toggle light-on-dark color scheme", color = MaterialTheme.colorScheme.primary)
                            Icon(Icons.Default.LightMode, null, tint = MaterialTheme.colorScheme.primary)
                            Icon(Icons.Default.DarkMode, null, tint = MaterialTheme.colorScheme.primary)
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                space = 8.dp, alignment = Alignment.CenterHorizontally
                            ), verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Minimize window", color = MaterialTheme.colorScheme.primary)
                            Icon(Icons.Default.Minimize, null, tint = MaterialTheme.colorScheme.primary)
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                space = 8.dp, alignment = Alignment.CenterHorizontally
                            ), verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Toggle fullscreen window mode",
                                color = MaterialTheme.colorScheme.primary
                            )
                            Icon(Icons.Default.Fullscreen, null, tint = MaterialTheme.colorScheme.primary)
                            Icon(Icons.Default.FullscreenExit, null, tint = MaterialTheme.colorScheme.primary)
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                space = 8.dp, alignment = Alignment.CenterHorizontally
                            ), verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Close window", color = MaterialTheme.colorScheme.primary)
                            Icon(Icons.Default.Close, null, tint = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            })
    }
}