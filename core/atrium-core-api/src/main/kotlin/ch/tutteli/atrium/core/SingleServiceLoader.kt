package ch.tutteli.atrium.core

import java.util.*

object SingleServiceLoader {
    fun <T : Any> load(clazz: Class<T>): T {
        val itr = ServiceLoader.load(clazz).iterator()
        check(itr.hasNext()) {
            "Could not find any implementation for ${clazz.name}"
        }
        val service = itr.next()
        check(!itr.hasNext()) {
            val sb = StringBuilder()
            itr.forEachRemaining {
                sb.appendln()
                sb.append(it::class.java.name)
            }
            "Found more than one implementation of ${clazz.name}, but found:\n${service::class.java.name}$sb"
        }
        return service
    }
}
