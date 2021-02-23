package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.reporting.text.impl.DefaultTextMethodCallFormatter

object TextMethodCallFormatterSpec : ch.tutteli.atrium.specs.reporting.TextMethodCallFormatterSpec(
    { DefaultTextMethodCallFormatter }
)
