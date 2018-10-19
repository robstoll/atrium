package ch.tutteli.atrium.core

import ch.tutteli.atrium.core.polyfills.appendln
import java.util.*
import kotlin.NoSuchElementException

/**
 * Loads a service vai [ServiceLoader] for a given [Class] and throws an [IllegalStateException]
 * in case it finds more than one service.
 */
object SingleServiceLoader {

    /**
     * Loads a service vai [ServiceLoader] for a given [Class] and throws an [IllegalStateException]
     * in case it finds more than one service.
     *
     * @param clazz The service represented by a [Class].
     * @return The loaded service
     *
     * @throws IllegalStateException in case none or more than one service is found for the given [clazz]
     */
    fun <T : Any> load(clazz: Class<T>): T {
        val itr = ServiceLoader.load(clazz).iterator()
        if (!itr.hasNext()) throw NoSuchElementException("Could not find any implementation for ${clazz.name}")

        val service = itr.next()
        check(!itr.hasNext()) {
            val sb = StringBuilder()
            itr.forEachRemaining {
                sb.appendln()
                sb.append(it::class.java.name)
            }
            "Found more than one implementation for ${clazz.name}:\n${service::class.java.name}$sb"
        }
        return service
    }
}
