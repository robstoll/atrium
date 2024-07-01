//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeExpectation.*
import java.time.LocalDate
import java.time.chrono.ChronoLocalDate

class DefaultChronoLocalDateAssertions : ChronoLocalDateAssertions {

    override fun <T : ChronoLocalDate> isBefore(
        container: AssertionContainer<T>,
        expected: ChronoLocalDate
    ): Assertion = container.createDescriptiveAssertion(TO_BE_BEFORE, expected) {
        it.isBefore(expected)
    }

    override fun <T : ChronoLocalDate> isBeforeOrEqual(
        container: AssertionContainer<T>,
        expected: ChronoLocalDate
    ): Assertion = container.createDescriptiveAssertion(TO_BE_BEFORE_OR_THE_SAME_POINT_IN_TIME_AS, expected) {
        it.isBefore(expected) || it.isEqual(expected)
    }

    override fun <T : ChronoLocalDate> isAfter(
        container: AssertionContainer<T>,
        expected: ChronoLocalDate
    ): Assertion = container.createDescriptiveAssertion(TO_BE_AFTER, expected) {
        it.isAfter(expected)
    }

    override fun <T : ChronoLocalDate> isAfterOrEqual(
        container: AssertionContainer<T>,
        expected: ChronoLocalDate
    ): Assertion = container.createDescriptiveAssertion(TO_BE_AFTER_OR_THE_SAME_POINT_IN_TIME_AS, expected) {
        it.isAfter(expected) || it.isEqual(expected)
    }

    override fun <T : ChronoLocalDate> isEqual(
        container: AssertionContainer<T>,
        expected: ChronoLocalDate
    ): Assertion = container.createDescriptiveAssertion(TO_BE_THE_SAME_DAY_AS, expected) { it.isEqual(expected) }

    override fun <T : ChronoLocalDate> isBefore(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.isBefore(LocalDate.parse(expected))

    override fun <T : ChronoLocalDate> isBeforeOrEqual(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.isBeforeOrEqual(LocalDate.parse(expected))

    override fun <T : ChronoLocalDate> isAfter(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.isAfter(LocalDate.parse(expected))

    override fun <T : ChronoLocalDate> isAfterOrEqual(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.isAfterOrEqual(LocalDate.parse(expected))

    override fun <T : ChronoLocalDate> isEqual(
        container: AssertionContainer<T>,
        expected: String
    ): Assertion = container.isEqual(LocalDate.parse(expected))
}
