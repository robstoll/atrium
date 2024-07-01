//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.assertions.builders.impl.representationOnly

import ch.tutteli.atrium.assertions.RepresentationOnlyAssertion

class RepresentationOnlyAssertionImpl(
    private val test: () -> Boolean,
    override val representation: Any?
) : RepresentationOnlyAssertion {

    override fun holds() = test()
}
