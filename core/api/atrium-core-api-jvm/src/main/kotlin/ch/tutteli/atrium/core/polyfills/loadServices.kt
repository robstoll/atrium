package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.core.SingleServiceLoader
import java.util.*
import kotlin.reflect.KClass

actual fun <T: Any> loadSingleService(klass: KClass<T>): T
    = SingleServiceLoader.load(klass.java)

actual fun <T : Any> loadServices(klass: KClass<T>): Sequence<T>
    = ServiceLoader.load(klass.java).iterator().asSequence()
