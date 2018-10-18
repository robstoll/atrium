@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder

@Deprecated("Use AssertImpl.charSequence.contains.searchBehaviours.ignoreCase, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.charSequence.contains.searchBehaviours.ignoreCase(containsBuilder)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : CharSequence> _containsIgnoringCase(containsBuilder: CharSequenceContainsBuilder<T, CharSequenceContainsNoOpSearchBehaviour>)
    = CharSequenceContainsBuilder(containsBuilder.plant, CharSequenceContainsIgnoringCaseSearchBehaviour(containsBuilder.searchBehaviour))
