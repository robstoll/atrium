package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect

/**
 * Represent a data structure which holds some [value] of type [V] together with an [assertionCreator]-lambda.
 *
 * Its purpose is to augment a feature which expects 1 argument with an assertionCreator-lambda,
 * eventually allowing to have a second overload which expects [WithAssertionCreator]. Thus make it possible that
 * the feature can have both variants, fail-fast without assertionCreator-lambda and non-fail fast,
 * i.e. assertion group block syntax, which expects an assertionCreator-lambda. The following example shows both syntax
 * in action:
 * ```
 * expect(listOf(1,2)) get 1 isGreaterThan 2 isLessThan 4     // without assertionCreator-lambda
 * expect(listOf(1,2)) get 1 {                                // without assertionCreator-lambda
 *   o isGreaterThan 2
 *   o isLessThan 4
 * }
 * ```
 *
 * @property value The value which shall be joined with an assertionCreator-lambda.
 * @property assertionCreator The assertionCreator-lambda as such which defines assertion for a subject of type [T].
 *
 * @param V The value type
 * @param T The type of the subject of assertion for which the [assertionCreator] is defined.
 */
data class WithAssertionCreator<V, T>(val value: V, val assertionCreator: Expect<T>.() -> Unit)

infix operator fun <V, T> V.invoke(assertionCreator: Expect<T>.() -> Unit): WithAssertionCreator<V, T> =
    WithAssertionCreator(this, assertionCreator)

