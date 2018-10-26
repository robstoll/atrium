package ch.tutteli.atrium.core.polyfills

import kotlin.reflect.KClass

/**
 * [KClass.simpleName] or `object: <unknown>` in case it is an anonymous object.
 */
actual val KClass<*>.fullName get(): String = simpleNameWithJsFallback() ?: "<unknown> (js: ${js.name})"

private fun KClass<*>.simpleNameWithJsFallback() = simpleName ?: jsFallback()

private fun KClass<*>.jsFallback(): String? =
    when {
        js.name == "Boolean" -> "Boolean"
        //TODO no need for <unknown> in case https://youtrack.jetbrains.com/issue/KT-27828 is fixed.
        !js.name.contains("$") -> "<unknown> (js: ${js.name})"
        else -> null
    }

/**
 * [KClass.simpleName] or `object: Class/Interface` in case it is an anonymous object.
 *
 * It tries to retrieve the class or interface of the [obj] in case [KClass.simpleName] is `null`.
 *
 * @param obj The object from which this [KClass] was created of.
 * @return The full name of this [KClass].
 */
actual fun <T : Any> KClass<out T>.fullName(obj: T): String =
    simpleNameWithJsFallback() ?: objFallback(obj)

private fun KClass<*>.objFallback(@Suppress("UNUSED_PARAMETER") obj: Any): String =
    js(
        "var proto = Object.getPrototypeOf(obj);" +
            "var constructor = proto != null ? proto.constructor : null;" +
            "var name = \"`object: \";" +
            "if (constructor != null && '\$metadata$' in constructor && constructor.\$metadata\$.interfaces.length == 1) {" +
            "name += constructor.\$metadata$.interfaces[0].name + \"`\"" +
            "} else {" +
            "name += \"<unknown>`\";" +
            "}" +
            "name"
    ) as String + " (js: ${js.name})"



actual fun <T : Any> KClass<T>.cast(any: Any?): T {
    //TODO remove `this == any::class` if https://youtrack.jetbrains.com/issue/KT-23178 is fixed
    // added because isInstance doesn't work with reified primitive types
    return if (any != null && (this == any::class || isInstance(any))) {
        @Suppress("UNCHECKED_CAST")
        val t = any as T
        t
    } else {
        val className = if (any != null) any::class.fullName(any) else "null"
        throw ClassCastException("Cannot cast $className to ${this.fullName}.")
    }
}
