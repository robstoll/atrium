@file:Suppress(/* TODO remove annotation with 1.0.0 */ "TYPEALIAS_EXPANSION_DEPRECATION", "DEPRECATION")

package ch.tutteli.atrium.domain.builders.creating.basic.contains

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.domain.creating.basic.contains.Contains
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains


/**
 * Helper method which simplifies adding assertions to the assertion container which itself is stored in
 * [Contains.CheckerOption.containsBuilder].
 *
 * @return The plant to support a fluent API.
 */
@Deprecated("Will be removed with 1.0.0")
fun <T : Any, B : Contains.Builder<T, *>> Contains.CheckerOption<T, *, *, B>.addAssertion(
    assertion: Assertion
): Expect<T> = addAssertion(containsBuilder.subjectProvider, assertion)


/**
 * Helper method which simplifies adding assertions to the assertion container which itself is stored in
 * [Contains.CheckerOption.containsBuilder].
 *
 * @return The plant to support a fluent API.
 */
@Deprecated("Will be removed with 1.0.0")
fun <E, T : Iterable<E>, S : IterableContains.SearchBehaviour> IterableContains.Builder<E, T, S>.addAssertion(
    assertion: Assertion
): Expect<T> = addAssertion(subjectProvider, assertion)

@Suppress("DEPRECATION")
private fun <T : Any> addAssertion(
    subjectProvider: SubjectProvider<T>,
    assertion: Assertion
): Expect<T> =
    //TODO simplify with 1.0.0
    when (subjectProvider) {
        is Expect<T> -> subjectProvider.addAssertion(assertion)
        is Assert<T> -> subjectProvider.asExpect().addAssertion(assertion)
        else -> throw IllegalStateException("neither Expect nor Assert")
    }


/**
 * Helper method which simplifies adding assertions to the assertion container which itself is stored in
 * [Contains.CheckerOption.containsBuilder].
 *
 * @return The plant to support a fluent API.
 */
@Deprecated("Will be removed with 1.0.0")
fun <T : Any, B : Contains.Builder<T, *>> Contains.CheckerOption<T, *, *, B>.addAssertionForAssert(
    assertion: Assertion
): Assert<T> = addAssertionForAssert(containsBuilder.subjectProvider, assertion)


/**
 * Helper method which simplifies adding assertions to the assertion container which itself is stored in
 * [Contains.CheckerOption.containsBuilder].
 *
 * @return The plant to support a fluent API.
 */
@Deprecated("Will be removed with 1.0.0")
fun <E, T : Iterable<E>, S : IterableContains.SearchBehaviour> IterableContains.Builder<E, T, S>.addAssertionForAssert(
    assertion: Assertion
): Assert<T> = addAssertionForAssert(subjectProvider, assertion)

@Suppress("DEPRECATION")
private fun <T : Any> addAssertionForAssert(
    subjectProvider: SubjectProvider<T>,
    assertion: Assertion
): Assert<T> =
    //TODO simplify with 1.0.0
    when (subjectProvider) {
        is Expect<T> -> subjectProvider.asAssert().addAssertion(assertion)
        is Assert<T> -> subjectProvider.addAssertion(assertion)
        else -> throw IllegalStateException("neither Expect nor Assert")
    }
