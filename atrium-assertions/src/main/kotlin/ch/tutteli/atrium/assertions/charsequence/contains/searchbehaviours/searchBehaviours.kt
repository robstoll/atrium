package ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder

fun <T : CharSequence> _containsIgnoringCase(containsBuilder: CharSequenceContainsBuilder<T, CharSequenceContainsNoOpSearchBehaviour>)
    = CharSequenceContainsBuilder(containsBuilder.plant, CharSequenceContainsIgnoringCaseSearchBehaviour(containsBuilder.searchBehaviour))
