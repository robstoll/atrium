package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.impl.DefaultAnyAssertions
import ch.tutteli.atrium.logic.impl.DefaultCollectionAssertions
import ch.tutteli.atrium.logic.impl.DefaultFeatureAssertions
import ch.tutteli.atrium.logic.impl.DefaultIterableLikeAssertions

@PublishedApi
internal inline val <T> AssertionContainer<T>._anyImpl
    get() = getImpl(AnyAssertions::class) { DefaultAnyAssertions() }

@PublishedApi
internal inline val <T> AssertionContainer<T>._featureImpl
    get() = getImpl(FeatureAssertions::class) { DefaultFeatureAssertions() }

@PublishedApi
internal inline val <T> AssertionContainer<T>._iterableLikeImpl
    get() = getImpl(IterableLikeAssertions::class) { DefaultIterableLikeAssertions() }

@PublishedApi
internal inline val <E, T : Collection<E>> AssertionContainer<T>._collectionImpl
    get() = getImpl(CollectionAssertions::class) { DefaultCollectionAssertions() }
