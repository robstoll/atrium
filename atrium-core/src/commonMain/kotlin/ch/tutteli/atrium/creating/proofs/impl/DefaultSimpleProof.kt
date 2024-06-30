package ch.tutteli.atrium.creating.proofs.impl

import ch.tutteli.atrium.reporting.reportables.InlineElement

internal class DefaultSimpleProof(
    val description: InlineElement,
    val representation: Any,
    test: () -> Boolean
) : TestBasedProof(test) {

    /**
     * @suppress
     */
    override fun toString() = "$description: $representation (holds=${holds()})"
}
