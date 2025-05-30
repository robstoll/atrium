package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.logic.creating.FeatureExpectOptions
import ch.tutteli.atrium.logic.creating.FeatureExpectOptionsChooser
import ch.tutteli.atrium.logic.creating.RootExpectBuilder
import ch.tutteli.atrium.logic.creating.RootExpectOptions
import ch.tutteli.atrium.reporting.Text

@RequiresOptIn
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
annotation class ExperimentalWithOptions

/**
 * Wraps the given [textRepresentation] into a [Text] and uses it as representation of the subject
 * instead of the representation that has been defined so far (which defaults to the subject itself).
 *
 * In case the subject of `this` expectation is not defined (i.e. `_logic.maybeSubject` is [None]),
 * then the previous representation is used.
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
 * In case the subject of `this` expectation is not defined (i.e. `_logic.maybeSubject` is [None]),
 * then the previous representation is used.
 */
@ExperimentalWithOptions
infix fun <T> RootExpect<T>.withRepresentation(representationProvider: (T) -> Any): Expect<T> =
    withOptions { withRepresentation(representationProvider) }

/**
 * Uses the given [configuration]-lambda to create an [RootExpectOptions] which in turn is used
 * to override (parts) of the existing configuration.
 */
@ExperimentalWithOptions
@OptIn(ExperimentalNewExpectTypes::class)
infix fun <T> RootExpect<T>.withOptions(configuration: RootExpectBuilder.OptionsChooser<T>.() -> Unit): Expect<T> =
    withOptions(RootExpectOptions(configuration))


/**
 * Uses the given [options] to override (parts) of the existing configuration.
 */
@ExperimentalWithOptions
@OptIn(ExperimentalNewExpectTypes::class)
infix fun <T> RootExpect<T>.withOptions(options: RootExpectOptions<T>): Expect<T> =
    RootExpect(this, options)

/**
 * Wraps the given [textRepresentation] into a [Text] and uses it as representation of the subject
 * instead of the representation that has been defined so far (which defaults to the subject itself).
 *
 * In case the subject of `this` expectation is not defined (i.e. `_logic.maybeSubject` is [None]),
 * then the previous representation is used.
 *
 * @return A new [Expect] with the specified options for subject of `this` expectation.
 */
@ExperimentalWithOptions
@OptIn(ExperimentalNewExpectTypes::class)
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
 * In case the subject of `this` expectation is not defined (i.e. `_logic.maybeSubject` is [None]),
 * then the previous representation is used.
 *
 * @return A new [Expect] with the specified options for subject of `this` expectation.
 */
@ExperimentalWithOptions
@OptIn(ExperimentalNewExpectTypes::class)
infix fun <T, R> FeatureExpect<T, R>.withRepresentation(representationProvider: (R) -> Any): Expect<R> =
    withOptions { withRepresentation(representationProvider) }

/**
 * Uses the given [configuration]-lambda to create an [FeatureExpectOptions] which in turn is used
 * to re-define (parts) of the existing configuration.
 *
 * @return A new [Expect] with the specified options for subject of `this` expectation.
 */
@ExperimentalWithOptions
@OptIn(ExperimentalNewExpectTypes::class)
infix fun <T, R> FeatureExpect<T, R>.withOptions(configuration: FeatureExpectOptionsChooser<R>.() -> Unit): Expect<R> =
    withOptions(FeatureExpectOptions(configuration))

/**
 * Uses the given [options] to override (parts) of the existing configuration.
 *
 * @return A new [Expect] with the specified options for subject of `this` expectation.
 */
@ExperimentalWithOptions
@OptIn(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
infix fun <T, R> FeatureExpect<T, R>.withOptions(options: FeatureExpectOptions<R>): Expect<R> =
    FeatureExpect(this, options)
