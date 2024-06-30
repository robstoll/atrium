package ch.tutteli.atrium.creating.transformers.impl

import ch.tutteli.atrium.creating.proofs.builders.AnyBuilder
import ch.tutteli.atrium.creating.transformers.addChildHint
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionThrowableProof

//TODO 1.3.0 shouldn't the receiver be AnyReportableGroupBuilder?
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
