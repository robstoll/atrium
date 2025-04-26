package ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.impl

import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain
import ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.reporting.reportables.Description
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof

/**
 * Represents still the default search behaviour but a [CharSequenceToContain.Checker] should be used which verifies
 * that the [CharSequenceToContain.Searcher] could not find the expected object.
 */
class NotSearchBehaviourImpl : NotSearchBehaviour {
    /**
     * Returns [DescriptionCharSequenceProof.NOT_TO_CONTAIN].
     * @return [DescriptionCharSequenceProof.NOT_TO_CONTAIN]
     */
    override fun decorateDescription(description: Description): Description =
        DescriptionCharSequenceProof.NOT_TO_CONTAIN
}
