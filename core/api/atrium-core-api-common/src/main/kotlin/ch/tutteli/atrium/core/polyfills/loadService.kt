package ch.tutteli.atrium.core.polyfills
import kotlin.reflect.KClass

expect fun <T: Any> loadServices(klass: KClass<T>): Sequence<T>
expect fun <T: Any> loadService(klass: KClass<T>): T
