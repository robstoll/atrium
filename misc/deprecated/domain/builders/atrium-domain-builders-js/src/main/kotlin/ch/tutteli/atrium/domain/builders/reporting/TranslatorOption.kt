//TODO remove file with 0.17.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.reporting.translating.Translator

/**
 * Provides options to create a [Translator].
 */
@Deprecated("Configure components via withOptions when creating an expectation verb instead; will be removed with 0.17.0")
actual interface TranslatorOption : TranslatorOptionCommon
