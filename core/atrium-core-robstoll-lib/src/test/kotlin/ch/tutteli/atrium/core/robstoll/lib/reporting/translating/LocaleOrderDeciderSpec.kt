package ch.tutteli.atrium.core.robstoll.lib.reporting.translating

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

class LocaleOrderDeciderSpec : ch.tutteli.atrium.spec.reporting.translating.LocaleOrderDeciderSpec(
    AssertionVerbFactory, ::CoroutineBasedLocaleOrderDecider)
