package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.*
import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.WithInOrderOnlyReportingOptions
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

/**
 * Helper function to create an [All] based on the given [t] and [ts]
 * -- allows expressing `T, vararg T`.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.toContainRegexMultiple
 */
fun <T> all(t: T, vararg ts: T): All<T> = All(t, ts)

/**
 * Helper function to create a [WithInOrderOnlyReportingOptions] wrapping an [IterableLike]
 * based on the given [iterableLike] and the given [report]-configuration-lambda.
 *
 * @param iterableLike The [IterableLike] whose elements this function is referring to.
 * @param report The lambda configuring the [InOrderOnlyReportingOptions].
 *
 * @since 0.18.0
 */
fun <T: IterableLike> elementsOf(
    iterableLike: T,
    report: InOrderOnlyReportingOptions.() -> Unit
): WithInOrderOnlyReportingOptions<T> =
    WithInOrderOnlyReportingOptions(report, iterableLike)



/**
 * Helper function to create an [Entry] based on the given [assertionCreatorOrNull].
 */
fun <T : Any> entry(assertionCreatorOrNull: (Expect<T>.() -> Unit)?): Entry<T> = Entry(assertionCreatorOrNull)

/**
 * Helper function to create an [Entries] based on the given [assertionCreatorOrNull]
 * and [otherAssertionCreatorsOrNulls] -- allows expressing `{ }, vararg { }`.
 *
 * In case `null` is used for an identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 *
 * @param assertionCreatorOrNull The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda creates.
 *   In case it is defined as `null`, then an entry is identified if it is `null` as well.
 * @param otherAssertionCreatorsOrNulls A variable amount of additional identification lambdas or `null`s.
 */
fun <T : Any> entries(
    assertionCreatorOrNull: (Expect<T>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Expect<T>.() -> Unit)?
): Entries<T> = Entries(assertionCreatorOrNull, otherAssertionCreatorsOrNulls)

/**
 * Helper function to create a [WithInOrderOnlyReportingOptions] wrapping an [Entries] based on the given
 * [assertionCreatorOrNull] and [otherAssertionCreatorsOrNulls] as well as the given [report]-configuration-lambda
 * -- allows expressing `{ }, vararg { }, report = { ... }`.
 *
 * In case `null` is used for an identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 *
 * @param assertionCreatorOrNull The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda creates.
 *   In case it is defined as `null`, then an entry is identified if it is `null` as well.
 * @param otherAssertionCreatorsOrNulls A variable amount of additional identification lambdas or `null`s.
 * @param report The lambda configuring the [InOrderOnlyReportingOptions].
 *
 * @since 0.18.0
 */
fun <T : Any> entries(
    assertionCreatorOrNull: (Expect<T>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Expect<T>.() -> Unit)?,
    report: InOrderOnlyReportingOptions.() -> Unit
): WithInOrderOnlyReportingOptions<Entries<T>> =
    WithInOrderOnlyReportingOptions(report, Entries(assertionCreatorOrNull, otherAssertionCreatorsOrNulls))


/**
 * Helper function to create a [Pairs] based on the given [pair] and [otherPairs]
 * -- allows expressing `Pair<K, V>, vararg Pair<K, V>`.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapExpectationSamples.toContainOnlyPairs
 */
fun <K, V> pairs(pair: Pair<K, V>, vararg otherPairs: Pair<K, V>): Pairs<K, V> = Pairs(pair, otherPairs)

/**
 * Helper function to create a [PresentWithCreator] based on the given [assertionCreator].
 */
fun <E> present(assertionCreator: Expect<E>.() -> Unit): PresentWithCreator<E> =
    PresentWithCreator(assertionCreator)

/**
 * Helper function to create a [RegexPatterns] based on the given [pattern] and [otherPatterns]
 * -- allows expressing `String, vararg String` which are treated as regex patterns.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.toContainRegexStringMultiple
 */
fun regexPatterns(pattern: String, vararg otherPatterns: String): RegexPatterns =
    RegexPatterns(pattern, otherPatterns)

/**
 * Helper function to create a [SuccessWithCreator] based on the given [assertionCreator].
 */
@Deprecated("Use aSuccess; will be removed with 1.0.0 at the latest", ReplaceWith("aSuccess<E>(assertionCreator)"))
fun <E> success(assertionCreator: Expect<E>.() -> Unit): SuccessWithCreator<E> =
    SuccessWithCreator(assertionCreator)

/**
 * Helper function to create a [SuccessWithCreator] based on the given [assertionCreator].
 *
 * @since 0.17.0
 */
fun <E> aSuccess(assertionCreator: Expect<E>.() -> Unit): SuccessWithCreator<E> =
    SuccessWithCreator(assertionCreator)

/**
 * Helper function to create a [Value] based on the given [value].
 */
fun <T> value(value: T): Value<T> = Value(value)

/**
 * Helper function to create a [Values] based on the given [value] and [otherValues]
 * -- allows expressing `T, vararg T`.
 */
fun <T> values(value: T, vararg otherValues: T): Values<T> = Values(value, otherValues)

/**
 * Helper function to create a [WithInOrderOnlyReportingOptions] wrapping a [Values] based on the given
 * [value] and [otherValues] as well as the given [report]-configuration-lambda -- allows expressing
 * `T, vararg T, report = { ... }`.
 *
 * @param value The first expected value.
 * @param otherValues The other expected values in the given order.
 * @param report The lambda configuring the [InOrderOnlyReportingOptions].
 * @since 0.18.0
 */
fun <T> values(
    value: T,
    vararg otherValues: T,
    report: InOrderOnlyReportingOptions.() -> Unit
): WithInOrderOnlyReportingOptions<Values<T>> = WithInOrderOnlyReportingOptions(report, Values(value, otherValues))
