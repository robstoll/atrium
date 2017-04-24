package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.reporting.Translator
import ch.tutteli.atrium.spec.reporting.translating.TranslatorSpec

object TranslatorSpec : TranslatorSpec(
    AssertionVerbFactory, { Translator })
