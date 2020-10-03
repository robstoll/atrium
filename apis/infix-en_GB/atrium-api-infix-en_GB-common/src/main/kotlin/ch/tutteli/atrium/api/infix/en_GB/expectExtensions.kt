package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.domain.builders.reporting.ExpectBuilder
import ch.tutteli.atrium.domain.builders.reporting.ExpectOptions
import ch.tutteli.atrium.reporting.Text

@Suppress("DEPRECATION" /* RequiresOptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@Experimental
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
annotation class ExperimentalWithOptions

/**
 * Wraps the given [textRepresentation] into a [Text] and uses it as representation of the subject
 * instead of the representation that has been defined so far (which defaults to the subject itself).
 *
 * In case [Expect.maybeSubject] is not defined i.e. [None], then the previous representation is used.
 */
@ExperimentalWithOptions
infix fun <T> RootExpect<T>.withRepresentation(textRepresentation: String): Expect<T> =
    withOptions { withRepresentation(textRepresentation) }

/**
 * Uses the given [representationProvider] to retrieve a representation which can be based on the current
 * subject where it is used as new representation of the subject
 * instead of the representation that has been defined so far (which defaults to the subject itself).
 *
 * Notice, if you want to use text (a [String] which is treated as raw string in reporting) as representation,
 * then wrap it into a [Text] and pass it instead.
 * If your text does not include the current subject, then we recommend to use the other overload which expects
 * a `String` and does the wrapping for you.
 *
 * In case [Expect.maybeSubject] is not defined i.e. [None], then the previous representation is used.
 */
@ExperimentalWithOptions
infix fun <T> RootExpect<T>.withRepresentation(representationProvider: (T) -> Any): Expect<T> =
    withOptions { withRepresentation(representationProvider) }

/**
 * Uses the given [configuration]-lambda to create an [ExpectOptions] which in turn is used
 * to override (parts) of the existing configuration.
 */
@ExperimentalWithOptions
infix fun <T> RootExpect<T>.withOptions(configuration: ExpectBuilder.OptionsChooser<T>.() -> Unit): Expect<T> =
    withOptions(ExpectBuilder.OptionsChooser.createAndBuild(configuration))

/**
 * Uses the given [options] to override (parts) of the existing configuration.
 */
@ExperimentalWithOptions
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalExpectConfig::class, ExperimentalNewExpectTypes::class)
infix fun <T> RootExpect<T>.withOptions(options: ExpectOptions<T>): Expect<T> =
    RootExpect(this, options.toRootExpectOptions())

/**
 * Wraps the given [textRepresentation] into a [Text] and uses it as representation of the subject
 * instead of the representation that has been defined so far (which defaults to the subject itself).
 *
 * In case [Expect.maybeSubject] is not defined i.e. [None], then the previous representation is used.
 *
 * @return An [Expect] for the current subject of the assertion.
 */
@ExperimentalWithOptions
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
infix fun <T, R> FeatureExpect<T, R>.withRepresentation(textRepresentation: String): Expect<R> =
    withOptions { withRepresentation(textRepresentation) }

/**
 * Uses the given [representationProvider] to retrieve a representation which can be based on the current
 * subject where it is used as new representation of the subject
 * instead of the representation that has been defined so far (which defaults to the subject itself).
 *
 * Notice, if you want to use text (a [String] which is treated as raw string in reporting) as representation,
 * then wrap it into a [Text] and pass it instead.
 * If your text does not include the current subject, then we recommend to use the other overload which expects
 * a `String` and does the wrapping for you.
 *
 * In case [Expect.maybeSubject] is not defined i.e. [None], then the previous representation is used.
 *
 * @return An [Expect] for the current subject of the assertion.
 */
@ExperimentalWithOptions
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
infix fun <T, R> FeatureExpect<T, R>.withRepresentation(representationProvider: (R) -> Any): Expect<R> =
    withOptions { withRepresentation(representationProvider) }

/**
 * Uses the given [configuration]-lambda to create an [FeatureExpectOptions] which in turn is used
 * to override (parts) of the existing configuration.
 *
 * @return An [Expect] for the current subject of the assertion.
 */
@ExperimentalWithOptions
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
infix fun <T, R> FeatureExpect<T, R>.withOptions(configuration: FeatureExpectOptionsChooser<R>.() -> Unit): Expect<R> =
    withOptions(FeatureExpectOptionsChooser(configuration))

/**
 * Uses the given [options] to override (parts) of the existing configuration.
 *
 * @return An [Expect] for the current subject of the assertion.
 */
@ExperimentalWithOptions
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
infix fun <T, R> FeatureExpect<T, R>.withOptions(options: FeatureExpectOptions<R>): Expect<R> =
    FeatureExpect(this, options)
