package ch.tutteli.atrium.core.robstoll.lib.checking

import ch.tutteli.atrium.core.polyfills.stack

internal actual fun <T : Throwable> adjustStack(t: T): T {
    val prefix = "    at "
    t.asDynamic().stack = t.stack.asSequence()
        .takeWhile { !it.contains(Regex("[\\\\|/]mocha[\\\\|/]")) }
        .joinToString("\n$prefix", prefix)
    return t
}
