package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.ExplanatoryAssertion
import ch.tutteli.atrium.assertions.builders.withFailureHintBasedOnDefinedSubject
import ch.tutteli.atrium.core.polyfills.formatFloatingPointNumber
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.logic.FloatingPointAssertions
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionFloatingPointAssertion.*
import kotlin.math.absoluteValue

class DefaultFloatingPointAssertions : FloatingPointAssertions {
    override fun toBeWithErrorTolerance(
        container: AssertionContainer<Float>,
        expected: Float,
        tolerance: Float
    ): Assertion = toBeWithErrorToleranceOfFloatOrDouble(container, expected, tolerance) {
        (it - expected).absoluteValue
    }

    override fun toBeWithErrorTolerance(
        container: AssertionContainer<Double>,
        expected: Double,
        tolerance: Double
    ): Assertion = toBeWithErrorToleranceOfFloatOrDouble(container, expected, tolerance) {
        (it - expected).absoluteValue
    }

    private fun <T> toBeWithErrorToleranceOfFloatOrDouble(
        container: AssertionContainer<T>,
        expected: T,
        tolerance: T,
        absDiff: (T) -> T
    ): Assertion where T : Comparable<T>, T : Number {
        return toBeWithErrorTolerance(container, expected, tolerance, absDiff) { subject ->
            listOf(
                ExpectImpl.builder.explanatory
                    .withExplanation(FAILURE_DUE_TO_FLOATING_POINT_NUMBER, subject::class.fullName)
                    .build(),
                createToBeWithErrorToleranceExplained(subject, expected, absDiff, tolerance)
            )
        }
    }
}


@Suppress("DEPRECATION" /* TODO don't format number here, should be done via ObjectFormatter */)
internal fun <T> createToBeWithErrorToleranceExplained(
    subject: T,
    expected: T,
    absDiff: (T) -> T,
    tolerance: T
): ExplanatoryAssertion where T : Comparable<T>, T : Number = ExpectImpl.builder.explanatory
    .withExplanation(
        TO_BE_WITH_ERROR_TOLERANCE_EXPLAINED,
        formatFloatingPointNumber(subject),
        formatFloatingPointNumber(expected),
        formatFloatingPointNumber(absDiff(subject)),
        formatFloatingPointNumber(tolerance)
    )
    .build()

internal fun <T : Comparable<T>> toBeWithErrorTolerance(
    container: AssertionContainer<out T>,
    expected: T,
    tolerance: T,
    absDiff: (T) -> T,
    explanatoryAssertionCreator: (T) -> List<Assertion>
): Assertion = ExpectImpl.builder.descriptive
    .withTest(container) { absDiff(it) <= tolerance }
    .withFailureHintBasedOnDefinedSubject(container) { subject ->
        //TODO that's not nice in case we use it in an Iterable contains assertion, for instance contains...entry { toBeWithErrorTolerance(x, 0.01) }
        //we do not want to see the failure nor the exact check in the 'an entry which...' part
        //same problematic applies to feature assertions within an identification lambda
        // => yet explanatory assertion should always hold
        ExpectImpl.builder.explanatoryGroup
            .withDefaultType
            .withAssertions(explanatoryAssertionCreator(subject))
            .build()
    }
    .withDescriptionAndRepresentation(TranslatableWithArgs(TO_BE_WITH_ERROR_TOLERANCE, tolerance), expected)
    .build()
