package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.proofs.Proof

@ExperimentalComponentFactoryContainer
@ExperimentalNewExpectTypes
internal class DelegatingExpectBasedOnProofContainer<T>(
    private val container: ProofContainer<*>,
    maybeSubject: Option<T>
) : BaseExpectImpl<T>(maybeSubject), DelegatingExpect<T> {

    override val components: ComponentFactoryContainer
        get() = container.components

    override fun append(proof: Proof): Expect<T> =
        apply { container.append(proof) }
}
