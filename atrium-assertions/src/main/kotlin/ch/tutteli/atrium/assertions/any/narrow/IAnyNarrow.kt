package ch.tutteli.atrium.assertions.any.narrow

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.throwable.thrown.IThrowableThrown.IAbsentThrowableMessageProvider
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IBaseAssertionPlant
import kotlin.reflect.KClass

/**
 * Defines the contract for sophisticated narrowing assertion builders.
 *
 * The assertion is typically created by the [DownCaster] which itself requires an [IAbsentThrowableMessageProvider].
 */
interface IAnyNarrow {

    /**
     * A handler which decides how the lambda -- which could have created subsequent assertions for the
     * down-casted [IAssertionPlant.subject], in case the down-cast of the [IAssertionPlant.subject] to type [TSub]
     * did not have fail -- should be used in reporting.
     *
     * @param T The type of [IAssertionPlant.subject].
     * @param TSub The type to which [IAssertionPlant.subject] should have been be down-casted, hence needs to be a
     *        subtype of [T].
     */
    interface IDownCastFailureHandler<T : Any, TSub : T> {

        /**
         * Makes something with the given [assertionCreator] lambda; might add assertions to [subjectPlant].
         *
         * @param subType The type to which the [subjectPlant]'s [subject][IAssertionPlant.subject] should have been
         *        down-casted.
         * @param subjectPlant The plant to which additional assertions would have been added.
         * @param failingAssertion The [IAssertion] representing the failed assertion that [subjectPlant]'s
         *        [subject][IAssertionPlant.subject] can be down-casted to [TSub].
         * @param assertionCreator The lambda which could have created subsequent assertions for the down-casted
         *        [IAssertionPlant.subject].
         *
         * @throws AssertionError Might throw an [AssertionError] depending on the [subjectPlant].
         */
        fun createAndAddAssertionToPlant(
            subType: KClass<TSub>,
            subjectPlant: IBaseAssertionPlant<T?, *>,
            failingAssertion: IAssertion,
            assertionCreator: IAssertionPlant<TSub>.() -> Unit)
    }
}
