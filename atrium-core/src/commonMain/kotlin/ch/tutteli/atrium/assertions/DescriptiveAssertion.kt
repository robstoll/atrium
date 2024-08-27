//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.reportables.ReportableWithDesignation
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * The base interface for [Assertion]s which consist of a [representation] of an expected result
 * with a complementary [description].
 *
 * For instance, the assertion `is not: null` could be represented with this type where `null` is the [representation]
 * and `is not` the complementary [description].
 */
@Deprecated(
    "Switch to Proof, will be removed with 2.0.0 at the latest",
    ReplaceWith(
        "Proof.simple(description, representation, { TODO(\"Define when the proof holds\") })",
        "ch.tutteli.atrium.creating.proofs.Proof"
    )
)
interface DescriptiveAssertion : Assertion, ReportableWithDesignation {
    /**
     * The representation of the expected result such as `1`, `null` etc.
     */
    override val representation: Any

    /**
     * The complementary description to the [representation] result such as `contains`, `is not` etc.
     */
    override val description: Translatable
}
