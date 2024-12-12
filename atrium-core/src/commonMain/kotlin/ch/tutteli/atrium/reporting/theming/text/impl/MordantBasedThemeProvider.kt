package ch.tutteli.atrium.reporting.theming.text.impl

import ch.tutteli.atrium.reporting.theming.text.TextThemeProvider
import com.github.ajalt.mordant.rendering.*
import com.github.ajalt.mordant.terminal.Terminal

internal abstract class MordantBasedThemeProvider(
    private val terminal: Terminal
) : TextThemeProvider {
    protected abstract val styleIdToTextStyle: Map<String, TextStyle>

    override val supportsAnsiColours: Boolean = terminal.info.ansiLevel != AnsiLevel.NONE

    override fun render(unstyledString: String, styleId: String): String? =
        styleIdToTextStyle[styleId]?.let { textStyle ->
            val spans = mutableListOf<Span>()
            var sb = StringBuilder()
            var isSbForWhitespace = false
            unstyledString.forEach {
                val isWhiteSpace = it.isWhitespace()
                if (isSbForWhitespace != isWhiteSpace) {
                    if (sb.isNotEmpty()) {
                        spans.add(Span.word(sb.toString(), textStyle))
                        sb = StringBuilder()
                    }
                    isSbForWhitespace = isWhiteSpace
                }
                sb.append(it)
            }
            spans.add(Span.word(sb.toString(), textStyle))
            terminal.render(Lines(listOf(Line(spans))))
        }
}
