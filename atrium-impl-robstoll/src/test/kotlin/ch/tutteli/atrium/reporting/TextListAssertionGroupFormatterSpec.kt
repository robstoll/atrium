package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerbFactory

object TextListAssertionGroupFormatterSpec : ch.tutteli.atrium.spec.reporting.TextListAssertionGroupFormatterSpec(
    AssertionVerbFactory,
    { controller, objectFormatter, translator ->
        TextListAssertionGroupFormatter(controller, TextSameLineAssertionPairFormatter(objectFormatter, translator))
    }
)
