package ch.tutteli.atrium.core.polyfills

private val lineSeparator = System.getProperty("line.separator") ?: '\n'
actual fun StringBuilder.appendln(): StringBuilder = append(lineSeparator)
