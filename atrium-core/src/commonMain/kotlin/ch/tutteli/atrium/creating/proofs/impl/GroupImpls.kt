package ch.tutteli.atrium.creating.proofs.impl

import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.ProofGroup
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.reportables.ReportableGroupWithDesignation

internal abstract class MemoizingHoldsProofGroup(override val children: List<Reportable>) : ProofGroup {

    private val itHolds by lazy(LazyThreadSafetyMode.NONE) {
        proofs.all { it.holds() }
    }

    override fun holds(): Boolean = itHolds
}

internal abstract class BaseProofGroup(
    override val description: InlineElement,
    override val representation: Any,
    children: List<Reportable>
) : MemoizingHoldsProofGroup(children), ReportableGroupWithDesignation {
    //TODO 1.3.0 override toString? checkout AssertionGroup impl.
}

internal class DefaultRootGroup(
    expectationVerb: InlineElement,
    representation: Any,
    children: List<Reportable>
) : BaseProofGroup(expectationVerb, representation, children)

internal class DefaultProofGroup(
    description: InlineElement,
    representation: Any,
    children: List<Reportable>
) : BaseProofGroup(description, representation, children)

internal class DefaultFeatureGroup(
    description: InlineElement,
    representation: Any,
    children: List<Reportable>
) : BaseProofGroup(description, representation, children)

internal class DefaultInvisibleProofGroup(children: List<Reportable>) : MemoizingHoldsProofGroup(children) {
    init {
        require(children.any { it is Proof }) {
            "At least one child needs to be a Proof in an invisible ProofGroup. Given: $children"
        }
    }

    /**
     * @suppress No need to document this behaviour.
     */
    override fun toString(): String {
        return this::class.simpleName + " " + children
    }
}

internal class DefaultInvisibleFixedClaimGroup(
    override val children: List<Reportable>,
    private val holds: Boolean
) : ProofGroup {

    override fun holds(): Boolean = holds

    /**
     * @suppress No need to document this behaviour.
     */
    override fun toString(): String {
        return this::class.simpleName + "holds=" + holds + ", children=" + children
    }
}

internal class DefaultFixedClaimGroup(
    description: InlineElement,
    representation: Any,
    children: List<Reportable>,
    private val itHolds: Boolean,
) : BaseProofGroup(description, representation, children) {
    override fun holds(): Boolean = itHolds
}
