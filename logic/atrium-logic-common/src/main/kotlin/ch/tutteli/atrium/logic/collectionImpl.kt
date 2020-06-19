package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.impl.DefaultCollectionAssertions

@PublishedApi
internal inline val <E, T : Collection<E>> AssertionContainer<T>._impl
    get() = getImpl(CollectionAssertions::class) { DefaultCollectionAssertions() }
