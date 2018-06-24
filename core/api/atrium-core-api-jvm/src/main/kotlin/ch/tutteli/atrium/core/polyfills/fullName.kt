package ch.tutteli.atrium.core.polyfills

import kotlin.reflect.KClass

/**
 * Returns [KClass.java].[name][Class.name].
 *
 * In contrast to [KClass.qualifiedName], this shows if a primitive type is used or a boxed type
 * (e.g. `int` vs. `Integer`).
 */
actual val KClass<*>.fullName: String
    get() = if (!java.isPrimitive) qualifiedName ?: java.name else java.name
