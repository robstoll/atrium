@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.verbs.internal

import ch.tutteli.atrium.core.polyfills.registerService

@Suppress("unused" /* here in order that the code is executed when module is loaded */)
private val register = run {

    registerService<ch.tutteli.atrium.reporting.ReporterFactory> { NoAdjustingReporterFactory() }
}
