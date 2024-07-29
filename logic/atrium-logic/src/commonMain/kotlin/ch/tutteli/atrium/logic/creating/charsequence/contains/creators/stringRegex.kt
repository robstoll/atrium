//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.charsequence.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour


fun <T : CharSequence> CharSequenceContains.CheckerStepLogic<T, NoOpSearchBehaviour>.regex(expected: List<String>): AssertionGroup =
    regex(expected.map { it.toRegex() })
