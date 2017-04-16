package ch.tutteli.atrium.checking

import ch.tutteli.atrium.AssertionVerbFactory

object ThrowingAssertionCheckerSpec : ch.tutteli.atrium.spec.checking.ThrowingAssertionCheckerSpec(
    AssertionVerbFactory, ::ThrowingAssertionChecker)
