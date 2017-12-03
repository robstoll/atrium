package ch.tutteli.atrium.checking

import ch.tutteli.atrium.AssertionVerbFactory

object DelegatingAssertionCheckerSpec : ch.tutteli.atrium.spec.checking.DelegatingAssertionCheckerSpec(
    AssertionVerbFactory, ::DelegatingAssertionChecker)
