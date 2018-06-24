package ch.tutteli.atrium.core.polyfills

import kotlin.reflect.KClass

/**
 * Returns the class name including package.
 *
 * Similar to [KClass.qualifiedName] but returns `String` and not `String?` and also returns a name for anonymous
 * classes (in which case [KClass.qualifiedName] returns `null`).
 */
expect val KClass<*>.fullName: String
