package ch.tutteli.atrium.assertions.builders.impl.representationOnly

import ch.tutteli.atrium.assertions.RepresentationOnlyAssertion
import ch.tutteli.atrium.assertions.builders.RepresentationOnly

internal object HoldsStepImpl : RepresentationOnly.HoldsStep,
    ch.tutteli.atrium.assertions.builders.common.impl.HoldsStepImpl<RepresentationOnly.RepresentationStep>() {

    override fun createNextStep(test: () -> Boolean): RepresentationOnly.RepresentationStep =
        RepresentationOnly.RepresentationStep.create(test)
}

internal class RepresentationStepImpl(override val test: () -> Boolean) : RepresentationOnly.RepresentationStep {

    override fun withRepresentation(representation: Any?): RepresentationOnly.FinalStep =
        RepresentationOnly.FinalStep.create(test, representation)
}

internal class FinalStepImpl(
    override val test: () -> Boolean,
    override val representation: Any?
) : RepresentationOnly.FinalStep {

    override fun build(): RepresentationOnlyAssertion =
        RepresentationOnlyAssertionImpl(test, representation)
}
