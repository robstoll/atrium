package ch.tutteli.atrium.core.robstoll.lib.checking

import ch.tutteli.atrium.core.robstoll.lib.AssertionVerbFactory

object FeatureAssertionCheckerSpec : ch.tutteli.atrium.spec.checking.FeatureAssertionCheckerSpec(
    AssertionVerbFactory, ::FeatureAssertionChecker)
