package ch.tutteli.atrium.core.robstoll.lib.reporting

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

object TextMethodCallFormatterSpec :  ch.tutteli.atrium.spec.reporting.TextMethodCallFormatterSpec(
    AssertionVerbFactory, { TextMethodCallFormatter }
)
