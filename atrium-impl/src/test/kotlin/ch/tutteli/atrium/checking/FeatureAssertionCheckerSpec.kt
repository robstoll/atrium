package ch.tutteli.atrium.checking

import ch.tutteli.atrium.AssertionVerbFactory

object FeatureAssertionCheckerSpec : ch.tutteli.atrium.test.checking.FeatureAssertionCheckerSpec(
    AssertionVerbFactory, ::FeatureAssertionChecker)
