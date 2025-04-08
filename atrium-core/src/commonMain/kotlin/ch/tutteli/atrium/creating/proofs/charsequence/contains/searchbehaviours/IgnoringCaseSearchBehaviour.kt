package ch.tutteli.atrium.creating.proofs.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.creating.proofs.charsequence.contains.CharSequenceToContain

/**
 * Represents the search behaviour that a [CharSequenceToContain.Searcher] should ignore case
 * when searching for an expected object.
 */
interface IgnoringCaseSearchBehaviour : CharSequenceToContain.SearchBehaviour
