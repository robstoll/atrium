package ch.tutteli.atrium.domain.creating.transformers.impl

import ch.tutteli.atrium.domain.creating.transformers.impl.ThrowableThrownFailureHandler.Companion.addChildHint
import ch.tutteli.atrium.domain.reporting.AnyBuilder
import ch.tutteli.atrium.translations.DescriptionThrowableExpectation

actual fun AnyBuilder.addAdditionalHints(
    throwable: Throwable
) {
    throwable.suppressed.forEach { suppressed ->
        addChildHint(
            throwable, suppressed,
            DescriptionThrowableExpectation.OCCURRED_EXCEPTION_SUPPRESSED
        )
    }
}
