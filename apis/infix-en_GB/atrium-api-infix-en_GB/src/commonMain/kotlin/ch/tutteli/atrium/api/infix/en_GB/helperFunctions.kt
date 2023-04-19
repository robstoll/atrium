package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.*
import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.WithInAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.api.infix.en_GB.creating.iterable.WithInOrderOnlyReportingOptions
import ch.tutteli.atrium.api.infix.en_GB.creating.map.KeyValues
import ch.tutteli.atrium.api.infix.en_GB.creating.map.KeyWithValueCreator
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.creating.typeutils.MapLike

/**
 * Helper function to create an [All] based on the given [t] and [ts]
 * -- allows expressing `T, vararg T`.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.CharSequenceExpectationSamples.toContainRegexMultiple
 */
fun <T> all(t: T, vararg ts: T): All<T> = All(t, ts)

/**
 * Helper function to create a [WithInOrderOnlyReportingOptions] wrapping an [IterableLike]
 * based on the given [iterableLike] and the given [reportOptionsInOrderOnly]-configuration-lambda.
 *
 * @param iterableLike The [IterableLike] whose elements this function is referring to.
 * @param reportOptionsInOrderOnly The lambda configuring the [InOrderOnlyReportingOptions].
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toContainExactlyElementsOf
 *
 * @since 0.18.0
 */
fun <T : IterableLike> elementsOf(
    iterableLike: T,
    reportOptionsInOrderOnly: InOrderOnlyReportingOptions.() -> Unit
): WithInOrderOnlyReportingOptions<T> = WithInOrderOnlyReportingOptions(reportOptionsInOrderOnly, iterableLike)

/**
 * Helper function to create a [WithInAnyOrderOnlyReportingOptions] wrapping an [IterableLike]
 * based on the given [iterableLike] and the given [reportOptionsInAnyOrderOnly]-configuration-lambda.
 *
 * @param iterableLike The [IterableLike] whose elements this function is referring to.
 * @param reportOptionsInAnyOrderOnly The lambda configuring the [InAnyOrderOnlyReportingOptions].
 *
 * @since 0.18.0
 */
fun <T : IterableLike> elementsOf(
    iterableLike: T,
    reportOptionsInAnyOrderOnly: InAnyOrderOnlyReportingOptions.() -> Unit
): WithInAnyOrderOnlyReportingOptions<T> = WithInAnyOrderOnlyReportingOptions(reportOptionsInAnyOrderOnly, iterableLike)

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
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toContainExactlyAssertions
 */
fun <T : Any> entries(
    assertionCreatorOrNull: (Expect<T>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Expect<T>.() -> Unit)?
): Entries<T> = Entries(assertionCreatorOrNull, otherAssertionCreatorsOrNulls)

/**
 * Helper function to create a [WithInOrderOnlyReportingOptions] wrapping an [Entries] based on the given
 * [assertionCreatorOrNull] and [otherAssertionCreatorsOrNulls] as well as the given
 * [reportOptionsInOrderOnly]-configuration-lambda -- allows expressing `{ }, vararg { }, reportOptionsInOrderOnly = { ... }`.
 *
 * In case `null` is used for an identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 *
 * @param assertionCreatorOrNull The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda creates.
 *   In case it is defined as `null`, then an entry is identified if it is `null` as well.
 * @param otherAssertionCreatorsOrNulls A variable amount of additional identification lambdas or `null`s.
 * @param reportOptionsInOrderOnly The lambda configuring the [InOrderOnlyReportingOptions].
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.IterableExpectationSamples.toContainExactlyAssertions
 *
 * @since 0.18.0
 */
fun <T : Any> entries(
    assertionCreatorOrNull: (Expect<T>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Expect<T>.() -> Unit)?,
    reportOptionsInOrderOnly: InOrderOnlyReportingOptions.() -> Unit
): WithInOrderOnlyReportingOptions<Entries<T>> =
    WithInOrderOnlyReportingOptions(reportOptionsInOrderOnly, Entries(assertionCreatorOrNull, otherAssertionCreatorsOrNulls))

/**
 * Helper function to create a [WithInAnyOrderOnlyReportingOptions] wrapping an [Entries] based on the given
 * [assertionCreatorOrNull] and [otherAssertionCreatorsOrNulls] as well as the given
 * [reportOptionsInAnyOrderOnly]-configuration-lambda -- allows expressing `{ }, vararg { }, reportOptionsInAnyOrderOnly = { ... }`.
 *
 * In case `null` is used for an identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 *
 * @param assertionCreatorOrNull The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda creates.
 *   In case it is defined as `null`, then an entry is identified if it is `null` as well.
 * @param otherAssertionCreatorsOrNulls A variable amount of additional identification lambdas or `null`s.
 * @param reportOptionsInAnyOrderOnly The lambda configuring the [InOrderOnlyReportingOptions].
 *
 * @since 0.18.0
 */
fun <T : Any> entries(
    assertionCreatorOrNull: (Expect<T>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Expect<T>.() -> Unit)?,
    reportOptionsInAnyOrderOnly: InAnyOrderOnlyReportingOptions.() -> Unit
): WithInAnyOrderOnlyReportingOptions<Entries<T>> =
    WithInAnyOrderOnlyReportingOptions(reportOptionsInAnyOrderOnly, Entries(assertionCreatorOrNull, otherAssertionCreatorsOrNulls))

/**
 * Helper function to create a [WithInOrderOnlyReportingOptions] wrapping an [MapLike]
 * based on the given [mapLike] and the given [reportOptionsInOrderOnly]-configuration-lambda.
 *
 * @param mapLike The [MapLike] whose elements this function is referring to.
 * @param reportOptionsInOrderOnly The lambda configuring the [InOrderOnlyReportingOptions].
 *
 * @since 0.18.0
 */
