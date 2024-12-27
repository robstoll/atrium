package ch.tutteli.atrium.core.polyfills

actual fun Char.isHighSurrogate(): Boolean = Character.isHighSurrogate(this)

