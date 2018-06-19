package ch.tutteli.atrium.reporting


/**
 * The access point to an implementation of [Reporter].
 *
 * It loads implementations of [ReporterFactory] lazily via [ServiceLoader] and searches for the id specified via the
 * system property with key [SYSTEM_PROPERTY] (which is `ch.tutteli.atrium.reporting.reporterFactory`)
 * or uses `default` in case the system property is not specified.
 *
 * Use [ReporterFactory.specifyFactory] or [ReporterFactory.specifyFactoryIfNotYetSet] to define the system property.
 *
 * Notice, that searching for a [ReporterFactory] is only done once and the result is cached afterwards.
 * Please [open an issue](https://github.com/robstoll/atrium/issues/new) if you want to change reporter during a test-run.
 */
expect val reporter: Reporter

/**
 * The key of the system property which is used to define which [ReporterFactory] shall be used.
 * You can use [ReporterFactory.specifyFactory] or [ReporterFactory.specifyFactoryIfNotYetSet]
 */
const val SYSTEM_PROPERTY = "ch.tutteli.atrium.reporting.reporterFactory"

/**
 * Responsible to create a [Reporter].
 *
 * It identify itself via its [id]. This id can be used by a user to specify that this [ReporterFactory] shall be used.
 * In order to do that, the user has to define the system property `ch.tutteli.atrium.reporting.reporterFactory`
 */
expect interface ReporterFactory {
    /**
     * Identification of the supplier
     */
    val id: String

    /**
     * Creates a new [Reporter].
     */
    fun create(): Reporter

    /**
     * Provides utility functions to specify a [ReporterFactory].
     */
    companion object {

        /**
         * Sets the system property with key [SYSTEM_PROPERTY] (which is `ch.tutteli.atrium.reporting.reporterFactory`)
         * to the given [reporterFactoryId] regardless if another id was specified before.
         *
         * Use [specifyFactoryIfNotYetSet] if you only want to set a default value but not overwrite an existing
         * specification.
         */
        fun specifyFactory(reporterFactoryId: String)

        /**
         * Sets the system property with key [SYSTEM_PROPERTY] (which is `ch.tutteli.atrium.reporting.reporterFactory`)
         * to the given [reporterFactoryId] if another id was not *yet* specified before.
         *
         * Use [specifyFactory] if you do not care if another id was specified before or in other words, if you want to
         * overwrite a previously defined id.
         */
        fun specifyFactoryIfNotYetSet(reporterFactoryId: String)
    }
}


