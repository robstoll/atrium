package ch.tutteli.atrium.core.polyfills

import kotlin.reflect.KClass

/**
 * [KClass.simpleName] or `object: <unknown>` in case it is an anonymous object.
 */
actual val KClass<*>.fullName get(): String = simpleName ?: "object: <unknown> {}"

/**
 * [KClass.simpleName] or `object: Class/Interface` in case it is an anonymous object.
 *
 * It tries to retrieve the class or interface of the [obj] in case [KClass.simpleName] is `null`.
 *
 * @param obj The object from which this [KClass] was created of.
 * @return The full name of this [KClass].
 */
actual fun <T : Any> KClass<out T>.fullName(obj: T): String = simpleName ?: getAnonymousObjectName(obj)

private fun getAnonymousObjectName(@Suppress("UNUSED_PARAMETER") obj: Any): String =
    js(
        "var proto = Object.getPrototypeOf(obj);" +
            "var constructor = proto != null ? proto.constructor : null;" +
            "var name = \"object: \";" +
            "if (constructor != null && '\$metadata$' in constructor && constructor.\$metadata\$.interfaces.length == 1) {" +
            "name += constructor.\$metadata$.interfaces[0].name" +
            "} else {" +
            "name += \"<unknown>\";" +
            "}" +
            "name"
    ) as String


actual fun <T : Any> KClass<T>.cast(any: Any?): T {
    //TODO according to https://youtrack.jetbrains.com/issue/KT-23178
    // this == any::class added because isInstance doesn't work with reified simple types
    return if (any != null && (this == any::class || isInstance(any))) {
        @Suppress("UNCHECKED_CAST")
        val t = any as T
        t
    } else {
        val className = if (any != null) any::class.fullName(any) else "null"
        throw ClassCastException("Cannot cast $className to ${this.fullName}.")
    }
}
