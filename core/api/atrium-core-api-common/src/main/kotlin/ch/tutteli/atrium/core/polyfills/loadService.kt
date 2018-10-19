package ch.tutteli.atrium.core.polyfills

import ch.tutteli.kbox.forEachRemaining
import kotlin.reflect.KClass

/**
 * Loads the service for the given [kClass] and throws an [IllegalStateException] if it finds more than one.
 *
 * @return The loaded service.
 *
 * @throws NoSuchElementException in case there is no service found for [kClass].
 * @throws IllegalStateException in case there is more than one service found for [kClass].
 */
expect fun <T : Any> loadSingleService(kClass: KClass<T>): T

/**
 * Loads all available service for the given [kClass].
 *
 * @return The loaded services as a [Sequence].
 */
expect fun <T : Any> loadServices(kClass: KClass<T>): Sequence<T>

/**
 * Returns the single service contained in [itr] and throws if there is any ore more than one.
 *
 * @throws NoSuchElementException in case there is no service found for [kClass].
 * @throws IllegalStateException in case there is more than one service found for [kClass].
 */
fun <T : Any> useSingleService(kClass: KClass<T>, itr: Iterator<T>): T {
    if (!itr.hasNext()) throw NoSuchElementException("Could not find any implementation for ${kClass.fullName}")

    val service = itr.next()
    check(!itr.hasNext()) {
        val sb = StringBuilder()
        itr.forEachRemaining {
            sb.appendln()
            sb.append(it::class.fullName)
        }
        "Found more than one implementation for ${kClass.fullName}:\n${service::class.fullName}$sb"
    }
    return service
}
