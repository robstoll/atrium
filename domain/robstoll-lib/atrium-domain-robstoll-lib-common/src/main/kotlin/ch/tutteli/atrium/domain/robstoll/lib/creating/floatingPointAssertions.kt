@file:JvmMultifileClass
@file:JvmName("floatingPointAssertionsKt")

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.ExplanatoryAssertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.withFailureHintBasedOnDefinedSubject
import ch.tutteli.atrium.core.polyfills.formatFloatingPointNumber
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionFloatingPointAssertion.*
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import kotlin.math.absoluteValue

fun _toBeWithErrorTolerance(subjectProvider: SubjectProvider<Float>, expected: Float, tolerance: Float): Assertion =
    toBeWithErrorToleranceOfFloatOrDouble(subjectProvider, expected, tolerance) { (it - expected).absoluteValue }

fun _toBeWithErrorTolerance(subjectProvider: SubjectProvider<Double>, expected: Double, tolerance: Double): Assertion =
    toBeWithErrorToleranceOfFloatOrDouble(subjectProvider, expected, tolerance) { (it - expected).absoluteValue }

private fun <T> toBeWithErrorToleranceOfFloatOrDouble(
    subjectProvider: SubjectProvider<T>,
    expected: T,
    tolerance: T,
    absDiff: (T) -> T
): Assertion where T : Comparable<T>, T : Number {
    return toBeWithErrorTolerance(subjectProvider, expected, tolerance, absDiff) { subject ->
        listOf(
            assertionBuilder.explanatory
                .withExplanation(FAILURE_DUE_TO_FLOATING_POINT_NUMBER, subject::class.fullName)
                .build(),
            createToBeWithErrorToleranceExplained(subject, expected, absDiff, tolerance)
        )
    }
}

@Suppress("DEPRECATION" /* TODO don't format number here, should be done via ObjectFormatter */)
internal fun <T> createToBeWithErrorToleranceExplained(
    subject: T,
    expected: T,
    absDiff: (T) -> T,
    tolerance: T
): ExplanatoryAssertion where T : Comparable<T>, T : Number = assertionBuilder.explanatory
    .withExplanation(
        TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED,
        formatFloatingPointNumber(subject),
        formatFloatingPointNumber(expected),
        formatFloatingPointNumber(absDiff(subject)),
        formatFloatingPointNumber(tolerance)
    )
    .build()

internal fun <T : Comparable<T>> toBeWithErrorTolerance(
    subjectProvider: SubjectProvider<T>,
    expected: T,
    tolerance: T,
    absDiff: (T) -> T,
    explanatoryAssertionCreator: (T) -> List<Assertion>
): Assertion = assertionBuilder.descriptive
    .withTest(subjectProvider) { absDiff(it) <= tolerance }
    .withFailureHintBasedOnDefinedSubject(subjectProvider) { subject ->
        //TODO that's not nice in case we use it in an Iterable contains assertion, for instance contains...entry { toBeWithErrorTolerance(x, 0.01) }
        //we do not want to see the failure nor the exact check in the 'an entry which...' part
        //same problematic applies to feature assertions within an identification lambda
        // => yet explanatory assertion should always hold
        assertionBuilder.explanatoryGroup
            .withDefaultType
            .withAssertions(explanatoryAssertionCreator(subject))
            .build()
    }
    .withDescriptionAndRepresentation(TranslatableWithArgs(TO_BE_WITH_ERROR_TOLERANCE, tolerance), expected)
    .build()
