//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.assertions

/**
 * Represents an [AssertionGroup] with an [InvisibleAssertionGroupType], which means the grouping should be
 * invisible in reporting.
 *l
 * @param assertions The assertions of this group.
 */
@Deprecated(
    "Switch to Proof, will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.creating.proofs.InvisibleProofGroup")
)
internal class InvisibleAssertionGroup
@Deprecated(
    "Switch to Proof, will be removed with 2.0.0 at the latest",
    ReplaceWith("Proof.invisibleGroup(assertions)", "ch.tutteli.atrium.creating.proofs.Proof")
)
constructor(assertions: List<Assertion>) :
    EmptyNameAndRepresentationAssertionGroup(InvisibleAssertionGroupType, assertions)
