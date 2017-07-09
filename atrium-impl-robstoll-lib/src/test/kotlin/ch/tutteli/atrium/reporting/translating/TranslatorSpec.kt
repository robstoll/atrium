package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.AssertionVerbFactory

object TranslatorSpec : ch.tutteli.atrium.spec.reporting.translating.TranslatorSpec(
    AssertionVerbFactory, ::Translator)
