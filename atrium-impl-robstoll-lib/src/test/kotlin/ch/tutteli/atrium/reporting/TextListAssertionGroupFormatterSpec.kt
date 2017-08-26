package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerbFactory

object TextListAssertionGroupFormatterSpec : ch.tutteli.atrium.spec.reporting.TextListAssertionGroupFormatterSpec(
    AssertionVerbFactory,
    { bulletPoint, controller, objectFormatter, translator ->
        TextListAssertionGroupFormatter(bulletPoint, controller, TextSameLineAssertionPairFormatter(objectFormatter, translator))
    }
)
