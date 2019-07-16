package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect

object ComparableAssertionsSpec : ch.tutteli.atrium.specs.integration.ComparableAssertionsSpec(
    AssertionVerbFactory,
    Expect<Int>::isLessThan.name to Expect<Int>::isLessThan,
    Expect<Int>::isLessOrEquals.name to Expect<Int>::isLessOrEquals,
    Expect<Int>::isGreaterThan.name to Expect<Int>::isGreaterThan,
    Expect<Int>::isGreaterOrEquals.name to Expect<Int>::isGreaterOrEquals
)
