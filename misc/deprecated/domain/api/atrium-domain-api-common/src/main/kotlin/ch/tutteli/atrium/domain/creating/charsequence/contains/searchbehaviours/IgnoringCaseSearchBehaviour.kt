//TODO remove with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains

/**
 * Represents the search behaviour that a [CharSequenceContains.Searcher] should ignore case
 * when searching for an expected object.
 */
@Deprecated("Use the IgnoringCaseSearchBehaviour from atrium-logic; will be removed with 1.0.0")
interface IgnoringCaseSearchBehaviour : CharSequenceContains.SearchBehaviour
