package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.assertions.IAssertion

val alwaysTrueAssertionFilter : (IAssertion) -> Boolean = { true }
val alwaysFalseAssertionFilter: (IAssertion) -> Boolean = { false }
