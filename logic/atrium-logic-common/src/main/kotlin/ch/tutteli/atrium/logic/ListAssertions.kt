package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep

interface ListAssertions {

    fun <E, T : List<E>> get(container: AssertionContainer<T>, index: Int): ExtractedFeaturePostStep<T, E>
}
