package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.domain.builders.reporting.impl.TextAssertionFormatterOptionImpl
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.AssertionPairFormatter

/**
 * Provides options to register [AssertionFormatter]s to the chosen [AssertionFormatterFacade].
 */
actual interface TextAssertionFormatterOption : TextAssertionFormatterOptionCommon {

    actual companion object {
        actual fun create(
            options: AssertionFormatterChosenOptions,
            assertionPairFormatter: AssertionPairFormatter
        ): TextAssertionFormatterOption = TextAssertionFormatterOptionImpl(options, assertionPairFormatter)
    }
}

