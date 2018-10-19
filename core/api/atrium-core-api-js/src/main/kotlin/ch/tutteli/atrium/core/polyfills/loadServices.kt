package ch.tutteli.atrium.core.polyfills

import kotlin.reflect.KClass

private val serviceRegistry = mutableMapOf<KClass<*>, HashSet<Any>>()

actual fun <T : Any> loadSingleService(kClass: KClass<T>): T =
    useSingleService(kClass, loadServices(kClass).iterator())


actual fun <T : Any> loadServices(kClass: KClass<T>): Sequence<T> {
    @Suppress("UNCHECKED_CAST" /* we have a homogeneous map but make sure insertions are kclass */)
    val set = serviceRegistry[kClass] as Set<T>?
    return set?.asSequence() ?: emptySequence()
}

/**
 * Registers the given [service] for the service of type [T].
 */
inline fun <reified T : Any> registerService(service: T) = registerService(T::class, service)

/**
 * Registers the given [service] for the given [serviceInterface].
 */
fun <T : Any> registerService(serviceInterface: KClass<T>, service: T) {
    val services = serviceRegistry.getOrPut(serviceInterface) { hashSetOf() }
    services.add(service)
}
