package ch.tutteli.atrium.reporting

import java.util.*

/**
 * The access point to an implementation of [Reporter].
 *
 * It loads implementations of [ReporterFactory] lazily via [ServiceLoader] and searches for the id specified via the
 * system property `ch.tutteli.atrium.reporting.reporterFactory` or uses `default` in case the system property is not specified.
 */
val reporter by lazy {
    val id = System.getProperty("ch.tutteli.atrium.reporting.reporterFactory") ?: "default"
    val factory = ServiceLoader.load(ReporterFactory::class.java).iterator().asSequence().first { it.id == id }
    factory.create()
}


/**
 * Responsible to create a [Reporter].
 *
 * It identify itself via its [id]. This id can be used by a user to specify that this [ReporterFactory] shall be used.
 * In order to do that, the user has to define the system property `ch.tutteli.atrium.reporting.reporterFactory`
 */
interface ReporterFactory {
    /**
     * Identification of the supplier
     */
    val id: String

    /**
     * Creates a new [Reporter].
     */
    fun create(): Reporter
}
