package ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours

import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder

@Deprecated("use CharSequenceContainsSearchBehaviours.ignoringCase, will be removed with 1.0.0", ReplaceWith("CharSequenceContainsSearchBehaviours.ignoringCase(containsBuilder)", "ch.tutteli.atrium.creating.charsequence.contains.searchbehaviours.CharSequenceContainsSearchBehaviours"))
fun <T : CharSequence> _containsIgnoringCase(containsBuilder: CharSequenceContainsBuilder<T, CharSequenceContainsNoOpSearchBehaviour>)
    = CharSequenceContainsBuilder(containsBuilder.plant, CharSequenceContainsIgnoringCaseSearchBehaviour(containsBuilder.searchBehaviour))
