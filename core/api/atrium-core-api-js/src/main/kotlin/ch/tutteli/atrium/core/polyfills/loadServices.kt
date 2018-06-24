package ch.tutteli.atrium.core.polyfills

import kotlin.reflect.KClass

actual fun <T : Any> loadSingleService(kClass: KClass<T>): T {
    TODO("need a concept for Services")
}

actual fun <T : Any> loadServices(kClass: KClass<T>): Sequence<T> {
    TODO("need a concept for Services")
}
