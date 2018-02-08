package ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsBuilder

fun <T : CharSequence> _containsIgnoringCase(containsBuilder: CharSequenceContainsBuilder<T, CharSequenceContainsNoOpSearchBehaviour>)
    = CharSequenceContainsBuilder(
        containsBuilder.plant,
        CharSequenceContainsIgnoringCaseSearchBehaviourImpl(containsBuilder.searchBehaviour)
    )
