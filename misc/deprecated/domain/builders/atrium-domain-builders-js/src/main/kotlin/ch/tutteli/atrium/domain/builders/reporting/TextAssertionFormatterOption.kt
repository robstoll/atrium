//TODO remove file with 0.17.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.domain.builders.reporting.impl.TextAssertionFormatterOptionImpl
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.AssertionPairFormatter

/**
 * Provides options to register [AssertionFormatter]s to the chosen [AssertionFormatterFacade].
 */
@Deprecated("Configure components via withOptions when creating an expectation verb instead; will be removed with 0.17.0")
actual interface TextAssertionFormatterOption : TextAssertionFormatterOptionCommon {

    actual companion object {
        actual fun create(
            options: AssertionFormatterChosenOptions,
            assertionPairFormatter: AssertionPairFormatter
        ): TextAssertionFormatterOption = TextAssertionFormatterOptionImpl(options, assertionPairFormatter)
    }
}

