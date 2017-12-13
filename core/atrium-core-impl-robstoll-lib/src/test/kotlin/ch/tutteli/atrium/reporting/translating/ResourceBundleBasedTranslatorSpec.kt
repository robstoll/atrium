package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.AssertionVerbFactory

object ResourceBundleBasedTranslatorSpec : ch.tutteli.atrium.spec.reporting.translating.TranslatorErrorCaseSpec(
    AssertionVerbFactory, ::ResourceBundleBasedTranslator)
