package ch.tutteli.atrium.assertions.builders.impl.representationOnly

import ch.tutteli.atrium.assertions.RepresentationOnlyAssertion

class RepresentationOnlyAssertionImpl(
    private val test: () -> Boolean,
    override val representation: Any?
) : RepresentationOnlyAssertion {

    override fun holds() = test()
}
