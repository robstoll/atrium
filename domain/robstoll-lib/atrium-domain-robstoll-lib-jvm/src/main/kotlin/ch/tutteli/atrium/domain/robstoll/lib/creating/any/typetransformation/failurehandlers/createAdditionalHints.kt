package ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.failurehandlers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion

actual fun createAdditionalHints(throwable: Throwable, maxStackTrace: Int): List<Assertion>
    = throwable.suppressed.map { suppressed ->
        ThrowableThrownFailureHandler.createChildHint(
            throwable,
            suppressed,
            DescriptionThrowableAssertion.OCCURRED_EXCEPTION_SUPPRESSED,
            maxStackTrace
        )
    }
