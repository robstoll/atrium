package ch.tutteli.atrium.core.robstoll.lib.checking

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory

object FeatureAssertionCheckerSpec : ch.tutteli.atrium.specs.checking.FeatureAssertionCheckerSpec(
    AssertionVerbFactory, ::FeatureAssertionChecker
)
