package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep

interface CollectionAssertions {
    fun <T : Collection<*>> isEmpty(container: AssertionContainer<T>): Assertion
    fun <T : Collection<*>> isNotEmpty(container: AssertionContainer<T>): Assertion
    fun <T : Collection<*>> size(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, Int>
}