package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerbFactory

object TranslatorSpec : ch.tutteli.atrium.spec.reporting.TranslatorSpec(
    AssertionVerbFactory, { Translator })
