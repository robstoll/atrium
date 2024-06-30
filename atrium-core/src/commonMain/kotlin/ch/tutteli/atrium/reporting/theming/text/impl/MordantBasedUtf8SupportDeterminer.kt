package ch.tutteli.atrium.reporting.theming.text.impl

import ch.tutteli.atrium.reporting.theming.text.Utf8SupportDeterminer
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.terminal.Terminal

internal class MordantBasedUtf8SupportDeterminer(terminal: Terminal) : Utf8SupportDeterminer {
    override val isSupported: Boolean = run {
        // doesn't need to be true at all, we simply assume that if the terminal supports the following AnsiLevel,
        // then utf-8 is supported as well.
        terminal.info.ansiLevel == AnsiLevel.ANSI256 || terminal.info.ansiLevel == AnsiLevel.TRUECOLOR
    }
}
