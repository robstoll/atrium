@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

object ComparableAssertionsSpec : ch.tutteli.atrium.spec.integration.ComparableAssertionsSpec(
    AssertionVerbFactory,
    Assert<Int>::isLessThan.name to Assert<Int>::isLessThan,
    Assert<Int>::isLessOrEquals.name to Assert<Int>::isLessOrEquals,
    Assert<Int>::isGreaterThan.name to Assert<Int>::isGreaterThan,
    Assert<Int>::isGreaterOrEquals.name to Assert<Int>::isGreaterOrEquals
)
