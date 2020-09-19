package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic.creating.typeutils.CollectionLike

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [Collection] type.
 */
interface CollectionLikeAssertions {

    fun <T : CollectionLike> isEmpty(container: AssertionContainer<T>, converter: (T) -> Collection<*>): Assertion
    fun <T : CollectionLike> isNotEmpty(container: AssertionContainer<T>, converter: (T) -> Collection<*>): Assertion

    fun <T : CollectionLike> size(
        container: AssertionContainer<T>,
        converter: (T) -> Collection<*>
    ): ExtractedFeaturePostStep<T, Int>
}
