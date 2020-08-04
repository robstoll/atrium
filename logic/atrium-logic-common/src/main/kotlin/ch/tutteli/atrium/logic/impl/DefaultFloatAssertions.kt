package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.ExplanatoryAssertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.withFailureHintBasedOnDefinedSubject
import ch.tutteli.atrium.core.polyfills.formatFloatingPointNumber
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.FloatAssertions
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionFloatingPointAssertion.*
import kotlin.math.absoluteValue

class DefaultFloatAssertions : BaseFloatingAssertions(), FloatAssertions {
    override fun toBeWithErrorTolerance(
        container: AssertionContainer<Float>,
        expected: Float,
        tolerance: Float
    ): Assertion = toBeWithErrorToleranceOfFloatOrDouble(container, expected, tolerance) {
        (it - expected).absoluteValue
    }
}
