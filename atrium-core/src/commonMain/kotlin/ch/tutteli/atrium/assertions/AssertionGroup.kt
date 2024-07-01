//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.proofs.ProofGroup
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * The base interface for [Assertion] groups, providing a default implementation for [Assertion.holds]
 * which returns `true` if all its [assertions] hold.
 */
@Deprecated(
    "switch to ProofGroup, will be removed with 2.0.0 at the latest",
    ReplaceWith("ProofGroup", "ch.tutteli.atrium.creating.proofs.ProofGroup")
)
interface AssertionGroup : Assertion, ProofGroup {

    override val children: List<Reportable> get() = assertions

    /**
     * The description of the group.
     */
    val description: Translatable

    /**
     * The type of the group, e.g. [RootAssertionGroupType].
     */
    val type: AssertionGroupType


    /**
     * A complementing representation to the description -- typically the subject for which the [assertions]
     * are defined.
     *
     * For instance, if the description is `index 0` then the representation shows what is at index 0.
     */
    val representation: Any

    /**
     * The [Assertion]s of this group, which are defined for the subject represented by [representation].
     */
    val assertions: List<Assertion> get() = proofs.filterIsInstance<Assertion>()

    /**
     * Holds if all its [proofs] hold.
     *
     * @return `true` if all [proofs] hold; `false` otherwise.
     */
    override fun holds(): Boolean = super.holds()
}
