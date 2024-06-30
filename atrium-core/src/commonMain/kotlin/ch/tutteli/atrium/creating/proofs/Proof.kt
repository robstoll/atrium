package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.creating.proofs.impl.*
import ch.tutteli.atrium.creating.proofs.impl.DefaultFixedClaimGroup
import ch.tutteli.atrium.creating.proofs.impl.DefaultInvisibleProofGroup
import ch.tutteli.atrium.creating.proofs.impl.DefaultProofGroup
import ch.tutteli.atrium.creating.proofs.impl.DefaultSimpleProof
import ch.tutteli.atrium.creating.proofs.impl.RepresentationOnlyProofImpl
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.Text

/**
 * The base interface of all proofs, providing the method [holds].
 */
interface Proof : Reportable {
    /**
     * Indicates whether the proof holds for a given subject or not.
     *
     * @return `true` in case the proof holds otherwise `false`.
     */
    fun holds(): Boolean

    /**
     * Extension point for [Proof] factories.
     */
    companion object {
        //TODO 1.3.0 write kdoc for methods

        fun simple(description: InlineElement, representation: Any?, test: () -> Boolean): Proof =
            DefaultSimpleProof(description, representation ?: Text.NULL, test)

        fun representationOnlyProof(representation: Any?, test: () -> Boolean): Proof =
            RepresentationOnlyProofImpl(representation ?: Text.NULL, test)

        //TODO 1.3.0 return a RootProofGroup or do we introduce a GroupType? At the moment I think different types would
        // be better than a GroupType
        //TODO 1.3.0 here and also for other functions returning a ProofGroup, we should check that at least one child is a proof
        fun rootGroup(expectationVerb: InlineElement, representation: Any?, children: List<Reportable>): ProofGroup =
            DefaultRootGroup(expectationVerb, representation ?: Text.NULL, children)

        fun group(description: InlineElement, representation: Any?, children: List<Reportable>): ProofGroup =
            DefaultProofGroup(description, representation ?: Text.NULL, children)

        fun featureGroup(description: InlineElement, representation: Any?, children: List<Reportable>): ProofGroup =
            DefaultFeatureGroup(description, representation ?: Text.NULL, children)

        fun invisibleGroup(children: List<Reportable>): ProofGroup = DefaultInvisibleProofGroup(children)

        fun invisibleFixedClaimGroup(children: List<Reportable>, holds: Boolean): ProofGroup =
            DefaultInvisibleFixedClaimGroup(children, holds)

        fun fixedClaimGroup(
            description: InlineElement,
            representation: Any?,
            children: List<Reportable>,
            holds: Boolean
        ): ProofGroup = DefaultFixedClaimGroup(description, representation ?: Text.NULL, children, holds)

    }
}
