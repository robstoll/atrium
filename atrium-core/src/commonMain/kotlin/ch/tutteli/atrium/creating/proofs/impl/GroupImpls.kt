package ch.tutteli.atrium.creating.proofs.impl

import ch.tutteli.atrium.creating.proofs.*
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.Reportable

internal abstract class MemoizingHoldsProofGroup(override val children: List<Reportable>) : ProofGroup {
    init {
        require(children.any { it is Proof }) {
            "At least one child needs to be a Proof in a ProofGroup. Given: $children"
        }
    }

    private val itHolds by lazy(LazyThreadSafetyMode.NONE) {
        proofs.all { it.holds() }
    }

    override fun holds(): Boolean = itHolds
}

internal abstract class BaseProofGroup(
    override val description: Reportable,
    override val representation: Any,
    children: List<Reportable>
) : MemoizingHoldsProofGroup(children), ProofGroupWithDesignation {
    //TODO 1.3.0 override toString? checkout AssertionGroup impl.

    override fun toString(): String {
        return super.toString() + "(holds=${holds()})"
    }
}

internal class DefaultRootGroup(
    expectationVerb: InlineElement,
    representation: Any,
    children: List<Reportable>
) : BaseProofGroup(expectationVerb, representation, children), RootProofGroup

internal class DefaultProofGroup(
    description: Reportable,
    representation: Any,
    children: List<Reportable>
) : BaseProofGroup(description, representation, children)

internal class DefaultFeatureGroup(
    description: Reportable,
    representation: Any,
    children: List<Reportable>
) : BaseProofGroup(description, representation, children), FeatureProofGroup

internal class DefaultInvisibleProofGroup(
    children: List<Reportable>
) : MemoizingHoldsProofGroup(children), InvisibleProofGroup {

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
) : InvisibleFixedClaimGroup {

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
) : BaseProofGroup(description, representation, children), FixedClaimGroup {
    override fun holds(): Boolean = itHolds
}
