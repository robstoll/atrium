//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

@file:JvmMultifileClass
@file:JvmName("ImplsKt")
package ch.tutteli.atrium.logic

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.impl.DefaultAnyAssertions
import ch.tutteli.atrium.logic.impl.DefaultCharSequenceAssertions
import ch.tutteli.atrium.logic.impl.DefaultCollectionLikeAssertions
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
import ch.tutteli.atrium.logic.impl.DefaultThrowableAssertions

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._anyImpl
    get() = getImpl(AnyAssertions::class) { DefaultAnyAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._charSequenceImpl
    get() = getImpl(CharSequenceAssertions::class) { DefaultCharSequenceAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._collectionLikeImpl
    get() = getImpl(CollectionLikeAssertions::class) { DefaultCollectionLikeAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._comparableImpl
    get() = getImpl(ComparableAssertions::class) { DefaultComparableAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._featureImpl
    get() = getImpl(FeatureAssertions::class) { DefaultFeatureAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._floatingPointImpl
    get() = getImpl(FloatingPointAssertions::class) { DefaultFloatingPointAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._fun0Impl
    get() = getImpl(Fun0Assertions::class) { DefaultFun0Assertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._iterableLikeImpl
    get() = getImpl(IterableLikeAssertions::class) { DefaultIterableLikeAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._iteratorImpl
    get() = getImpl(IteratorAssertions::class) { DefaultIteratorAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._listImpl
    get() = getImpl(ListAssertions::class) { DefaultListAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._mapImpl
    get() = getImpl(MapAssertions::class) { DefaultMapAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._mapEntryImpl
    get() = getImpl(MapEntryAssertions::class) { DefaultMapEntryAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._pairImpl
    get() = getImpl(PairAssertions::class) { DefaultPairAssertions() }

@PublishedApi
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
internal inline val <T> AssertionContainer<T>._throwableImpl
    get() = getImpl(ThrowableAssertions::class) { DefaultThrowableAssertions() }

