package ch.tutteli.atrium.core.polyfills
import kotlin.reflect.KClass

/**
 * Loads the service for the given [kClass] and throws an [IllegalStateException] if it finds more than one.
 *
 * @return The loaded service.
 * @throws IllegalStateException in case more than one service is found.
 */
expect fun <T: Any> loadSingleService(kClass: KClass<T>): T

/**
 * Loads all available service for the given [kClass].
 *
 * @return The loaded services as a [Sequence].
 */
expect fun <T: Any> loadServices(kClass: KClass<T>): Sequence<T>
