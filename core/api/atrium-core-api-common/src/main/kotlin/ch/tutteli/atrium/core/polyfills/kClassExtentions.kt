package ch.tutteli.atrium.core.polyfills

import kotlin.reflect.KClass

/**
 * Returns the class name including package.
 *
 * Similar to [KClass.qualifiedName] but returns `String` and not `String?` which means it also returns a name
 * for anonymous classes (in which case [KClass.qualifiedName] returns `null`).
 */
expect val KClass<*>.fullName: String

/**
 * Casts the given [any] to the type of the given [KClass].
 */
expect fun <T: Any> KClass<T>.cast(any: Any?): T
