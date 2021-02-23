package ch.tutteli.atrium.reporting.text

import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.MethodCallFormatter

/**
 * Marker interface for [AssertionFormatter] which are intended for text output, e.g. for terminal output.
 */
interface TextMethodCallFormatter : MethodCallFormatter
