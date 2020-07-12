package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import java.util.*

interface OptionalAssertions {
    fun <T : Optional<*>> isEmpty(container: AssertionContainer<T>): Assertion
    fun <E, T : Optional<E>> isPresent(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, E>
}

