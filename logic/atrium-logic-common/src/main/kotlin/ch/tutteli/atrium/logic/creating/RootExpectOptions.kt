package ch.tutteli.atrium.logic.creating

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.RootExpectOptions

/**
 * Helper function to create a [RootExpectOptions] via [RootExpectBuilder.OptionsChooser].
 */
@ExperimentalNewExpectTypes
@Suppress("FunctionName")
fun <T> RootExpectOptions(configuration: RootExpectBuilder.OptionsChooser<T>.() -> Unit): RootExpectOptions<T> =
    RootExpectBuilder.OptionsChooser(configuration)
