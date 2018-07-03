package ch.tutteli.atrium.core.polyfills

/**
 * Stands for `kotlin.jvm.JvmName` on the JVM platform and a dummy annotation on the other platforms.
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.FILE)
@Retention(AnnotationRetention.BINARY)
expect annotation class JvmName(val name: String)

/**
 * Stands for `kotlin.jvm.JvmMultifileClass` on the JVM platform and a dummy annotation on the other platforms.
 */
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
expect annotation class JvmMultifileClass()
