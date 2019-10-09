package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.RepresentationOnlyAssertion
import ch.tutteli.atrium.assertions.builders.impl.representationOnly.FinalStepImpl
import ch.tutteli.atrium.assertions.builders.impl.representationOnly.HoldsStepImpl
import ch.tutteli.atrium.assertions.builders.impl.representationOnly.RepresentationStepImpl
import ch.tutteli.atrium.reporting.RawString

/**
 * Defines the contract to build an [RepresentationOnlyAssertion].
 */
interface RepresentationOnly {

    /**
     * Step which allows to specify [RepresentationOnlyAssertion.holds].
     */
    interface HoldsStep : ch.tutteli.atrium.assertions.builders.common.HoldsStep<RepresentationStep> {

        companion object {
            /**
             * Factory method to create the [HoldsStep] step in the process of building a descriptive assertion.
             */
            fun create(): HoldsStep = HoldsStepImpl
        }
    }

    /**
     * Step which allows to specify an [RepresentationOnlyAssertion.representation].
     */
    interface RepresentationStep {
        /**
         * The previously defined test which is used to determine [DescriptiveAssertion.holds].
         */
        val test: () -> Boolean

        /**
         * Uses the given [representation] as [RepresentationOnlyAssertion.representation].
         *
         * Notice, if you want to use a text (e.g. a [String]) as representation,
         * then wrap it into a [RawString] via [RawString.create] and pass the [RawString] instead.
         */
        fun withRepresentation(representation: Any?): FinalStep

        companion object {
            /**
             * Factory method to create the [RepresentationStep] in the building process
             * of an [RepresentationOnlyAssertion].
             */
            fun create(test: () -> Boolean): RepresentationStep = RepresentationStepImpl(test)
        }
    }


    /**
     * Final step which creates an [RepresentationOnlyAssertion] based on the previously defined [representation].
     */
    interface FinalStep : AssertionBuilderFinalStep<RepresentationOnlyAssertion> {
        /**
         * The previously defined test which is used to determine [DescriptiveAssertion.holds].
         */
        val test: () -> Boolean

        /**
         * The previously defined [RepresentationOnlyAssertion.representation].
         */
        val representation: Any?

        companion object {
            /**
             * Factory method to create the [FinalStep] in the building process of a [RepresentationOnlyAssertion]
             * based on the given [test] and [representation].
             */
            fun create(test: () -> Boolean, representation: Any?): FinalStep = FinalStepImpl(test, representation)
        }
    }
}
