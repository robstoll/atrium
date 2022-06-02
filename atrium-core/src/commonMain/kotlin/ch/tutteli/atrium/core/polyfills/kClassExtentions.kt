package ch.tutteli.atrium.core.polyfills

import kotlin.reflect.KClass

/**
 * The full name of this [KClass].
 *
 * The full name consists of the class name and, depending on the platform,
 * additional information such as the package name.
 *
 * Similar to [KClass.qualifiedName] but returns `String` and not `String?` which means it also returns a name
 * for anonymous classes (in which case [KClass.qualifiedName] returns `null`).
 */
expect val KClass<*>.fullName: String

/**
 * Returns the full name of this [KClass].
 *
 * The full name consists of the class name and, depending on the platform,
 * additional information such as the package name.
 *
 * [obj] can be used to retrieve more information
 *
 * Similar to [KClass.qualifiedName] but returns also names for anonymous classes
 * (in which case [KClass.qualifiedName] returns `null`).
 *
 * @param obj The object from which this [KClass] was created of.
 * @return The full name of this [KClass].
 */
expect fun <T : Any> KClass<out T>.fullName(obj: T): String

/**
 * Casts the given [any] to the type of the given [KClass].
 */
//TODO should be provided by kotlin IMO: https://youtrack.jetbrains.com/issue/KT-27735
expect fun <T : Any> KClass<T>.cast(any: Any?): T
