package ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.creating.charsequence.contains.builders.CharSequenceContainsBuilder

fun <T : CharSequence> _containsIgnoringCase(
    containsBuilder: CharSequenceContains.Builder<T, NoOpSearchBehaviour>
): CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>
    = CharSequenceContainsBuilder(
        containsBuilder.plant,
        IgnoringCaseSearchBehaviourImpl(containsBuilder.searchBehaviour)
    )
