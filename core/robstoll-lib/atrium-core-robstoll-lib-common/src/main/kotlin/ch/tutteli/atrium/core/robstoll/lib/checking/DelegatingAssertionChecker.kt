package ch.tutteli.atrium.core.robstoll.lib.checking

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.creating.AssertionHolder
import ch.tutteli.atrium.reporting.translating.Translatable

class DelegatingAssertionChecker(private val originalAssertionHolder: AssertionHolder) : AssertionChecker {

    override fun check(assertionVerb: Translatable, representationProvider: () -> Any, assertions: List<Assertion>) {
        originalAssertionHolder.addAssertion(assertionBuilder.invisibleGroup.withAssertions(assertions).build())
    }
}
