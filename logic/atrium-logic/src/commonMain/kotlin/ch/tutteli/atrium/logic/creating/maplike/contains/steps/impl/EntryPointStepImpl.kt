package ch.tutteli.atrium.logic.creating.maplike.contains.steps.impl

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.basic.contains.steps.impl.EntryPointStepImpl
import ch.tutteli.atrium.logic.creating.maplike.contains.MapLikeContains
import ch.tutteli.atrium.logic.creating.typeutils.MapLike

class EntryPointStepImpl<K, V, T : MapLike, out S : MapLikeContains.SearchBehaviour>(
    container: AssertionContainer<T>,
    override val converter: (T) -> Map<out K, V>,
    searchBehaviour: S
) : EntryPointStepImpl<T, S>(container, searchBehaviour), MapLikeContains.EntryPointStepInternal<K, V, T, S>
