package ch.tutteli.atrium.core.robstoll.lib.checking

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

//TODO #116 migrate spek1 to spek2 - move to common module
object FeatureAssertionCheckerSpec : ch.tutteli.atrium.spec.checking.FeatureAssertionCheckerSpec(
    AssertionVerbFactory, ::FeatureAssertionChecker
)
