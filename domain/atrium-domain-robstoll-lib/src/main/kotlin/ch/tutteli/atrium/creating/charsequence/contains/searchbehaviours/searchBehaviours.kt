package ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsBuilder

fun <T : CharSequence> _containsIgnoringCase(
    containsBuilder: CharSequenceContains.Builder<T, CharSequenceContainsNoOpSearchBehaviour>
): CharSequenceContains.Builder<T, CharSequenceContainsIgnoringCaseSearchBehaviour>
    = CharSequenceContainsBuilder(
        containsBuilder.plant,
        CharSequenceContainsIgnoringCaseSearchBehaviourImpl(containsBuilder.searchBehaviour)
    )
