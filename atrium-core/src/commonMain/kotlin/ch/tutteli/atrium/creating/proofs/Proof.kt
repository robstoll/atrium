package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.creating.proofs.impl.*
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.Diagnostic
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.reportables.ReportableGroup
import ch.tutteli.atrium.reporting.reportables.ReportableWithInlineDesignation
import ch.tutteli.kbox.takeIf

/**
 * The base interface of all proofs, providing the method [holds].
 *
 * @since 1.3.0
 */
interface Proof : Reportable {
    /**
     * Indicates whether the proof holds for a given subject or not.
     *
     * @return `true` in case the proof holds otherwise `false`.
     *
     * @since 1.3.0
     */
    fun holds(): Boolean

    /**
     * Extension point for [Proof] factories.
     */
    companion object {
        //TODO 1.3.0 write kdoc for methods

        fun simple(description: InlineElement, representation: Any?, test: () -> Boolean): Proof =
            DefaultSimpleProof(description, representation ?: Text.NULL, test)

        //TODO 1.3.0 lambda for test necessary?
        fun representationOnlyProof(representation: Any?, test: () -> Boolean): Proof =
            RepresentationOnlyProofImpl(representation ?: Text.NULL, test)

        //TODO 1.3.0 return a RootProofGroup or do we introduce a GroupType? At the moment I think different types would
        // be better than a GroupType
        //TODO 1.3.0 here and also for other functions returning a ProofGroup, we should check that at least one child is a proof
        fun rootGroup(
            expectationVerb: InlineElement,
            representation: Any?,
            children: List<Reportable>
        ): RootProofGroup = DefaultRootGroup(
            expectationVerb,
            representation ?: Text.NULL,
            children
            //TODO 1.3.0 why not the following to children?
            //children.unwrapInvisibleGroupIfSingleElement()
        )

        fun group(description: Diagnostic, representation: Any?, children: List<Reportable>): ProofGroup =
            DefaultProofGroup(description, representation ?: Text.NULL, children.unwrapInvisibleGroupIfSingleElement())

        fun featureGroup(description: Diagnostic, representation: Any?, children: List<Reportable>): FeatureProofGroup =
            DefaultFeatureGroup(
                description,
                representation ?: Text.NULL,
                children.unwrapInvisibleGroupIfSingleElement()
            )

        /**
         * Creates an [InvisibleProofGroup] if there are more than one [children], otherwise it returns the only child.
         *
         * @returns The newly created [InvisibleProofGroup] if there are more than one child
         * @throws IllegalArgumentException in case none of the [children] is a [Proof], at least one needs to be
         *
         * @since 1.3.0
         */
        fun invisibleGroupOrSingleChildIfProof(children: List<Reportable>): Proof =
            takeIf(children.size == 1) {
                children[0] as? Proof
            } ?: invisibleGroup(children)

        fun invisibleGroup(children: List<Reportable>): InvisibleProofGroup =
            DefaultInvisibleProofGroup(children.unwrapInvisibleGroup())

        fun invisibleFailingProofGroup(children: List<Diagnostic>): InvisibleFailingProof =
            DefaultInvisibleFailingProofGroup(children)

        fun fixedClaimGroup(
            description: InlineElement,
            representation: Any?,
            children: List<Reportable>,
            holds: Boolean
        ): FixedClaimGroup = DefaultFixedClaimGroup(description, representation ?: Text.NULL, children, holds)

    }
}

interface SimpleProof : Proof, ReportableWithInlineDesignation
interface RepresentationOnlyProof : Proof {
    /**
     * The representation.
     *
     * @since 1.3.0
     */
    val representation: Any
}

