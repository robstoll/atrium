package ch.tutteli.atrium.logic.creating.changers.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion.OCCURRED_EXCEPTION_SUPPRESSED

actual fun createAdditionalHints(
    throwable: Throwable
): List<Assertion> = throwable.suppressed.map { suppressed ->
    ThrowableThrownFailureHandler.createChildHint(throwable, suppressed, OCCURRED_EXCEPTION_SUPPRESSED)
}
