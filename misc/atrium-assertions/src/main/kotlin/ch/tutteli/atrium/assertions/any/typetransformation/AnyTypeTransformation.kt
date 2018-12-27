@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.assertions.any.typetransformation

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.any.typetransformation.AnyTypeTransformation.TypeTransformationFailureHandler
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Defines the contract for sophisticated narrowing assertion builders.
 *
 * The assertion is typically created by the [DownCaster] or the more generic [TypeTransformer]
 * which itself requires a [TypeTransformationFailureHandler].
 */
@Deprecated(
    "use the interface from package domain.creating; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.domain.creating.any.typetransformation.AnyTypeTransformation")
)
interface AnyTypeTransformation {

    /**
     * A handler which decides how the lambda -- which could have created subsequent assertions for the
     * transformed [AssertionPlant.subject] if the transformation of the [AssertionPlant.subject] to type [TSub]
     * did not fail -- should be used in reporting.
     *
     * @param T The type of [AssertionPlant.subject].
     * @param TSub The type to which [AssertionPlant.subject] should have been transformed to.
     */
    @Deprecated(
        "use the interface from package domain.creating; will be removed with 1.0.0",
        ReplaceWith("ch.tutteli.atrium.domain.creating.any.typetransformation.AnyTypeTransformation.TypeTransformationFailureHandler")
    )
    interface TypeTransformationFailureHandler<in T : Any, out TSub : Any> {

        /**
         * Makes something with the given [assertionCreator] lambda; might add assertions to [subjectPlant].
         *
         * @param warningTransformationFailed Explains why the [subjectPlant]'s [subject][AssertionPlant.subject] could not be
         *   should have been down-casted.
         * @param subjectPlant The plant to which additional assertions would have been added.
         * @param failingAssertion The failing [Assertion] representing that [subjectPlant]'s
         *   [subject][AssertionPlant.subject] can be transformed to [TSub].
         * @param assertionCreator The lambda which could have created subsequent assertions for the transformed
         *   [AssertionPlant.subject].
         *
         * @throws AssertionError Might throw an [AssertionError] depending on the [subjectPlant].
         */
        fun createAndAddAssertionToPlant(
            warningTransformationFailed: Translatable,
            subjectPlant: BaseAssertionPlant<T?, *>,
            failingAssertion: Assertion,
            assertionCreator: AssertionPlant<TSub>.() -> Unit
        )
    }
}
