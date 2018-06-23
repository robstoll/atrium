package ch.tutteli.atrium.core.polyfills

import kotlin.reflect.KClass

/**
 * Returns the class name including package.
 */
expect val KClass<*>.fullName: String
