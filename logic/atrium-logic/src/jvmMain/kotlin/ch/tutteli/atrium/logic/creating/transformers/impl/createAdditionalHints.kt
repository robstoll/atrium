//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.transformers.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.translations.DescriptionThrowableExpectation

actual fun createAdditionalHints(
    throwable: Throwable
): List<Assertion> = throwable.suppressed.map { suppressed ->
    ThrowableThrownFailureHandler.createChildHint(throwable, suppressed,
        DescriptionThrowableExpectation.OCCURRED_EXCEPTION_SUPPRESSED
    )
}
