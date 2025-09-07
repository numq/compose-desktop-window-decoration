[![Kotlin](https://img.shields.io/badge/kotlin-2.2.0-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Compose](https://img.shields.io/badge/compose%20desktop-1.8.2-blue)](https://www.jetbrains.com/lp/compose-mpp/)

<h1 align="center">Compose desktop window decoration</h1>

<div align="center" style="display: grid; justify-content: center;">

|                                                                  🌟                                                                   |                  Support this project                   |               
|:-------------------------------------------------------------------------------------------------------------------------------------:|:-------------------------------------------------------:|
|  <img src="https://raw.githubusercontent.com/ErikThiart/cryptocurrency-icons/master/32/bitcoin.png" alt="Bitcoin (BTC)" width="32"/>  | <code>bc1qs6qq0fkqqhp4whwq8u8zc5egprakvqxewr5pmx</code> | 
| <img src="https://raw.githubusercontent.com/ErikThiart/cryptocurrency-icons/master/32/ethereum.png" alt="Ethereum (ETH)" width="32"/> | <code>0x3147bEE3179Df0f6a0852044BFe3C59086072e12</code> |
|  <img src="https://raw.githubusercontent.com/ErikThiart/cryptocurrency-icons/master/32/tether.png" alt="USDT (TRC-20)" width="32"/>   |     <code>TKznmR65yhPt5qmYCML4tNSWFeeUkgYSEV</code>     |

</div>

<p align="center"><img align="center" src="media/preview.png" alt="preview"></p>

A template for creating custom window decorations in Jetpack Compose Desktop applications. Provides complete control
over window chrome while maintaining native functionality.

## Key Features

- 🖼️ **Complete Window Customization**
    - Fully customizable title bar with draggable area
    - Double-click to maximize/restore
    - Themed window controls
    - Flexible layout options

- 🎨 **Material Design Integration**
    - Built-in light/dark theme support
    - Custom color schemes
    - Adaptive icon colors

- ⚙️ **Enhanced Window Controls**
    - Standard buttons (minimize/maximize/close)
    - Theme switcher
    - Custom controls section
    - Responsive layout

- 🖱️ **Native Behavior**
    - Smooth window dragging
    - DPI-aware scaling
    - Full multi-monitor support
    - Window state management

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
> If you use `SwingPanel`, some components may not be compatible with a transparent window - in this case the background
> remains transparent or flickers. Use the `isTransparent = false` flag.

```kotlin
WindowDecoration(
    isDarkTheme = isDarkTheme,
    setIsDarkTheme = { isDarkTheme = it },
    windowDecorationHeight = 48.dp,
    windowDecorationColors = WindowDecorationColors(
        decoration = { MaterialTheme.colorScheme.surface },
        content = { MaterialTheme.colorScheme.background },
        switchSchemeButton = { MaterialTheme.colorScheme.primary },
        minimizeButton = { MaterialTheme.colorScheme.primary },
        fullscreenButton = { MaterialTheme.colorScheme.primary },
        closeButton = { MaterialTheme.colorScheme.error }
    ),
    title = {
        Text("My Application", color = MaterialTheme.colorScheme.primary)
    },
    content = { windowState: WindowDecorationState ->
        // Main application content
        Surface(modifier = Modifier.fillMaxSize()) {
            Text(
                "Hello World!",
                style = MaterialTheme.typography.displayMedium
            )
        }
    }
)
```

## Customization

### WindowDecorationColors

Customize colors for different parts of the window:

```kotlin
windowDecorationColors = WindowDecorationColors().copy(
    decoration = { Color(0xFF2E3440) },         // Window decoration background
    content = { Color(0xFF3B4252) },            // Content area background
    switchSchemeButton = { Color.Unspecified }, // Hide button
    closeButton = { Color.Red }                 // Make close button red
)
```

### Window State

Access window state in your content:

```kotlin
content = { windowState: WindowDecorationState ->
    if (windowState.isFullscreen) {
        FullscreenContent()
    } else {
        NormalContent()
    }
}
```

### Custom Controls

Add your own controls:

```kotlin
controls = {
    Box(
        modifier = Modifier.fillMaxHeight().aspectRatio(1f).clickable {
            /* handle action */
        }, contentAlignment = Alignment.Center
    ) {
        Icon(
            Icons.Default.Settings, null, tint = MaterialTheme.colorScheme.primary
        )
    }
}
```

### Window Parameters

Control window behavior:

```kotlin
WindowDecoration(
    initialWindowPosition = WindowPosition(100.dp, 100.dp),
    initialWindowSize = DpSize(800.dp, 600.dp),
    minimumWindowSize = DpSize(400.dp, 300.dp),
    isResizable = true,
    isAlwaysOnTop = false,
    // ...
)
```

## Example

See complete example:
[Application.kt](composeApp/src/jvmMain/kotlin/io/github/numq/composedesktopwindowdecoration/application/Application.kt)
