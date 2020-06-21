package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep

@ImplVal("_collectionImpl")
interface CollectionAssertions {
    fun isEmpty(container: AssertionContainer<Collection<*>>): Assertion
    fun isNotEmpty(container: AssertionContainer<Collection<*>>): Assertion
    fun <T : Collection<*>> size(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, Int>
}