fun <T : MapLike> entriesOf(
    mapLike: T,
    reportOptionsInOrderOnly: InOrderOnlyReportingOptions.() -> Unit
): WithInOrderOnlyReportingOptions<T> = WithInOrderOnlyReportingOptions(reportOptionsInOrderOnly, mapLike)

/**
 * Helper function to create a [KeyValues] based on the given [keyValue] and [otherKeyValues]
 * -- allows expressing `Pair<K, (Expect<V>.() -> Unit)?>, vararg Pair<K, (Expect<V>.() -> Unit)?>`.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapExpectationSamples.toContainKeyValues
 */
// TODO 1.1.0: consider to rename to `entries` once updated to Kotlin 1.4 maybe the type inference is better and there
// is no longer a clash with Iterable entries
fun <K, V : Any> keyValues(
    keyValue: KeyWithValueCreator<K, V>,
    vararg otherKeyValues: KeyWithValueCreator<K, V>
): KeyValues<K, V> = KeyValues(keyValue, otherKeyValues)

/**
 * Helper function to create a [KeyValues] based on the given [keyValue] and [otherKeyValues]
 * -- allows expressing `Pair<K, (Expect<V>.() -> Unit)?>, vararg Pair<K, (Expect<V>.() -> Unit)?>`.
 *
 * @param reportOptionsInOrderOnly The lambda configuring the [InOrderOnlyReportingOptions] -- it is optional where
 *   the default [InOrderOnlyReportingOptions] apply if not specified.
 *   since 0.18.0
 */
// TODO 1.1.0: consider to rename to `entries` once updated to Kotlin 1.4 maybe the type inference is better and there
// is no longer a clash with Iterable entries
fun <K, V : Any> keyValues(
    keyValue: KeyWithValueCreator<K, V>,
    vararg otherKeyValues: KeyWithValueCreator<K, V>,
    reportOptionsInOrderOnly: InOrderOnlyReportingOptions.() -> Unit
): WithInOrderOnlyReportingOptions<KeyValues<K, V>> =
    WithInOrderOnlyReportingOptions(reportOptionsInOrderOnly, KeyValues(keyValue, otherKeyValues))

/**
 * Helper function to create a [KeyWithValueCreator] based on the given [key] and [valueAssertionCreatorOrNull]
 * -- allows expressing `Pair<K, (Expect<V>.() -> Unit)?>`.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapExpectationSamples.toContainKeyValue
 *
 */
fun <K, V : Any> keyValue(key: K, valueAssertionCreatorOrNull: (Expect<V>.() -> Unit)?): KeyWithValueCreator<K, V> =
    KeyWithValueCreator(key, valueAssertionCreatorOrNull)

/**
 * Helper function to create a [Pairs] based on the given [pair] and [otherPairs]
 * -- allows expressing `Pair<K, V>, vararg Pair<K, V>`.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapExpectationSamples.toContainOnlyPairs
 */
fun <K, V> pairs(pair: Pair<K, V>, vararg otherPairs: Pair<K, V>): Pairs<K, V> = Pairs(pair, otherPairs)

/**
 * Helper function to create a [Pairs] based on the given [pair] and [otherPairs]
 * -- allows expressing `Pair<K, V>, vararg Pair<K, V>`.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.MapExpectationSamples.toContainOnlyPairs
 *
 * @since 0.18.0
 */
fun <K, V> pairs(
    pair: Pair<K, V>,
    vararg otherPairs: Pair<K, V>,
    reportOptionsInOrderOnly: InOrderOnlyReportingOptions.() -> Unit
): WithInOrderOnlyReportingOptions<Pairs<K, V>> = WithInOrderOnlyReportingOptions(reportOptionsInOrderOnly, Pairs(pair, otherPairs))

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
 * [value] and [otherValues] as well as the given [reportOptionsInOrderOnly]-configuration-lambda -- allows expressing
 * `T, vararg T, reportOptionsInOrderOnly = { ... }`.
 *
 * @param value The first expected value.
 * @param otherValues The other expected values in the given order.
 * @param reportOptionsInOrderOnly The lambda configuring the [InOrderOnlyReportingOptions].
 *
 * @since 0.18.0
 */
fun <T> values(
    value: T,
    vararg otherValues: T,
    reportOptionsInOrderOnly: InOrderOnlyReportingOptions.() -> Unit
): WithInOrderOnlyReportingOptions<Values<T>> = WithInOrderOnlyReportingOptions(reportOptionsInOrderOnly, Values(value, otherValues))

/**
 * Helper function to create a [WithInOrderOnlyReportingOptions] wrapping a [Values] based on the given
 * [value] and [otherValues] as well as the given [reportOptionsInAnyOrderOnly]-configuration-lambda -- allows expressing
 * `T, vararg T, reportOptionsInAnyOrderOnly = { ... }`.
 *
 * @param value The first expected value.
 * @param otherValues The other expected values in the given order.
 * @param reportOptionsInAnyOrderOnly The lambda configuring the [InOrderOnlyReportingOptions].
 *
 * @since 0.18.0
 */
fun <T> values(
    value: T,
    vararg otherValues: T,
    reportOptionsInAnyOrderOnly: InAnyOrderOnlyReportingOptions.() -> Unit
): WithInAnyOrderOnlyReportingOptions<Values<T>> = WithInAnyOrderOnlyReportingOptions(reportOptionsInAnyOrderOnly, Values(value, otherValues))
