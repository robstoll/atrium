package ch.tutteli.atrium.core.polyfills

import kotlin.reflect.KClass

actual val KClass<*>.fullName get(): String = simpleName ?: "<unknown class name>"

actual fun <T: Any> KClass<T>.cast(any: Any?): T = TODO("implement reflective cast in javascript")
