package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.impl.DelegatingExpectBasedOnProofContainer
import ch.tutteli.atrium.creating.impl.DelegatingExpectImpl

/**
 * Represents an [Expect] which passes on appended [Proof]s to a given [Expect].
 */
interface DelegatingExpect<T> : Expect<T> {
    companion object {
        @OptIn(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
        operator fun <T> invoke(container: ProofContainer<*>, maybeSubject: Option<T>): Expect<T> =
            DelegatingExpectBasedOnProofContainer(container, maybeSubject)

        //TODO remove with 2.0.0 at the latest
        @Suppress("DEPRECATION")
        @Deprecated(
            "switch to ProofContainer, AssertionContainer will be removed with 2.0.0 at the latest",
            ReplaceWith(
                "DelegatingExpect(expect.toExpect().toProofContainer)",
                "ch.tutteli.atrium.logic.toExpect()",
                "ch.tutteli.atrium.creating.toProofContainer"
            )
        )
        @OptIn(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
        operator fun <T> invoke(expect: AssertionContainer<*>, maybeSubject: Option<T>): Expect<T> =
            DelegatingExpectImpl(expect, maybeSubject)
    }
}
