package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.impl.DefaultAnyAssertions
import ch.tutteli.atrium.logic.impl.DefaultCollectionAssertions
import ch.tutteli.atrium.logic.impl.DefaultFeatureAssertions

/**
 * Use to specify if the generated extension methods which delegate to the implementation of an assertions interface
 * shall use a different name than `_impl` to retrieve the implementation of the assertions interface.
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class ImplVal(@Suppress("unused") val name: String)

@PublishedApi
internal inline val <T> AssertionContainer<T>._impl
    get() = getImpl(AnyAssertions::class) { DefaultAnyAssertions() }

@PublishedApi
internal inline val <T> AssertionContainer<T>._feature
    get() = getImpl(FeatureAssertions::class) { DefaultFeatureAssertions() }

//TODO use _impl once https://youtrack.jetbrains.com/issue/KT-39744 is fixed
@PublishedApi
internal inline val <E, T : Collection<E>> AssertionContainer<T>._collectionImpl
    get() = getImpl(CollectionAssertions::class) { DefaultCollectionAssertions() }
