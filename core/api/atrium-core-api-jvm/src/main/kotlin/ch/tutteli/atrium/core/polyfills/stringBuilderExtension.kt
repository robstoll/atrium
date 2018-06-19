package ch.tutteli.atrium.core.polyfills

import kotlin.text.appendln as kotlinAppendLn

actual fun StringBuilder.appendln(): StringBuilder = this.kotlinAppendLn()
