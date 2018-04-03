package ch.tutteli.atrium.domain.robstoll.creating.collectors

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.collectors.NonThrowingAssertionCollectorForExplanation
import ch.tutteli.atrium.domain.creating.collectors.ThrowingAssertionCollectorForExplanation
import ch.tutteli.atrium.domain.robstoll.lib.creating.collectors.AssertionCollectorForExplanation
import ch.tutteli.atrium.reporting.translating.Translatable

class NonThrowingAssertionCollectorForExplanationImpl : NonThrowingAssertionCollectorForExplanation {
    override fun <E : Any> collect(
        warning: Translatable,
        assertionCreator: (AssertionPlant<E>.() -> Unit)?,
        subject: E?
    ): List<Assertion>
        = AssertionCollectorForExplanation(false)
        .collect(warning, assertionCreator, subject)
}

class ThrowingAssertionCollectorForExplanationImpl : ThrowingAssertionCollectorForExplanation {
    override fun <E : Any> collect(
        warning: Translatable,
        assertionCreator: (AssertionPlant<E>.() -> Unit)?,
        subject: E?
    ): List<Assertion>
        = AssertionCollectorForExplanation(true)
        .collect(warning, assertionCreator, subject)
}
