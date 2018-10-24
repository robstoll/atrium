package ch.tutteli.atrium.core.polyfills

import kotlin.reflect.KClass

actual val KClass<*>.fullName get(): String = simpleName ?: "<unknown class name>"


actual fun <T : Any> KClass<T>.cast(any: Any?): T {
    //TODO according to https://youtrack.jetbrains.com/issue/KT-23178
    // this == subject::class added because isInstance doesn't work with reified simple types
    return if (any != null && (this == any::class || isInstance(any))) {
        @Suppress("UNCHECKED_CAST")
        val t = any as T
        t
    } else {
        val className = if (any != null) any::class.fullName else "null"
        throw ClassCastException("Cannot cast $className to ${this.fullName}.")
    }
}
