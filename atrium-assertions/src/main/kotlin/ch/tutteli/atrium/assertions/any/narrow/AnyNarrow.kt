package ch.tutteli.atrium.assertions.any.narrow

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.any.narrow.AnyNarrow.DownCastFailureHandler
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.BaseAssertionPlant
import kotlin.reflect.KClass

/**
 * Defines the contract for sophisticated narrowing assertion builders.
 *
 * The assertion is typically created by the [DownCaster] which itself requires a [DownCastFailureHandler].
 */
interface AnyNarrow {

    /**
     * A handler which decides how the lambda -- which could have created subsequent assertions for the
     * down-casted [AssertionPlant.subject] if the down-cast of the [AssertionPlant.subject] to type [TSub]
     * did not fail -- should be used in reporting.
     *
     * @param T The type of [AssertionPlant.subject].
     * @param TSub The type to which [AssertionPlant.subject] should have been be down-casted, hence needs to be a
     *        subtype of [T].
     */
    interface DownCastFailureHandler<T : Any, TSub : T> {

        /**
         * Makes something with the given [assertionCreator] lambda; might add assertions to [subjectPlant].
         *
         * @param subType The type to which the [subjectPlant]'s [subject][AssertionPlant.subject] should have been
         *        down-casted.
         * @param subjectPlant The plant to which additional assertions would have been added.
         * @param failingAssertion The [Assertion] representing the failed assertion that [subjectPlant]'s
         *        [subject][AssertionPlant.subject] can be down-casted to [TSub].
         * @param assertionCreator The lambda which could have created subsequent assertions for the down-casted
         *        [AssertionPlant.subject].
         *
         * @throws AssertionError Might throw an [AssertionError] depending on the [subjectPlant].
         */
        fun createAndAddAssertionToPlant(
            subType: KClass<TSub>,
            subjectPlant: BaseAssertionPlant<T?, *>,
            failingAssertion: Assertion,
            assertionCreator: AssertionPlant<TSub>.() -> Unit)
    }
}
