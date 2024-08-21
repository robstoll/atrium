package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder

/**
 * Collection of assertion functions and builders which are applicable to subjects with an [Iterator] type.
 */
interface IteratorAssertions {
    fun <E, T : Iterator<E>> hasNext(container: AssertionContainer<T>): Assertion
    fun <E, T : Iterator<E>> hasNotNext(container: AssertionContainer<T>): Assertion
    fun <E, T : Iterator<E>> next(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, E>
}
