//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.iterable.contains.steps.impl

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.basic.contains.steps.impl.EntryPointStepImpl
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

class EntryPointStepImpl<E, T : IterableLike, out S : IterableLikeContains.SearchBehaviour>(
    container: AssertionContainer<T>,
    override val converter: (T) -> Iterable<E>,
    searchBehaviour: S
) : EntryPointStepImpl<T, S>(container, searchBehaviour), IterableLikeContains.EntryPointStepInternal<E, T, S>
