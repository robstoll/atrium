//TODO 1.4.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.specs.reporting

import ch.tutteli.atrium.assertions.Assertion

val alwaysTrueAssertionFilter: (Assertion) -> Boolean = { true }
val alwaysFalseAssertionFilter: (Assertion) -> Boolean = { false }
