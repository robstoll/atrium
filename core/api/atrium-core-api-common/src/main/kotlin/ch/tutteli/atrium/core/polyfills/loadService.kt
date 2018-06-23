package ch.tutteli.atrium.core.polyfills
import kotlin.reflect.KClass

/**
 * Loads the service for the given [klass] and throws an [IllegalStateException] if it finds more than one.
 *
 * @return The loaded service.
 * @throws IllegalStateException in case more than one service is found.
 */
expect fun <T: Any> loadSingleService(klass: KClass<T>): T

/**
 * Loads all available service for the given [klass].
 *
 * @return The loaded services as a [Sequence].
 */
expect fun <T: Any> loadServices(klass: KClass<T>): Sequence<T>
