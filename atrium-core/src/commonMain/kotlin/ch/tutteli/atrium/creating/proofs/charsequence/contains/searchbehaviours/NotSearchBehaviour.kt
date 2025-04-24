package ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain

/**
 * Represents still the default search behaviour but a [CharSequenceToContain.Checker] should be used which verifies
 * that the [CharSequenceToContain.Searcher] could not find the expected object.
 *
 * @since 1.3.0
 */
interface NotSearchBehaviour : NoOpSearchBehaviour
