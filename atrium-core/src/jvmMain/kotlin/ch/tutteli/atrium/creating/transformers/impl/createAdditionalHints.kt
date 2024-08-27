package ch.tutteli.atrium.creating.transformers.impl

import ch.tutteli.atrium.creating.proofs.AnyBuilder
import ch.tutteli.atrium.creating.transformers.addChildHint
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionThrowableProof

actual fun AnyBuilder.addAdditionalHints(
    throwable: Throwable
) {
    throwable.suppressed.forEach { suppressed ->
        addChildHint(
            throwable, suppressed,
            DescriptionThrowableProof.OCCURRED_EXCEPTION_SUPPRESSED
        )
    }
}
