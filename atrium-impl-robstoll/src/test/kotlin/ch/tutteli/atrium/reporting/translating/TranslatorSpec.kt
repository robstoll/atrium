package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.assert
import ch.tutteli.atrium.toBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import java.util.*

object TranslatorSpec : ch.tutteli.atrium.spec.reporting.translating.TranslatorSpec(
    AssertionVerbFactory, ::Translator)
