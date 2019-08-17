@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] is less than [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().isLessThan(expected)",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.isLessThan"
    )
)
fun <T : Comparable<T>> Assert<T>.isLessThan(expected: T)
    = addAssertion(AssertImpl.comparable.isLessThan(this, expected))

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] is less than or equals [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().isLessOrEquals(expected)",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.isLessOrEquals"
    )
)
fun <T : Comparable<T>> Assert<T>.isLessOrEquals(expected: T)
    = addAssertion(AssertImpl.comparable.isLessOrEquals(this, expected))

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] is greater than [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().isGreaterThan(expected)",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.isGreaterThan"
    )
)
fun <T : Comparable<T>> Assert<T>.isGreaterThan(expected: T)
    = addAssertion(AssertImpl.comparable.isGreaterThan(this, expected))

/**
 * Makes the assertion that the [Assert.subject][AssertionPlant.subject] is greater than or equals [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated(
    "Switch from Assert to Expect; will be removed with 1.0.0",
    ReplaceWith(
        "this.asExpect().isGreaterOrEquals(expected)",
        "ch.tutteli.atrium.domain.builders.migration.asExpect",
        "ch.tutteli.atrium.api.fluent.en_GB.isGreaterOrEquals"
    )
)
fun <T : Comparable<T>> Assert<T>.isGreaterOrEquals(expected: T)
    = addAssertion(AssertImpl.comparable.isGreaterOrEquals(this, expected))

