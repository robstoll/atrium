package ch.tutteli.atrium.core.polyfills

import kotlin.reflect.KClass
import kotlin.reflect.full.cast as kotlinCast

/**
 * Returns [KClass.java].[name][Class.name].
 *
 * In contrast to [KClass.qualifiedName], this shows if a primitive type is used or a boxed type
 * (e.g. `int` vs. `Integer`) and returns also a name for anonymous classes.
 */
actual val KClass<*>.fullName: String
    get() = if (!java.isPrimitive) qualifiedName ?: java.name else java.name

/**
 * Casts the given [any] with help of [kotlin.reflect.full.cast] to the specified [T].
 */
actual fun <T : Any> KClass<T>.cast(any: Any?) = kotlinCast(any)

/**
 * Returns [KClass.java].[name][Class.name].
 *
 * In contrast to [KClass.qualifiedName], this shows if a primitive type is used or a boxed type
 * (e.g. `int` vs. `Integer`) and returns also a name for anonymous classes.
 *
 * @param obj Is ignored.
 * @return same as the property [fullName].
 */
actual fun <T : Any> KClass<out T>.fullName(obj: T): String = fullName
