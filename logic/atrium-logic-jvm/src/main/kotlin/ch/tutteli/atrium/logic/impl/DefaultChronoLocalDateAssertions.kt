@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.ChronoLocalDateAssertions
import ch.tutteli.atrium.logic.createDescriptiveAssertion
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion.*
import java.time.chrono.ChronoLocalDate

class DefaultChronoLocalDateAssertions : ChronoLocalDateAssertions {

    override fun <T : ChronoLocalDate> isBefore(
        container: AssertionContainer<T>,
        expected: ChronoLocalDate
    ): Assertion = container.createDescriptiveAssertion(IS_BEFORE, expected) {
        it.isBefore(expected)
    }

    override fun <T : ChronoLocalDate> isBeforeOrEqual(
        container: AssertionContainer<T>,
        expected: ChronoLocalDate
    ): Assertion = container.createDescriptiveAssertion(IS_BEFORE_OR_EQUAL, expected) {
        it.isBefore(expected) || it.isEqual(expected)
    }

    override fun <T : ChronoLocalDate> isAfter(
        container: AssertionContainer<T>,
        expected: ChronoLocalDate
    ): Assertion = container.createDescriptiveAssertion(IS_AFTER, expected) {
        it.isAfter(expected)
    }

    override fun <T : ChronoLocalDate> isAfterOrEqual(
        container: AssertionContainer<T>,
        expected: ChronoLocalDate
    ): Assertion = container.createDescriptiveAssertion(IS_AFTER_OR_EQUAL, expected) {
        it.isAfter(expected) || it.isEqual(expected)
    }

    override fun <T : ChronoLocalDate> isEqual(
        container: AssertionContainer<T>,
        expected: ChronoLocalDate
    ): Assertion = container.createDescriptiveAssertion(SAME_DAY, expected) { it.isEqual(expected) }

}
