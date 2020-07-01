//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

@file:JvmMultifileClass
@file:JvmName("ImplsKt")
package ch.tutteli.atrium.logic

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.impl.DefaultAnyAssertions
import ch.tutteli.atrium.logic.impl.DefaultCharSequenceAssertions
import ch.tutteli.atrium.logic.impl.DefaultCollectionAssertions
import ch.tutteli.atrium.logic.impl.DefaultComparableAssertions
import ch.tutteli.atrium.logic.impl.DefaultFeatureAssertions
import ch.tutteli.atrium.logic.impl.DefaultFloatingPointAssertions
import ch.tutteli.atrium.logic.impl.DefaultFun0Assertions
import ch.tutteli.atrium.logic.impl.DefaultIterableLikeAssertions
import ch.tutteli.atrium.logic.impl.DefaultIteratorAssertions
import ch.tutteli.atrium.logic.impl.DefaultListAssertions
import ch.tutteli.atrium.logic.impl.DefaultMapAssertions
import ch.tutteli.atrium.logic.impl.DefaultMapEntryAssertions
import ch.tutteli.atrium.logic.impl.DefaultPairAssertions

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
internal inline val <T> AssertionContainer<T>._comparableImpl
    get() = getImpl(ComparableAssertions::class) { DefaultComparableAssertions() }

@PublishedApi
internal inline val <T> AssertionContainer<T>._featureImpl
    get() = getImpl(FeatureAssertions::class) { DefaultFeatureAssertions() }

@PublishedApi
internal inline val <T> AssertionContainer<T>._floatingPointImpl
    get() = getImpl(FloatingPointAssertions::class) { DefaultFloatingPointAssertions() }

@PublishedApi
internal inline val <T> AssertionContainer<T>._fun0Impl
    get() = getImpl(Fun0Assertions::class) { DefaultFun0Assertions() }

@PublishedApi
internal inline val <T> AssertionContainer<T>._iterableLikeImpl
    get() = getImpl(IterableLikeAssertions::class) { DefaultIterableLikeAssertions() }

@PublishedApi
internal inline val <T> AssertionContainer<T>._iteratorImpl
    get() = getImpl(IteratorAssertions::class) { DefaultIteratorAssertions() }

@PublishedApi
internal inline val <T> AssertionContainer<T>._listImpl
    get() = getImpl(ListAssertions::class) { DefaultListAssertions() }

@PublishedApi
internal inline val <T> AssertionContainer<T>._mapImpl
    get() = getImpl(MapAssertions::class) { DefaultMapAssertions() }

@PublishedApi
internal inline val <T> AssertionContainer<T>._mapEntryImpl
    get() = getImpl(MapEntryAssertions::class) { DefaultMapEntryAssertions() }

@PublishedApi
internal inline val <T> AssertionContainer<T>._pairImpl
    get() = getImpl(PairAssertions::class) { DefaultPairAssertions() }

