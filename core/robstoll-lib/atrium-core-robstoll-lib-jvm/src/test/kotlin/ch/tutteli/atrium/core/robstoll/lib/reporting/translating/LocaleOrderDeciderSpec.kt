package ch.tutteli.atrium.core.robstoll.lib.reporting.translating

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory

//TODO #116 migrate spek1 to spek2 - move to common module
class LocaleOrderDeciderSpec : ch.tutteli.atrium.specs.reporting.translating.LocaleOrderDeciderSpec(
    AssertionVerbFactory, ::CoroutineBasedLocaleOrderDecider
)
