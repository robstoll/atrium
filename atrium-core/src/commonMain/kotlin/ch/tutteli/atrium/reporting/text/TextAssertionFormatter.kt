// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.reporting.text

import ch.tutteli.atrium.reporting.AssertionFormatter

/**
 * Marker interface for [AssertionFormatter] which are intended for text output, e.g. for terminal output.
 */
@Deprecated("Switch to Proof based reporting, will be removed with 2.0.0 at the latest")
interface TextAssertionFormatter: AssertionFormatter
