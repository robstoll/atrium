package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerbFactory

object ListAssertionGroupFormatterSpec : ch.tutteli.atrium.spec.reporting.ListAssertionGroupFormatterSpec(
    AssertionVerbFactory,
    { controller, objectFormatter, translator ->
        ListAssertionGroupFormatter(controller, SameLineAssertionPairFormatter(objectFormatter, translator))
    }
)
