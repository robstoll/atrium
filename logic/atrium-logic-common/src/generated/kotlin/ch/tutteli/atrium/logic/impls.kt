//---------------------------------------------------
//  Generated content, modify:
//  logic/atrium-logic-common/build.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.impl.DefaultAnyAssertions
import ch.tutteli.atrium.logic.impl.DefaultCharSequenceAssertions
import ch.tutteli.atrium.logic.impl.DefaultCollectionAssertions
import ch.tutteli.atrium.logic.impl.DefaultFeatureAssertions
import ch.tutteli.atrium.logic.impl.DefaultIterableLikeAssertions

@PublishedApi
internal inline val <T> AssertionContainer<T>._anyImpl
    get() = getImpl(AnyAssertions::class) { DefaultAnyAssertions() }

@PublishedApi
internal inline val <T> AssertionContainer<T>._charSequenceImpl
    get() = getImpl(CharSequenceAssertions::class) { DefaultCharSequenceAssertions() }

@PublishedApi
internal inline val <T> AssertionContainer<T>._collectionImpl
    get() = getImpl(CollectionAssertions::class) { DefaultCollectionAssertions() }

@PublishedApi
internal inline val <T> AssertionContainer<T>._featureImpl
    get() = getImpl(FeatureAssertions::class) { DefaultFeatureAssertions() }

@PublishedApi
internal inline val <T> AssertionContainer<T>._iterableLikeImpl
    get() = getImpl(IterableLikeAssertions::class) { DefaultIterableLikeAssertions() }

