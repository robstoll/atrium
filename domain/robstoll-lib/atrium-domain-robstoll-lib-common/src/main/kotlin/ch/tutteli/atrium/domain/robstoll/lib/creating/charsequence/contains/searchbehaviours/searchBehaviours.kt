package ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.builders.CharSequenceContainsBuilder

fun <T : CharSequence> _containsIgnoringCase(
    containsBuilder: CharSequenceContains.Builder<T, NoOpSearchBehaviour>
): CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>
    = CharSequenceContainsBuilder(
        containsBuilder.subjectProvider,
        IgnoringCaseSearchBehaviourImpl(containsBuilder.searchBehaviour)
    )
