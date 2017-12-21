package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.assertions.Assertion

val alwaysTrueAssertionFilter : (Assertion) -> Boolean = { true }
val alwaysFalseAssertionFilter: (Assertion) -> Boolean = { false }
