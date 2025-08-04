package io.github.numq.composedesktopwindowdecoration.application

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import io.github.numq.composedesktopwindowdecoration.decoration.WindowDecoration
import io.github.numq.composedesktopwindowdecoration.decoration.WindowDecorationColors
import io.github.numq.composedesktopwindowdecoration.theme.CustomTheme

private const val APP_NAME = "Compose desktop window decoration"

private val minimumWindowSize = DpSize(768.dp, 512.dp)

fun main() = application {
    val isSystemInDarkTheme = isSystemInDarkTheme()

    val (isDarkTheme, setIsDarkTheme) = remember(isSystemInDarkTheme) {
        mutableStateOf(isSystemInDarkTheme)
    }

    CustomTheme(isDarkTheme = isDarkTheme) {
        WindowDecoration(
            isDarkTheme = isDarkTheme,
            setIsDarkTheme = setIsDarkTheme,
            initialWindowSize = minimumWindowSize,
            minimumWindowSize = minimumWindowSize,
            title = {
                Text(APP_NAME, color = MaterialTheme.colorScheme.primary)
            },
            content = {
                val windowDecorationColors = remember { WindowDecorationColors() }

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(
                        modifier = Modifier.width(IntrinsicSize.Max),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(space = 8.dp, alignment = Alignment.CenterVertically)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(
                                space = 32.dp, alignment = Alignment.CenterHorizontally
                            ), verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                "Toggle light-on-dark color scheme", color = windowDecorationColors.switchSchemeButton()
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(
                                    space = 8.dp, alignment = Alignment.CenterHorizontally
                                )
                            ) {
                                windowDecorationColors.switchSchemeButton().let { tint ->
                                    Icon(Icons.Default.LightMode, null, tint = tint)
                                    Icon(Icons.Default.DarkMode, null, tint = tint)
                                }
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Minimize window", color = windowDecorationColors.minimizeButton())
                            Icon(Icons.Default.Minimize, null, tint = windowDecorationColors.minimizeButton())
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Toggle fullscreen window mode", color = windowDecorationColors.fullscreenButton())
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(
                                    space = 8.dp, alignment = Alignment.CenterHorizontally
                                )
                            ) {
                                windowDecorationColors.fullscreenButton().let { tint ->
                                    Icon(Icons.Default.Fullscreen, null, tint = tint)
                                    Icon(Icons.Default.FullscreenExit, null, tint = tint)
                                }
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Close window", color = windowDecorationColors.closeButton())
                            Icon(Icons.Default.Close, null, tint = windowDecorationColors.closeButton())
                        }
                    }
                }
            })
    }
}