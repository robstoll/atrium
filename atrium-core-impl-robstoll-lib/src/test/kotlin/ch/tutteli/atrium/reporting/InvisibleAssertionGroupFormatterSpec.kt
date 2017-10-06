package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerbFactory

object InvisibleAssertionGroupFormatterSpec : ch.tutteli.atrium.spec.reporting.InvisibleAssertionGroupFormatterSpec(
    AssertionVerbFactory, ::InvisibleAssertionGroupFormatter
)
