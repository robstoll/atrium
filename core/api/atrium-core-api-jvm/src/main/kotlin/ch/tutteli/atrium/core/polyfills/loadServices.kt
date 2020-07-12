@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.core.polyfills

import java.util.*
import kotlin.reflect.KClass

/**
 * Loads a service via [ServiceLoader] for a given [kClass] and throws if there is none or more services.
 *
 * @param kClass The service type.
 * @return The loaded service
 *
 * @throws NoSuchElementException in case there is no service found for [kClass].
 * @throws IllegalStateException in case there is more than one service found for [kClass].
 */
actual fun <T : Any> loadSingleService(kClass: KClass<T>): T =
    useSingleService(kClass, ServiceLoader.load(kClass.java).iterator())

actual fun <T : Any> loadServices(kClass: KClass<T>): Sequence<T> =
    ServiceLoader.load(kClass.java).asSequence()
