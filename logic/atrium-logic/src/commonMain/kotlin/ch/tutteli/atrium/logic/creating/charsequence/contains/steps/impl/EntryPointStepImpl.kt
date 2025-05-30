//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.charsequence.contains.steps.impl

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.logic.creating.basic.contains.steps.impl.EntryPointStepImpl

class EntryPointStepImpl<T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    container: AssertionContainer<T>,
    searchBehaviour: S
) : EntryPointStepImpl<T, S>(container, searchBehaviour), CharSequenceContains.EntryPointStepInternal<T, S>

