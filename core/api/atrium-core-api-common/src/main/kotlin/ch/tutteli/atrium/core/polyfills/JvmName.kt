package ch.tutteli.atrium.core.polyfills

/**
 * Stands for `kotlin.jvm.JvmName` on the JVM platform and a dummy annotation on the other platforms.
 */
expect annotation class JvmName(val name: String)
