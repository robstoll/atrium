//TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.InvisibleAssertionGroup
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.proofs.InvisibleProofGroup
import ch.tutteli.atrium.creating.proofs.Proof

@ExperimentalComponentFactoryContainer
@ExperimentalNewExpectTypes
//TODO 1.3.0 deprecate
internal class DelegatingExpectImpl<T>(private val container: AssertionContainer<*>, maybeSubject: Option<T>) :
    BaseExpectImpl<T>(maybeSubject), DelegatingExpect<T> {

    override val components: ComponentFactoryContainer
        get() = container.components

    @Suppress("OVERRIDE_DEPRECATION")
    override fun append(assertion: Assertion): Expect<T> =
        apply { container.append(assertion) }

    override fun append(proof: Proof): Expect<T> =
        when (proof) {
            is Assertion -> append(proof)
            is InvisibleProofGroup -> append(InvisibleAssertionGroup(proof.children.filterIsInstance<Assertion>()))
            else -> throw UnsupportedOperationException("cannot cope with $proof please switch to ProofContainer based DelegatingExpect")
        }
}
