package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AssertionVerbFactory

object TextMethodCallFormatterSpec :  ch.tutteli.atrium.spec.reporting.TextMethodCallFormatterSpec(
    AssertionVerbFactory, { TextMethodCallFormatter }
)
