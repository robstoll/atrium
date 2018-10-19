package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.core.SingleServiceLoader
import java.util.*
import kotlin.reflect.KClass

actual fun <T: Any> loadSingleService(kClass: KClass<T>): T
    = SingleServiceLoader.load(kClass.java)

actual fun <T : Any> loadServices(kClass: KClass<T>): Sequence<T>
    = ServiceLoader.load(kClass.java).asSequence()
