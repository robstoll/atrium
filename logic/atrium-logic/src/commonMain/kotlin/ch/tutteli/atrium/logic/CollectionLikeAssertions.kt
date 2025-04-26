//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.typeutils.CollectionLike

/**
 * Collection of assertion functions and builders which are applicable to subjects which can be transformed to a
 * [Collection] - intended for types which are Collection like such as [Map].
 */
@Deprecated("Switch to CollectionLikeProofs, will be removed with 2.0.0 at the latest")
interface CollectionLikeAssertions {

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toBeEmpty, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toBeEmpty()", "ch.tutteli.atrium.creating.proofs.toBeEmpty")
    )
    fun <T : CollectionLike> isEmpty(container: AssertionContainer<T>, converter: (T) -> Collection<*>): Assertion

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToBeEmpty, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToBeEmpty()", "ch.tutteli.atrium.creating.proofs.notToBeEmpty")
    )
    fun <T : CollectionLike> isNotEmpty(container: AssertionContainer<T>, converter: (T) -> Collection<*>): Assertion

    fun <T : CollectionLike> size(
        container: AssertionContainer<T>,
        converter: (T) -> Collection<*>
    ): FeatureExtractorBuilder.ExecutionStep<T, Int>
}
