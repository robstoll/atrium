package ch.tutteli.atrium.core.polyfills

/**
 * Without any effects on the JS Platform.
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.FILE)
@Retention(AnnotationRetention.BINARY)
actual annotation class JvmName actual constructor(actual val name: String)

/**
 * Without any effects on the JS Platform.
 */
@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
actual annotation class JvmMultifileClass actual constructor()
