package ch.tutteli.atrium.logic.creating.iterablelike.contains.steps.impl

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.basic.contains.steps.impl.EntryPointStepImpl
import ch.tutteli.atrium.logic.creating.iterablelike.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

class EntryPointStepImpl<E, T : IterableLike, out S : IterableLikeContains.SearchBehaviour>(
    container: AssertionContainer<T>,
    override val converter: (T) -> Iterable<E>,
    searchBehaviour: S
) : EntryPointStepImpl<T, S>(container, searchBehaviour), IterableLikeContains.EntryPointStepInternal<E, T, S>
