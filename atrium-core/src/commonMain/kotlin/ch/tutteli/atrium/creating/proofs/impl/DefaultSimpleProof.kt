package ch.tutteli.atrium.creating.proofs.impl

import ch.tutteli.atrium.creating.proofs.SimpleProof
import ch.tutteli.atrium.reporting.reportables.InlineElement

internal class DefaultSimpleProof(
    override val description: InlineElement,
    override val representation: Any,
    test: () -> Boolean
) : TestBasedProof(test), SimpleProof {

    /**
     * @suppress
     */
    override fun toString() = "DefaultSimpleProof(h=${holds()}, $description: $representation)"
}
