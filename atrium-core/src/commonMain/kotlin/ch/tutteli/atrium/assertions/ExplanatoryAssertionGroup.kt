//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.reportables.Reportable

/**
 * Represents an [AssertionGroup] with an [ExplanatoryAssertionGroupType], which means a [Reporter] should not
 * show whether the [assertions] hold or not.
 *
 * @constructor Represents an [AssertionGroup] with an [ExplanatoryAssertionGroupType].
 * @param type The concrete [ExplanatoryAssertionGroupType]
 * @param explanatoryAssertions The [assertions] of this group which shall not be evaluated but are used in reporting
 *   to explain something (rather than making assumptions).
 */
@Deprecated(
    "Switch to Reportable, will be removed with 2.0.0 at the latest",
    ReplaceWith(
        "Reportable.proofExplanation(Proof.invisibleGroup(explanatoryAssertions)) /* note, if you created a holding ExplanatoryAssertionGroup, then you need to create a Proof.fixedClaimGroup instead */",
        "ch.tutteli.atrium.creating.proofs.Proof",
        "ch.tutteli.atrium.reporting.reportables.Reportable"
    )
)
internal class ExplanatoryAssertionGroup(
    type: ExplanatoryAssertionGroupType,
    explanatoryAssertions: List<Assertion>,
    private val holds: Boolean
) : EmptyNameAndRepresentationAssertionGroup(
    type, when (explanatoryAssertions.size) {
        1 -> when (val assertion = explanatoryAssertions[0]) {
            is AssertionGroup -> if (assertion.type is InvisibleAssertionGroupType) assertion.assertions else explanatoryAssertions
            else -> explanatoryAssertions
        }

        else -> explanatoryAssertions
    }
) {

    override fun holds() = holds

    /**
     * @suppress
     */
    override fun toString(): String {
        return this::class.simpleName!!
    }

    override val children: List<Reportable> get() = assertions
}
