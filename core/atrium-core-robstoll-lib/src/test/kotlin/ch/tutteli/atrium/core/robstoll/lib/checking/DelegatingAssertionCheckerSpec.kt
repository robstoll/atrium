package ch.tutteli.atrium.core.robstoll.lib.checking

import ch.tutteli.atrium.core.robstoll.lib.AssertionVerbFactory

object DelegatingAssertionCheckerSpec : ch.tutteli.atrium.spec.checking.DelegatingAssertionCheckerSpec(
    AssertionVerbFactory, ::DelegatingAssertionChecker)
