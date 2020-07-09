package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.translations.DescriptionComparableAssertion.*

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T1 : Comparable<T2>, T2 : Any?> _isLessThan(subjectProvider: SubjectProvider<T1>, expected: T2) =
    assertionBuilder.createDescriptive(subjectProvider, IS_LESS_THAN, expected) { it < expected }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T1 : Comparable<T2>, T2 : Any?> _isLessThanOrEqual(subjectProvider: SubjectProvider<T1>, expected: T2) =
    assertionBuilder.createDescriptive(subjectProvider, IS_LESS_THAN_OR_EQUAL, expected) { it <= expected }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T1 : Comparable<T2>, T2 : Any?> _isGreaterThan(subjectProvider: SubjectProvider<T1>, expected: T2) =
    assertionBuilder.createDescriptive(subjectProvider, IS_GREATER_THAN, expected) { it > expected }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T1 : Comparable<T2>, T2 : Any?> _isGreaterThanOrEqual(subjectProvider: SubjectProvider<T1>, expected: T2) =
    assertionBuilder.createDescriptive(subjectProvider, IS_GREATER_THAN_OR_EQUAL, expected) { it >= expected }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T1 : Comparable<T2>, T2 : Any?> _isEqualComparingTo(expect: Expect<T1>, expected: T2) =
    assertionBuilder.createDescriptive(expect, IS_EQUAL, expected) { it.compareTo(expected) == 0 }
