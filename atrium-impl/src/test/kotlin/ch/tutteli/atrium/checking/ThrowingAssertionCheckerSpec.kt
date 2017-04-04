package ch.tutteli.atrium.checking

import ch.tutteli.atrium.AssertionVerbFactory

class ThrowingAssertionCheckerSpec : ch.tutteli.atrium.test.checking.ThrowingAssertionCheckerSpec(
    AssertionVerbFactory, ::ThrowingAssertionChecker)
