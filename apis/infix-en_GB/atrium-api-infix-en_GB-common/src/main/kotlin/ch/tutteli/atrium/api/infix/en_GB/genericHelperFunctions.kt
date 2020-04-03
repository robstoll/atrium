package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.creating.*
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect

/**
 * Helper function to create an [All] based on the given [t] and [ts]
 * -- allows to express `T, vararg T`.
 */
fun <T> all(t: T, vararg ts: T) = All(t, ts)

/**
 * Helper function to create an [Entry] based on the given [assertionCreatorOrNull].
 */
fun <T : Any> entry(assertionCreatorOrNull: (Expect<T>.() -> Unit)?): Entry<T> = Entry(assertionCreatorOrNull)

/**
 * Helper function to create an [Entries] based on the given [assertionCreatorOrNull]
 * and [otherAssertionCreatorsOrNulls] -- allows to express `{ }, vararg { }`.
 *
 * In case `null` is used for an identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 *
 * @param assertionCreatorOrNull The identification lambda identifying the entry where an entry is considered
 *   to be identified if it holds all [Assertion]s the lambda might create.
 *   In case it is defined as `null`, then an entry is identified if it is `null` as well.
 * @param otherAssertionCreatorsOrNulls A variable amount of additional identification lambdas or `null`s.
 */
fun <T : Any> entries(
    assertionCreatorOrNull: (Expect<T>.() -> Unit)?,
    vararg otherAssertionCreatorsOrNulls: (Expect<T>.() -> Unit)?
): Entries<T> = Entries(assertionCreatorOrNull, otherAssertionCreatorsOrNulls)


/**
 * Helper function to create a [Pairs] based on the given [pair] and [otherPairs]
 * -- allows to express `Pair<K, V>, vararg Pair<K, V>`.
 */
fun <K, V> pairs(pair: Pair<K, V>, vararg otherPairs: Pair<K, V>): Pairs<K, V> = Pairs(pair, otherPairs)

/**
 * Helper function to create a [Value] based on the given [value].
 */
fun <T> value(value: T): Value<T> = Value(value)

/**
 * Helper function to create a [Values] based on the given [value] and [otherValues]
 * -- allows to express `T, vararg T`.
 */
fun <T> values(value: T, vararg otherValues: T): Values<T> = Values(value, otherValues)
