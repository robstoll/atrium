package ch.tutteli.atrium.core.polyfills

actual fun StringBuilder.appendln(): StringBuilder = append("\n")
