package ch.tutteli.atrium.logic.creating

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.RootExpectOptions
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Helper function to create a [RootExpectOptions] via
 */
@ExperimentalNewExpectTypes
@Suppress("FunctionName")
fun <T> RootExpectOptions(configuration: RootExpectBuilder.OptionsChooser<T>.() -> Unit): RootExpectOptions<T> =
    RootExpectBuilder.OptionsChooser.createAndBuild(configuration)

/**
 * Helper function to create a [RootExpectOptions] by specifying components via named parameters.
 *
 * You can use it as follows: `RootExpectOptions(reporter = myReporter)`
 */
@ExperimentalNewExpectTypes
@Suppress("FunctionName")
fun <T> RootExpectOptions(
    expectationVerb: Translatable? = null,
    representationInsteadOfSubject: ((T) -> Any)? = null,
    reporter: Reporter? = null
): RootExpectOptions<T> = RootExpectOptions(
    expectationVerb,
    representationInsteadOfSubject,
    reporter
)
