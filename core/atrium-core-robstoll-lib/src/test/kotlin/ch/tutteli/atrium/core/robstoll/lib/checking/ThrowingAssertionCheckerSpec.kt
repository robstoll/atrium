package ch.tutteli.atrium.core.robstoll.lib.checking

import ch.tutteli.atrium.core.robstoll.lib.AssertionVerbFactory

object ThrowingAssertionCheckerSpec : ch.tutteli.atrium.spec.checking.ThrowingAssertionCheckerSpec(
    AssertionVerbFactory, ::ThrowingAssertionChecker)
