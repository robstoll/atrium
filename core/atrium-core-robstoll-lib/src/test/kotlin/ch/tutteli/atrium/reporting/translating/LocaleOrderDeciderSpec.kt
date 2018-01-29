package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.AssertionVerbFactory

class LocaleOrderDeciderSpec : ch.tutteli.atrium.spec.reporting.translating.LocaleOrderDeciderSpec(
    AssertionVerbFactory, ::CoroutineBasedLocaleOrderDecider)
