package ch.tutteli.atrium.checking

import ch.tutteli.atrium.AssertionVerbFactory

object FeatureAssertionCheckerSpec : ch.tutteli.atrium.spec.checking.FeatureAssertionCheckerSpec(
    AssertionVerbFactory, ::FeatureAssertionChecker)
