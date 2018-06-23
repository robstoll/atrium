package ch.tutteli.atrium.core.polyfills

import kotlin.reflect.KClass

actual fun <T : Any> loadSingleService(klass: KClass<T>): T {
    TODO("need a concept for Services")
}

actual fun <T : Any> loadServices(klass: KClass<T>): Sequence<T> {
    TODO("need a concept for Services")
}
