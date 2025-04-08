package ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.impl

import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.reporting.reportables.Description
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof.Companion.IGNORING_CASE

/**
 * Represents the search behaviour that a [CharSequenceToContain.Searcher] should ignore case
 * when searching for an expected object.
 */
class IgnoringCaseSearchBehaviourImpl(
    private val previousBehaviour: NoOpSearchBehaviour
) : IgnoringCaseSearchBehaviour {

    override fun decorateDescription(description: Description): Description {
        val previousDecorated = previousBehaviour.decorateDescription(description)
        return previousDecorated.IGNORING_CASE
    }
}
