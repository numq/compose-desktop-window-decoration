# Compose desktop window decoration

[![Kotlin](https://img.shields.io/badge/kotlin-2.2.0-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Compose](https://img.shields.io/badge/compose%20desktop-1.8.2-blue)](https://www.jetbrains.com/lp/compose-mpp/)

![Preview](media/preview.png)

A template for creating custom window decorations in Jetpack Compose Desktop applications. Provides complete control
over window chrome while maintaining native functionality.

## Key Features

- 🖼️ **Custom title bar** with:
    - Draggable area
    - Double-click to maximize
    - Themed controls
- 🎨 **Material 3 ready**:
    - Light/dark mode support
    - Custom color schemes
- ⚙️ **Window controls**:
    - Theme switcher
    - Minimize/maximize/close
- 🖱️ **Native integration**:
    - Smooth dragging
    - DPI-aware scaling

## Installation

1. Add the icons dependency:
    ```kotlin
    implementation("org.jetbrains.compose.material:material-icons-extended:1.7.3")
    ```

2. Copy these components to your project:

- [WindowDecoration.kt](composeApp/src/jvmMain/kotlin/io/github/numq/composedesktopwindowdecoration/decoration/WindowDecoration.kt) -
  Main decoration logic
- [WindowDecorationColors.kt](composeApp/src/jvmMain/kotlin/io/github/numq/composedesktopwindowdecoration/decoration/WindowDecorationColors.kt) -
  Color scheme
- [WindowDecorationState.kt](composeApp/src/jvmMain/kotlin/io/github/numq/composedesktopwindowdecoration/decoration/WindowDecorationState.kt) -
  Window state

## Basic usage

> [!IMPORTANT]
> `WindowDecoration` content area has a transparent background by default, so you must explicitly set the background
> color

```kotlin
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
            modifier = Modifier.fillMaxSize(),
            backgroundColor = MaterialTheme.colorScheme.background // Set background explicitly
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                // Your content is here
            }
        }
    })

WindowDecoration(
    minWindowSize = minWindowSize,
    decorationHeight = decorationHeight,
    windowDecorationColors = WindowDecorationColors(
        surface = MaterialTheme.colorScheme.surface,
        switchSchemeButton = MaterialTheme.colorScheme.primary,
        // ...
    ),
    isDarkTheme = isDarkTheme,
    setIsDarkTheme = setIsDarkTheme,
    close = { exitProcess(0) },
    decoration = {
        Text("Your application name", color = MaterialTheme.colorScheme.primary)
    },
    content = { windowState ->
        Surface(
            color = MaterialTheme.colorScheme.background // Set background color
        ) {
            YourContent(windowState)
        }
    }
)
```

> [!TIP]
> Use `WindowState` to access the current state of a window

## Customization

Hide controls:

  ```kotlin
  WindowDecorationColors(
    closeButton = Color.Unspecified // Hides button
)
  ```

## Example

See complete example:
[Application.kt](composeApp/src/jvmMain/kotlin/io/github/numq/composedesktopwindowdecoration/application/Application.kt)