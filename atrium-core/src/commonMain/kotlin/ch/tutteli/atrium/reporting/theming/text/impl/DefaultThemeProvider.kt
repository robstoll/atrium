package ch.tutteli.atrium.reporting.theming.text.impl

import ch.tutteli.atrium.reporting.theming.text.Style
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal

internal class DefaultThemeProvider(terminal: Terminal) : MordantBasedThemeProvider(terminal) {
    override val styleIdToTextStyle: Map<String, TextStyle> = mapOf(
        Style.USAGE_HINT.styleId to TextStyle(TextColors.yellow, bold = true),
        Style.DEBUG_INFO.styleId to TextColors.blue,
        Style.FAILURE.styleId to TextColors.red,
        Style.FEATURE.styleId to TextColors.cyan,
        Style.INFORMATION_SOURCE.styleId to TextStyle(TextColors.brightBlue, bold = true),
        Style.SUCCESS.styleId to TextColors.green,
    )
}
