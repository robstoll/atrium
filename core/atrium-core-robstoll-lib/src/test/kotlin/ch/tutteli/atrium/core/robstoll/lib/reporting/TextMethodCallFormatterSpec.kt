package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.core.robstoll.lib.AssertionVerbFactory

object TextMethodCallFormatterSpec :  ch.tutteli.atrium.spec.reporting.TextMethodCallFormatterSpec(
    AssertionVerbFactory, { TextMethodCallFormatter }
)
