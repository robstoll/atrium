package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.reporting.translating.TranslationSupplier
import ch.tutteli.atrium.reporting.translating.Translator

/**
 * Provides options to create a [Translator] or [TranslationSupplier].
 */
actual interface ReporterBuilder : ReporterBuilderCommon
