package ch.tutteli.atrium.creating.proofs.impl

import ch.tutteli.atrium.creating.proofs.*
import ch.tutteli.atrium.reporting.reportables.Diagnostic
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.reportables.requireOneChild

private fun ProofGroup.requireOneProofAsChild() {
    require(children.any { it is Proof }) {
        "At least one child needs to be a Proof in a ProofGroup. Given: $children"
    }
}


internal abstract class MemoizingHoldsProofGroup(override val children: List<Reportable>) : ProofGroup {
    init {
        requireOneProofAsChild()
    }

    private val itHolds by lazy(LazyThreadSafetyMode.NONE) {
        proofs.all { it.holds() }
    }

    override fun holds(): Boolean = itHolds
}

internal abstract class BaseProofGroup(
    override val description: Diagnostic,
    override val representation: Any,
    children: List<Reportable>
) : MemoizingHoldsProofGroup(children), ProofGroupWithDesignation {
    //TODO 1.3.0 override toString? checkout AssertionGroup impl.

    /**
     * @suppress No need to document this behaviour.
     */
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
    description: Diagnostic,
    representation: Any,
    children: List<Reportable>
) : BaseProofGroup(description, representation, children)

internal class DefaultFeatureGroup(
    description: Diagnostic,
    representation: Any,
    children: List<Reportable>
) : BaseProofGroup(description, representation, children), FeatureProofGroup

internal class DefaultInvisibleProofGroup(
    children: List<Reportable>
) : MemoizingHoldsProofGroup(children), InvisibleProofGroup {
    init {
        requireOneProofAsChild()
    }

    /**
     * @suppress No need to document this behaviour.
     */
    override fun toString(): String {
        return this::class.simpleName + " " + children
    }
}

internal class DefaultInvisibleFailingProofGroup(
    override val children: List<Diagnostic>,
) : InvisibleFailingProof {
    init {
        requireOneChild()
    }

    override fun holds(): Boolean = false

    /**
     * @suppress No need to document this behaviour.
     */
    override fun toString(): String {
        return this::class.simpleName + "holds=" + holds() + ", children=" + children
    }
}

internal class DefaultFixedClaimGroup(
    override val description: InlineElement,
    override val representation: Any,
    override val children: List<Reportable>,
    private val itHolds: Boolean,
) : FixedClaimGroup {
    override fun holds(): Boolean = itHolds

    /**
     * @suppress No need to document this behaviour.
     */
    override fun toString(): String {
        return super.toString() + "(holds=${holds()})"
    }
}
